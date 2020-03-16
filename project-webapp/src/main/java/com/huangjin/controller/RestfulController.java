package com.huangjin.controller;

import com.huangjin.util.IOUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

@RestController
@RequestMapping("/restful")
public class RestfulController {

    private static Log log = LogFactory.getLog(RestfulController.class);

    @RequestMapping(value="/testMap/{str}", method = RequestMethod.GET)
    public Map<?,?> testString(@PathVariable String str) {

        log.info(str);
        str.length();
        Map<String,String> map = new HashMap<>();
        map.put("firstName", "huangjin");
        map.put("lastName", "jin");
        return map;
    }


    @RequestMapping(value="/testPrint")
    public void testPrint(HttpServletRequest request, HttpServletResponse response) {
        PrintWriter writer = null;
        try {
            writer = response.getWriter();
            writer.print("huangjin");

        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            if (null != writer) {
                writer.close();
            }
        }
    }

    @RequestMapping(value="/testString")
    public String testString(String str1, String str2) {
        return "test1";
    }

    @RequestMapping(value="/testInt")
    public void testInt(int i) {
        System.out.println(i);
    }
}