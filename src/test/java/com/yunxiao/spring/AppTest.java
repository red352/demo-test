package com.yunxiao.spring;


import cn.hutool.json.JSON;
import com.yunxiao.spring.core.util.JsonUtils;
import com.yunxiao.spring.model.RequestPo;
import com.yunxiao.spring.rest.RestService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@SpringBootTest
class AppTest {

    RestTemplate restTemplate = new RestTemplateBuilder().build();

    @Test
    void testRest() {
        RestService restService = new RestService(restTemplate);
        RequestPo requestPo = RequestPo
                .builder()
                .url("https://api.bilibili.com/x/space/wbi/acc/info?mid=1366330988")
                .method("GET")
                .headers(Map.of("User-Agent", List.of("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/93.0.4577.82 Safari/537.36")))
                .build();
        List<String> fields = List.of("data", "live_room", "liveStatus");
        JSON json = restService.doStringRest(requestPo).getJson();

        String jsonValue = JsonUtils.getJsonValue(json, fields);
        System.out.println(jsonValue);

    }



}
