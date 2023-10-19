package com.yunxiao.spring.rest;

import com.yunxiao.spring.model.RequestPo;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * @author LuoYunXiao
 * @since 2023/10/19 22:06
 */
public class RestService {

    private final RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public StringResponseParser doStringRest(RequestPo po) {
        return new StringResponseParser(restTemplate.exchange(getRequestEntity(po), String.class));
    }

    private static RequestEntity<String> getRequestEntity(RequestPo po) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.putAll(po.getHeaders());
        return new RequestEntity<>(po.getBody(), httpHeaders, HttpMethod.valueOf(po.getMethod()), URI.create(po.getUrl()));
    }
}
