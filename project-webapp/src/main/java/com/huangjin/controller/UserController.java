package com.huangjin.controller;


import com.huangjin.domain.User;
import com.huangjin.service.UserService;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    public List<User> listUser(User user, String username) {

        List<User> list = new ArrayList<>();

        try {
            list = userService.selectList(user);
        } catch (Exception e) {
            logger.error("查询用户出错", e);
        }

        return list;
    }
}