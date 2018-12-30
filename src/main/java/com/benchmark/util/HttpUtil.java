package com.benchmark.util;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

/**
 * @author hejianglong
 * @date 2018/12/30 下午5:01
 * @email hejlong@163.com
 * @Desc
 */
public class HttpUtil {

    public static CloseableHttpClient getHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(100);
        cm.setDefaultMaxPerRoute(20);
        cm.setDefaultMaxPerRoute(50);
        return HttpClients.custom().setConnectionManager(cm).build();
    }
}
