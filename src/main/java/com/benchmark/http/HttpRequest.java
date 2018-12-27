package com.benchmark.http;

import com.alibaba.fastjson.JSONObject;
import java.util.Map;

/**
 * @author hejianglong
 * @date 2018/12/27.
 */
public class HttpRequest {

    private String requestType;

    private String requestUrl;

    private String methodType;

    private HttpRequestHeader httpRequestHeader;

    private HttpBody httpBody;

    private Handler handler;

    public HttpRequest(String methodType, String requestUrl, String requestType) {
        this.requestType = requestType;
        this.requestUrl = requestUrl;
        this.methodType = methodType;
        httpRequestHeader = new HttpRequestHeader();
        httpBody = new HttpBody();
        handler = new Handler();
    }

    public HttpRequest withHeaders(String key, String value) {
        httpRequestHeader.add(key, value);
        return this;
    }

    public Handler withBody(Map<String, Object> objectMap) {
        httpBody.setJsonData(JSONObject.toJSONString(objectMap));
        return handler.setHttpInfo(this);
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public HttpRequestHeader getHttpRequestHeader() {
        return httpRequestHeader;
    }

    public void setHttpRequestHeader(HttpRequestHeader httpRequestHeader) {
        this.httpRequestHeader = httpRequestHeader;
    }

    public HttpBody getHttpBody() {
        return httpBody;
    }

    public void setHttpBody(HttpBody httpBody) {
        this.httpBody = httpBody;
    }
}
