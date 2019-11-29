package com.study.httprequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

/**
 * @author riemann
 * @date 2019/05/25 1:35
 */
public class CloseableHttpClientUtilTEST {

    private static String tokenString = "qwert";
    private static String AUTH_TOKEN_EXPIRED = "AUTH_TOKEN_EXPIRED";
    private static CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    /**
     * 以get方式调用第三方接口
     *
     * @param url
     * @param token
     * @return
     */
    public static String doGet(String url, String token) {
        //创建HttpClient对象
//        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
//        if (null != tokenString && !tokenString.equals("")) {
//            tokenString = getToken();
//        }
        //api_gateway_auth_token自定义header头，用于token验证使用
        httpGet.addHeader("api_gateway_auth_token", tokenString);
//        httpGet.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/77.0.3865.120 Safari/537.36");
        try {
            HttpResponse response = httpClient.execute(httpGet);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 以post方式调用第三方接口
     *
     * @param url
     * @param json
     * @return
     */
    public static String doPost(String url, JSONObject json) {

        HttpPost httpPost = new HttpPost(url);
        //api_gateway_auth_token自定义header头，用于token验证使用
        httpPost.addHeader("api_gateway_auth_token", tokenString);
//        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        try {
            StringEntity se = new StringEntity(json.toString());
            se.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
//            se.setContentType("application/json");
            se.setContentType("application/x-www-form-urlencoded");
            //设置请求参数
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                //返回json格式
                String res = EntityUtils.toString(response.getEntity());
                return res;
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    /**
     * 获取第三方接口的token
     */
    public static String getToken() {
        String token = "";
        JSONObject object = new JSONObject();
        object.put("appid", "appid");
        object.put("secretkey", "secretkey");
        if (null == httpClient) {
            httpClient = HttpClientBuilder.create().build();
        }
        HttpPost httpPost = new HttpPost("http://localhost/login");
        httpPost.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.81 Safari/537.36");
        try {
            StringEntity se = new StringEntity(object.toString());
            se.setContentEncoding("UTF-8");
            //发送json数据需要设置contentType
            se.setContentType("application/x-www-form-urlencoded");
            //设置请求参数
            httpPost.setEntity(se);
            HttpResponse response = httpClient.execute(httpPost);
            //这里可以把返回的结果按照自定义的返回数据结果，把string转换成自定义类
            //ResultTokenBO result = JSONObject.parseObject(response, ResultTokenBO.class);
            //把response转为jsonObject
            JSONObject result = (JSONObject) JSONObject.parseObject(String.valueOf(response));
            if (result.containsKey("token")) {
                token = result.getString("token");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return token;
    }

    /**
     * 测试
     */
    public static void test(String telephone) {

        JSONObject object = new JSONObject();
        object.put("zuotian", telephone);

//        //首先获取token
//        tokenString = getToken();
        String response = doPost("http://localhost:8087/lala/post",object);
//        String response = doGet("http://localhost:8087/lala","qwert");
        //如果返回的结果是list形式的，需要使用JSONObject.parseArray转换
        //List<Result> list = JSONObject.parseArray(response, Result.class);
        System.out.println(response);
    }

    public static void main(String[] args) throws Exception {
//        InetAddress localHost = InetAddress.getLocalHost();//获取ip地址
//        System.out.println(localHost.getHostAddress());
//        Enumeration<NetworkInterface> nets = NetworkInterface
//                .getNetworkInterfaces();
//        for (NetworkInterface netint : Collections.list(nets))
//            if (null != netint.getHardwareAddress()) {
//                List<InterfaceAddress> list = netint.getInterfaceAddresses();
//                for (InterfaceAddress interfaceAddress : list) {
//                    String localip = interfaceAddress.getAddress().toString();
//                    System.out.println(localip);
//                }
//            }

        test("12345678910");
    }
}
