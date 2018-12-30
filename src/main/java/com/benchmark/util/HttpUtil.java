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

    private static final int MAX_TOTAL = 3 * 10000;

    public static CloseableHttpClient getHttpClient() {
        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        cm.setMaxTotal(MAX_TOTAL);
        cm.setDefaultMaxPerRoute(MAX_TOTAL);
        cm.setDefaultMaxPerRoute(MAX_TOTAL);
        return HttpClients.custom().setConnectionManager(cm).build();
    }
}
