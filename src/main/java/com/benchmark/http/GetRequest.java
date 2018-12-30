package com.benchmark.http;

import com.benchmark.constant.MethodType;
import com.benchmark.constant.RequestType;
import com.benchmark.util.HttpUtil;
import org.apache.http.impl.client.CloseableHttpClient;

/**
 * @author hejianglong
 * @date 2018/12/30 下午1:47
 * @email hejlong@163.com
 * @Desc
 */
public class GetRequest extends HttpRequest {

    public GetRequest(MethodType methodType,
                      String requestUrl,
                      RequestType requestType) {
        super(methodType, requestUrl, requestType);
    }

    public static GetRequest getRequest(String requestUrl, RequestType requestType) {
        return new GetRequest(MethodType.GET, requestUrl, requestType);
    }


    @Override
    public GetRequest withHeaders(String key, String value) {
        super.withHeaders(key, value);
        return this;
    }

    @Override
    public GetRequest witheParams(String key, String value) {
        super.witheParams(key, value);
        return this;
    }

    @Override
    public void execute() throws InterruptedException {
        CloseableHttpClient httpClient = HttpUtil.getHttpClient();
        super.executeDoGet(httpClient);
        System.out.println("请求结束");
    }

    @Override
    public GetRequest withThreadNum(int threadCount) {
        super.withThreadNum(threadCount);
        return this;
    }
}
