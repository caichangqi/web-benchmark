/*
 * Project: com.benchmark.http
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
package com.benchmark.http;

/**
 * @author hejianglong
 * @date 2018/12/27 下午9:08
 * @email hejlong@163.com
 * @Desc
 */
public class HttpBody {

    private String jsonString = null;

    public void setJsonData(String jsonData) {
        this.jsonString = jsonData;
    }

    public String getJsonString() {
        return jsonString;
    }
}
