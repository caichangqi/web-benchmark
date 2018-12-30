package com.benchmark.http;

import com.benchmark.constant.RequestType;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hejianglong
 * @date 2018/12/27.
 */
public class HttpRequestHeader {

    private Map<String, String> headers = new HashMap<>();

    public HttpRequestHeader(RequestType requestType) {
        if (requestType == RequestType.FORM) {
            headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
        } else {
            headers.put("Content-Type", "application/json");
        }
    }

    public HttpRequestHeader add(String key, String value) {
        headers.put(key, value);
        return this;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
