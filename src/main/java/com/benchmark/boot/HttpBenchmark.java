package com.benchmark.boot;

import com.benchmark.constant.RequestType;
import com.benchmark.http.HttpRequest;

/**
 * @author hejianglong
 * @date 2018/12/27.
 */
public class HttpBenchmark {

    public static HttpRequest form(String methodType, String requestUrl) {
        return new HttpRequest(methodType, requestUrl, RequestType.FORM);
    }

    public HttpRequest json(String methodType, String requestUrl) {
        return new HttpRequest(methodType, requestUrl, RequestType.JSON);
    }
}
