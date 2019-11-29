package com.study.httprequest;

import org.apache.http.*;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.*;

public class HttpClientUtils {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtils.class);

    public static String METHOD_POST = "post";
    public static String METHOD_GET = "get";

    private static PoolingHttpClientConnectionManager connectionManager = null;
    private static HttpClientBuilder httpBulder = null;
    private static RequestConfig requestConfig = null;

    private static int MAXCONNECTION = 20;  //最大连接数

    private static int DEFAULTMAXCONNECTION = 5;

    private static String IP = "localhost";
    private static int PORT = 8087;

    static {
        //设置http的状态参数   //用于设置超时时间
        requestConfig = RequestConfig.custom()
                .setSocketTimeout(5000)
                .setConnectTimeout(5000)
                .setConnectionRequestTimeout(5000)
                .build();

        HttpHost target = new HttpHost(IP, PORT);
        connectionManager = new PoolingHttpClientConnectionManager();
        connectionManager.setMaxTotal(MAXCONNECTION);
        connectionManager.setDefaultMaxPerRoute(DEFAULTMAXCONNECTION);
        connectionManager.setMaxPerRoute(new HttpRoute(target), 20);
        httpBulder = HttpClients.custom();
        httpBulder.setConnectionManager(connectionManager);
    }

    public static CloseableHttpClient getConnection() {
        CloseableHttpClient httpClient = httpBulder.build();
        return httpClient;
    }

    private static HttpUriRequest getRequestMethod(Map<String, String> map, String url, String method) {
        List<NameValuePair> params = new ArrayList<>();
        map.forEach((x, y) -> {
            params.add(new BasicNameValuePair(x, y));
        });

        HttpUriRequest reqMethod = null;
        if (METHOD_POST.equals(method)) {
            reqMethod = RequestBuilder.post().setUri(url)
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
        } else if (METHOD_GET.equals(method)) {
            reqMethod = RequestBuilder.get().setUri(url)
                    .addParameters(params.toArray(new BasicNameValuePair[params.size()]))
                    .setConfig(requestConfig).build();
        }
        return reqMethod;
    }

    public static String doActive(String url, Map<String, String> parmMap, String method) {
        CloseableHttpClient client = getConnection();
        HttpUriRequest post = getRequestMethod(parmMap, url, method);
        HttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (IOException e) {
            logger.warn("http post请求执行错误", e);
            return null;
        }
        HttpEntity entity = response.getEntity();
        String message = null;
        try {
            message = EntityUtils.toString(entity, Consts.UTF_8); //释放资源到池中
        } catch (IOException e) {
            logger.error("http post 数据转换错误", e);
        }
        if (response.getStatusLine().getStatusCode() == 200) {
            return message;
        } else {
            logger.error("请求失败,返回转态码为：" + response.getStatusLine().getStatusCode());
            return null;
        }
    }

    public static void main(String args[]) throws IOException {
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", "2");
        map.put("name", "sdf");
        map.put("age", "12");
        for (int i = 0; i < 100; i++) {
            System.out.println(i);
            new Thread(() -> {
                String s = doActive("http://localhost:8087/lala", map, METHOD_GET);
                System.out.println("返回值为：" + s);
            }).start();

        }
//        String s = doPost("http://localhost:8087/lala/post", map);
//        System.out.println("结果为：" + s);
        try {
            Thread.sleep(100000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
