package com.benchmark.http;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hejianglong
 * @date 2018/12/27.
 */
public class HttpRequestHeader {

    private Map<String, String> headers = new HashMap<>();

    public void add(String key, String value) {
        headers.put(key, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
