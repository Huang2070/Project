package com.huangjin.controller;


import com.huangjin.domain.Aaa;
import com.huangjin.domain.Bbb;
import com.huangjin.domain.City;
import com.huangjin.domain.User;
import com.huangjin.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpRequest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/user"})
public class UserController {

    private static Log logger = LogFactory.getLog(RestfulController.class);

    @Resource(name = "userService")
    private UserService userService;


    @RequestMapping(value = "/hehe", method = RequestMethod.GET)
    public Map<?,?> test1() {

        Aaa aaa = new Aaa();

        List<User> list = new ArrayList<>();

        Map<Integer, User> map = new HashMap<>();

        Map<Integer, String> map2 = new HashMap<>();

        Map<User, User> map3 = new HashMap<>();

        Map<String, List<User>> map4 = new HashMap<>();



        User user = new User();
        user.setId(1);
        user.setUsername("123");
        user.setPassword("123");

        City city = new City();
        city.setId(12);
        city.setCountrycode("123");

        aaa.setCity(city);
        aaa.setUser(user);



        User user1 = new User();
        user1.setId(1);
        user1.setUsername("123");
        user1.setPassword("123");

        User user2 = new User();
        user2.setId(2);
        user2.setUsername("123");
        user2.setPassword("123");

        User user3 = new User();
        user3.setId(3);
        user3.setUsername("123");
        user3.setPassword("123");

        list.add(user1);
        list.add(user2);
        list.add(user3);


        map.put(1, user1);
        map.put(2, user1);
        map.put(3, user1);


        map2.put(1, "one");
        map2.put(2, "two");
        map2.put(3, "three");

        map3.put(user1, user2);

        map4.put("user", list);
        map4.put("user2", list);

        return map4;
    }


    @RequestMapping(value = "/haha", method = RequestMethod.GET)
    public Map<?,?> test2(User user, City city) {
        Map<String, String> map = new HashMap<>();
        map.put("user1", user.getUsername());
        map.put("city1", city.getName());


        return map;
    }

    @RequestMapping(value = "/hahaha", method = RequestMethod.GET)
    public Bbb test3(Bbb bbb) {
        //传递参数listStr=123,123,123, 可以接受到参数到list
        return bbb;
    }


    @RequestMapping(value = "/ha", method = RequestMethod.GET)
    public void test4(HttpServletRequest request) {
        String boo = request.getParameter("boo");

        System.out.println();
    }


}