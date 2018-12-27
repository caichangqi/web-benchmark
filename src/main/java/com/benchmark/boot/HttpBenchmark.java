package com.benchmark.boot;

import com.benchmark.constant.RequestType;
import com.benchmark.http.HttpRequest;

/**
 * @author hejianglong
 * @date 2018/12/27.
 */
public class HttpBenchmark {

    public static HttpRequest form() {
        return new HttpRequest(RequestType.FORM);
    }

    public static HttpRequest json() {
        return new HttpRequest(RequestType.JSON);
    }
}
