package com.yunxiao.spring;

import org.junit.jupiter.api.Test;

import java.util.Map;

/**
 * @author LuoYunXiao
 * @since 2023/10/18 11:39
 */
class JavaTest {

    @Test
    void test1() {
        Map<Object, Object> map = Map.of("",0);
        map.values().forEach(System.out::println);
    }

    @Test
    void test2() {

    }
}
