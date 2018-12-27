/*
 * Project: com.benchmark.http
 * 
 * File Created at 2018/12/27
 * 
 * Copyright 2018 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.benchmark.http;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author hejianglong
 * @date 2018/12/27 下午9:31
 * @email hejlong@163.com
 * @Desc
 */
public class Handler {

    private static CloseableHttpClient httpClient;

    static {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        httpClient = HttpClients.custom().setConnectionManager(cm).build();
    }

    private CountDownLatch start = new CountDownLatch(1);
    private CountDownLatch end = new CountDownLatch(1);

    private ExecutorService executor = Executors.newFixedThreadPool(1);

    private HttpRequest httpRequest;

    /**
     * 线程数量
     */
    private int threadCount = 1;

    public Handler withThreadCount(int threadCount) {
        if (threadCount <=0) {
            throw new IllegalArgumentException("线程数量必须大于0");
        }
        this.threadCount = threadCount;
        executor = Executors.newFixedThreadPool(threadCount);
        start= new CountDownLatch(threadCount);
        return this;
    }

    // TODO
    public void done() throws InterruptedException {
        for (int i = 0; i < threadCount; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    start.countDown();
                    //httpClient.execute();
                }
            });
        }
        end.await();
    }

    public Handler setHttpInfo(HttpRequest httpRequest) {
        this.httpRequest = httpRequest;
        return this;
    }

    // TODO
    private HttpPost buildPost() throws UnsupportedEncodingException {
        HttpPost post = new HttpPost();
        StringEntity stringEntity = new StringEntity(this.httpRequest.getHttpBody().getJsonString());
        stringEntity.setContentEncoding("UTF-8");
        return post;
    }
}
