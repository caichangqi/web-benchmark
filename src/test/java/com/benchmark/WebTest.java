package com.benchmark;

import com.alibaba.fastjson.JSONObject;
import com.benchmark.constant.RequestType;
import org.junit.Test;
import static com.benchmark.http.GetRequest.getRequest;
import static com.benchmark.http.PostRequest.postRequest;

/**
 * @author hejianglong
 * @date 2018/12/27 下午8:29
 * @email hejlong@163.com
 * @Desc
 */
public class WebTest {

    @Test
    public void postJSONTest() throws InterruptedException {
        // 压测试数量 threadNum
        postRequest("http://localhost:8888/api/test3", RequestType.JSON)
            .witheParams("name", "long")
            .witheParams("age", "abiao")
            .withHeaders("clientType", "ios")
            .withBody(getJSONObject())
            .withThreadNum(200)
            .execute();
    }

    @Test
    public void getJSONTest() throws InterruptedException {
        getRequest("http://localhost:8888/api/test1", RequestType.JSON)
            .witheParams("name", "long")
            .witheParams("age", "abiao")
            .withHeaders("clientType", "ios")
            .withThreadNum(100)
            .execute();
    }

    @Test
    public void postFormTest() throws InterruptedException {
        // 压测试数量 threadNum
        postRequest("http://localhost:8888/api/test3", RequestType.FORM)
            .witheParams("name", "long")
            .witheParams("age", "abiao")
            .withHeaders("clientType", "ios")
            .withBody(getJSONObject())
            .withThreadNum(200)
            .execute();
    }

    @Test
    public void getFormTest() throws InterruptedException {
        getRequest("http://localhost:8888/api/test1", RequestType.FORM)
            .witheParams("name", "long")
            .witheParams("age", "abiao")
            .withHeaders("clientType", "ios")
            .withThreadNum(100)
            .execute();
    }

    private JSONObject getJSONObject() {
        JSONObject object = new JSONObject();
        object.fluentPut("id", "11").fluentPut("name", "abcd");
        return object;
    }
}
