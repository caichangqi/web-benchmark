/*
 * Project: com.benchmark
 * 
 * File Created at 2018/12/27
 * 
 * Copyright 2018 CMCC Corporation Limited.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * ZYHY Company. ("Confidential Information").  You shall not
 * disclose such Confidential Information and shall use it only in
 * accordance with the terms of the license.
 */
package com.benchmark;

import com.benchmark.boot.HttpBenchmark;
import com.benchmark.constant.MethodType;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

/**
 * @author hejianglong
 * @date 2018/12/27 下午8:29
 * @email hejlong@163.com
 * @Desc
 */
public class WebTest {

    @Test
    public void test() {
        String requestUrl = "";
        Map<String, Object> data = new HashMap<>();

        HttpBenchmark
            .form(MethodType.GET, requestUrl)
            .withHeaders("clientType", "ANDROID")
            .withBody(data)
            .done();
    }
}
