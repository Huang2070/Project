package com.huangjin.controller;

import com.huangjin.domain.User;
import com.huangjin.vo.ActivityVO;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by huangjin on 2016/7/14.
 */

@Controller
@RequestMapping("/view")
public class ViewController {

    private static Log log = LogFactory.getLog(RestfulController.class);

    //返回ModelAndView,可以用addObject传参
    @RequestMapping(value="/getView1")
    public ModelAndView testView1() {
        ModelAndView modelAndView = new ModelAndView("test1");
        modelAndView.addObject("username", "huangjin");
        return modelAndView;
    }

    //返回字符串,自动转换为资源
    @RequestMapping(value="/getView2")
    public String testView2(HttpServletRequest request, HttpServletResponse response) {
        String info = (String)request.getSession().getAttribute("test");
        log.info(info);
        return "printCookies";
    }

    //加上注解后返回字符串
    @ResponseBody
    @RequestMapping(value="getView3")
    public String testView3() {
        String message = "test1";
        return message;
    }

    //POST请求
    @RequestMapping(value="/getView4", method = RequestMethod.POST)
    public String testView4() {
        String message = "test1";
        return message;
    }

    //重定向
    @RequestMapping(value="/getView5")
    public String testView5() {
        return "redirect:/view/getView2";
    }

    //直接在Controller的方法中传入PrintWriter对象
    @RequestMapping(value="/getView6")
    public String testView6(PrintWriter out, @RequestParam String username) {
        out.println(username);
        return null;
    }

    //把表单自动注入bean
    @RequestMapping(value="/getView7")
    public String testView7(PrintWriter out, User user) {
        out.println(user.getId());
        out.println(user.getUsername());
        return null;
    }

    //通过map传递参数给页面
    @RequestMapping(value="/getView8")
    public ModelAndView testView8() {
        Map<String,Object> model = new HashMap();
        model.put("username", "huangjin");
        ModelAndView mav = new ModelAndView("test1", model);
        return mav;
    }

    //直接从参数中传参给页面
    @RequestMapping(value="getView9")
    public String testView9(@ModelAttribute("username") String username) {
        return "test1";
    }


    //用ModelMap传参
    @RequestMapping(value="getView10")
    public String testView10(ModelMap model) {
        model.addAttribute("username", "huangjin");
        model.put("id", 100);
        return "test1";
    }

    //使用RedirectView重定向
    @RequestMapping(value="getView11")
    public ModelAndView testView11() {
        RedirectView view = new RedirectView("/view/getView1");
        return new ModelAndView(view);
    }

    @RequestMapping(value="testPrint")
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

    @RequestMapping(value="testRequest")
    public void testRequest(HttpServletRequest request, HttpServletResponse response, ActivityVO activityVO) {
        try {
            request.setAttribute("activity", activityVO);
            request.getRequestDispatcher("/WEB-INF/jsp/zhuanpan.jsp").forward(request, response);
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


}
