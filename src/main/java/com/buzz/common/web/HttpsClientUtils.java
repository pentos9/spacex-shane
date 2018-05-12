package com.buzz.common.web;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class HttpsClientUtils {
    private static final int DEFAULT_CONN_TIMEOUT = 1000 * 60;
    private static final int DEFAULT_READ_TIMEOUT = 1000 * 60 * 10;
    private static final String DEFAULT_CHARSET = "utf-8";

    public static String doPost(String url, Map<String, String> header, Map<String, String> param) throws KeyManagementException, NoSuchAlgorithmException, IOException {
        String result = null;
        HttpClient httpClient = new SSLClient();
        httpClient.getParams().setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, DEFAULT_CONN_TIMEOUT);
        httpClient.getParams().setParameter(CoreConnectionPNames.SO_TIMEOUT, DEFAULT_READ_TIMEOUT);
        HttpPost httpPost = new HttpPost(url);

        if (header != null) {
            Set<String> keys = header.keySet();
            keys.forEach(key -> {
                httpPost.setHeader(key, header.get(key));
            });
        }

        List<NameValuePair> nameValuePairList = new ArrayList<>();
        Iterator<Map.Entry<String, String>> iterator = param.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> entry = (Map.Entry<String, String>) iterator.next();
            nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }

        if (nameValuePairList.size() > 0) {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(nameValuePairList, DEFAULT_CHARSET);
            httpPost.setEntity(entity);
        }

        HttpResponse response = httpClient.execute(httpPost);
        if (response != null) {
            HttpEntity responseEntity = response.getEntity();
            if (responseEntity != null) {
                result = EntityUtils.toString(responseEntity, DEFAULT_CHARSET);
            }
        }

        return result;
    }
}
