package com.buzz.util.framework;

import com.buzz.util.*;
import com.buzz.util.bean.Data;
import com.buzz.util.bean.Handler;
import com.buzz.util.bean.Param;
import com.buzz.util.bean.View;
import com.buzz.util.helper.BeanHelper;
import com.buzz.util.helper.ConfigHelper;
import com.buzz.util.helper.ControllerHelper;
import com.buzz.util.helper.HelperLoader;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求转发器
 */
@WebServlet(urlPatterns = "/", loadOnStartup = 0)
public class DispatcherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        //初始化相关的类
        HelperLoader.init();

        //注册servlet
        ServletContext servletContext = servletConfig.getServletContext();

        //处理JSP的servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(ConfigHelper.getAppJspPath() + "*");

        //注册处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping(ConfigHelper.getAppAssetPath() + "*");


    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String requestMethod = req.getMethod().toLowerCase();
        String requestPath = req.getPathInfo();

        Handler handler = ControllerHelper.getHandler(requestMethod, requestPath);

        if (handler != null) {
            Class<?> controllerHandler = handler.getControllerClass();
            Object controllerBean = BeanHelper.getBean(controllerHandler);

            Map<String, Object> paramMap = new HashMap<>();
            Enumeration<String> paramNames = req.getParameterNames();
            while (paramNames.hasMoreElements()) {
                String paramName = paramNames.nextElement();
                String paramValue = req.getParameter(paramName);
                paramMap.put(paramName, paramValue);


                String body = CodecUtil.decodeURL(StreamUtil.getString(req.getInputStream()));

                if (StringUtils.isNotEmpty(body)) {
                    String[] params = StringUtils.split(body, "&");
                    if (ArrayUtil.isNotEmpty(params)) {
                        for (String param : params) {
                            String[] array = StringUtils.split(param, "=");
                            if (ArrayUtil.isNotEmpty(array)) {
                                String parameterName = array[0];
                                String parameterValue = array[1];
                                paramMap.put(parameterName, parameterValue);
                            }
                        }
                    }
                }

                //
                Param param = new Param(paramMap);
                Method actionMethod = handler.getActionMethod();
                Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                if (result instanceof View) {
                    //return JSP page

                    View view = (View) result;

                    String path = view.getPath();
                    if (StringUtils.isNotEmpty(path)) {
                        if (path.startsWith("/")) {
                            resp.sendRedirect(req.getContextPath() + path);

                        } else {
                            Map<String, Object> model = view.getModel();
                            for (Map.Entry<String, Object> entry : model.entrySet()) {
                                req.setAttribute(entry.getKey(), entry.getValue());
                            }
                            req.getRequestDispatcher(ConfigHelper.getAppJspPath() + path).forward(req, resp);
                        }
                    }

                } else if (result instanceof Data) {
                    Data data = (Data) result;
                    Object model = data.getModel();
                    if (model != null) {
                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        PrintWriter writer = resp.getWriter();
                        String json = JsonUtil.toJson(model);
                        writer.write(json);
                        writer.flush();
                        writer.close();
                    }
                }
            }

        }
    }
}
