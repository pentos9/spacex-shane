package com.buzz.util.helper;

import com.buzz.util.ArrayUtil;
import com.buzz.util.CollectionUtil;
import com.buzz.util.ReflectionUtil;
import com.buzz.util.annotation.Inject;


import java.lang.reflect.Field;
import java.util.Map;

public class IocHelper {
    static {
        Map<Class<?>, Object> beanMap = BeanHelper.getBeanMap();
        if (!CollectionUtil.isEmpty(beanMap)) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                //get bean fields

                Field[] fields = beanClass.getDeclaredFields();

                if (ArrayUtil.isNotEmpty(fields)) {
                    // traverse all fields

                    for (Field beanField : fields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> beanFieldClass = beanField.getType();
                            Object beanFieldInstance = beanMap.get(beanFieldClass);

                            if (beanFieldInstance != null) {
                                ReflectionUtil.setField(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }

                }
            }
        }
    }
}
