package com.benchmark.http;

import com.alibaba.fastjson.JSONObject;
import com.benchmark.constant.MethodType;
import com.benchmark.constant.RequestType;
import com.benchmark.util.StringUtils;
import lombok.Data;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.util.concurrent.CountDownLatch;

/**
 * @author hejianglong
 * @date 2018/12/27.
 */
@Data
public abstract class HttpRequest {

    private RequestType requestType;

    private String requestUrl;

    private String params = "";

    private MethodType methodType;

    private HttpRequestHeader httpRequestHeader;

    // 并发线程数量
    private int threadNum = 1;

    // 并发请求累加器到达threadNum数量后发起并发请求
    protected CountDownLatch start = new CountDownLatch(1);

    // 请求响应数量, 当所有请求返回的时候取消阻塞程序结束
    protected CountDownLatch end = new CountDownLatch(1);

    public HttpRequest(MethodType methodType, String requestUrl, RequestType requestType) {
        this.requestType = requestType;
        this.requestUrl = requestUrl;
        this.methodType = methodType;
        httpRequestHeader = new HttpRequestHeader(requestType);
    }

    protected abstract void execute() throws InterruptedException;

    protected HttpRequest withHeaders(String key, String value) {
        httpRequestHeader.add(key, value);
        return this;
    }

    protected HttpRequest witheParams(String key, String value) {
        if (StringUtils.isEmpty(params)) {
            params = params.concat("?");
        } else {
            params = params.concat("&");
        }
        params = params.concat(key).concat("=").concat(value);
        return this;
    }

    protected HttpRequest withThreadNum(int threadNum) {
        this.threadNum = threadNum;
        start = new CountDownLatch(threadNum);
        end = new CountDownLatch(threadNum);
        return this;
    }

    protected void executeDoGet(CloseableHttpClient httpClient) throws InterruptedException {
        for(int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doGet(httpClient);
                }
            }).start();
        }
        // 阻塞等待所有并发请求返回
        end.await();
    }

    protected void executeDoPost(CloseableHttpClient httpClient, JSONObject object) throws InterruptedException {
        for(int i = 0; i < threadNum; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    doPost(httpClient, object);
                }
            }).start();
        }
        // 阻塞等待所有并发请求返回
        end.await();
    }

    protected JSONObject doPost(CloseableHttpClient httpClient, JSONObject object){
        if (StringUtils.isEmpty(params)) {
            params = "";
        }
        HttpPost post = new HttpPost(this.requestUrl.concat(params));
        JSONObject response = null;
        try {
            this.getHttpRequestHeader().getHeaders().forEach((key, v) -> {
                post.setHeader(key, v);
            });
            StringEntity s = new StringEntity(object.toString());
            s.setContentEncoding("UTF-8");
            post.setEntity(s);
            this.start.countDown();
            this.start.await();
            HttpResponse res = httpClient.execute(post);
            String result = EntityUtils.toString(res.getEntity());// 返回json格式：
            System.out.println(result);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            end.countDown();
        }
        return response;
    }

    /**
     *
     * params = "?id=1&name=long"
     *
     * @param httpClient
     * @return
     */
    private JSONObject doGet(CloseableHttpClient httpClient) {
        if (StringUtils.isEmpty(this.params)) {
            this.params = "";
        }
        HttpGet get = new HttpGet(requestUrl.concat(params));
        try {
            this.httpRequestHeader.getHeaders().forEach((key, v) -> {
                get.setHeader(key, v);
            });
            // 每到达一个请求的时候，需要并发的总数减一
            start.countDown();
            // 等待所有并发请求到来, 当并发总数减为0的时候
            start.await();
            // 发起并发请求
            HttpResponse res = httpClient.execute(get);
            String result = EntityUtils.toString(res.getEntity());
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            // 每个请求结束后, 并发结束请求总数减一
            // 减为0后程序结束
            end.countDown();
        }
        return null;
    }
}
