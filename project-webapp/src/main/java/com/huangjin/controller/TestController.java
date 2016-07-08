package com.huangjin.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/test")
public class TestController {

    private static Log log = LogFactory.getLog(TestController.class);

    @RequestMapping(value="/testMap/{str}", method = RequestMethod.GET)
    public Map<?,?> testString(@PathVariable String str) {

        log.info(str);
        str.length();
        Map<String,String> map = new HashMap<>();
        map.put("firstName", "huangjin");
        map.put("lastName", "jin");
        return map;
    }
}