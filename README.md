# benchmark
    http benchmark, 基于JAVA简单简洁的压力测试工具

# 参数说明
    threadNum: 并发的线程数量, 不填写默认为1
    其它的参数就不多说了大家应该很熟悉
    
# 使用方式
1. JSON, GET 

            getRequest("http://localhost:8888/api/test1", RequestType.JSON)
                .witheParams("name", "long")
                .witheParams("age", "abiao")
                .withHeaders("clientType", "ios")
                .withThreadNum(100)
                .execute();
2. JSON, POST

            postRequest("http://localhost:8888/api/test3", RequestType.JSON)
                .witheParams("name", "long")
                .witheParams("age", "abiao")
                .withHeaders("clientType", "ios")
                .withBody(getJSONObject())
                .withThreadNum(200)
                .execute();
            
            private JSONObject getJSONObject() {
                JSONObject object = new JSONObject();
                object.fluentPut("id", "11").fluentPut("name", "abcd");
                return object;
            }

    ##### FORM的用法除了RequestType不同其它用法一样
3. FORM, GET

        postRequest("http://localhost:8888/api/test3", RequestType.FORM)
       
4. FORM, POST  
        
        postRequest("http://localhost:8888/api/test3", RequestType.FORM)

# 待测试的功能
    FORM 方式 GET, POST
    
# 待更新的功能
    1. 完善多个请求方式FORM, JSON等, 以及方法类型PUT, DELETE
    2. 日志输出调整
    3. 报表及关键信息统计及展示(耗时, 成功失败率等等等等)
    4. QPS测试
    5. 稳定在一段时间以相同的并发量或者QPS进行测试
    6. 数据库SQL吞吐量测试
