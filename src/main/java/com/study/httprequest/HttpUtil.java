package com.study.httprequest;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

public class HttpUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpUtil.class);

    //创建httpclient
//    private static CloseableHttpClient httpClient = HttpClients.createDefault();


    public static String doPost(String url, Map<String, String> parmMap) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http post
        HttpPost httpPost = new HttpPost(url);
        //设置头
        httpPost.addHeader("api_gateway_auth_token", "tokenString");

        //设置请求数据
        List<NameValuePair> params = new ArrayList<>();

        parmMap.forEach((x, y) -> {
            params.add(new BasicNameValuePair(x, y));
        });
        //构建表单
        UrlEncodedFormEntity formEntity = null;
        try {
            formEntity = new UrlEncodedFormEntity(params);
        } catch (UnsupportedEncodingException e) {
            logger.error("http post构建表单错误", e);
        }
        //将表达请求放入到httpost
        httpPost.setEntity(formEntity);

        //返回类型
        CloseableHttpResponse response = null;

        try {
            response = httpClient.execute(httpPost);
            String content = EntityUtils.toString(response.getEntity(), "utf-8");
            return content;

        } catch (Exception e) {
            logger.warn("http post请求执行错误", e);
            try {
                response = httpClient.execute(httpPost);
                String content = EntityUtils.toString(response.getEntity(), "utf-8");
                return content;

            } catch (Exception e1) {
                logger.error("http post请求第二次执行错误", e1);

            }
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException e) {
                    logger.error("http post 资源关闭失败", e);
                }
            }
        }
        return null;

    }

    public static long start = 0;

    public static void main(String[] args) {

        int num = 1;

        CountDownLatch countDownLatch = new CountDownLatch(num);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(num);
        for (int i = 0; i < num; i++) {
            int finalI = i;
            new Thread(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Map<String, String> map = new HashMap<>();
                map.put("id", finalI + "");
                map.put("name", "xiaowang");
                map.put("age", new Random().nextInt(100) + "");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                if (finalI == 0) {
                    start = System.currentTimeMillis();
                }
                String x = doPost("http://localhost:8087/lala/post", map);
//                System.out.println(x);
                countDownLatch.countDown();
            }).start();
        }

        try {
            countDownLatch.await();
            long l = System.currentTimeMillis();
            System.out.println(l - start);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
