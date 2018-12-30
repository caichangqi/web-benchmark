package com.benchmark.http;

import com.alibaba.fastjson.JSONObject;
import com.benchmark.constant.MethodType;
import com.benchmark.constant.RequestType;
import com.benchmark.util.HttpUtil;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author hejianglong
 * @date 2018/12/30 下午5:27
 * @email hejlong@163.com
 * @Desc
 */
public class PostRequest extends HttpRequest {

    private JSONObject body = new JSONObject();

    public PostRequest(MethodType methodType, String requestUrl, RequestType requestType) {
        super(methodType, requestUrl, requestType);
    }

    public static PostRequest postRequest(String requestUrl, RequestType requestType) {
        return new PostRequest(MethodType.POST, requestUrl, requestType);
    }

    @Override
    public void execute() throws InterruptedException {
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        super.executeDoPost(httpClient, this.body);
        System.out.println("并发请求结束..");
    }

    @Override
    public PostRequest withHeaders(String key, String value) {
        super.withHeaders(key, value);
        return this;
    }

    @Override
    public PostRequest witheParams(String key, String value) {
        super.witheParams(key, value);
        return this;
    }

    /**
     * 设置并发线程数量
     * @param threadCount
     * @return
     */
    @Override
    public PostRequest withThreadNum(int threadCount) {
        super.withThreadNum(threadCount);
        return this;
    }

    public PostRequest withBody(JSONObject object) {
        body = object;
        return this;
    }
}
