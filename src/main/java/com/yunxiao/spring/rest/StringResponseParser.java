package com.yunxiao.spring.rest;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONUtil;
import org.springframework.http.ResponseEntity;

/**
 * @author LuoYunXiao
 * @since 2023/10/19 22:43
 */
public record StringResponseParser(ResponseEntity<String> responseEntity) {

    public String getText() {
        return responseEntity.getBody();
    }

    // getJson
    public JSON getJson() {
        return JSONUtil.parse(getText());
    }


}
