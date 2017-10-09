package com.huangjin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
import java.io.File;
import java.util.*;


/**
 * @author 黄金(huangjin)
 * @since 2017/8/25 18:38.
 */
@Controller
public class UpLoadController {
    @ResponseBody
    @RequestMapping("/uploadAndSaveImg")
    public Map<?,?> uploadAndSaveImg(@RequestParam("imgFile") MultipartFile[] files, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        try {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            MultipartFile multipartFile = multipartRequest.getFile("imgFile");
            int i = 1;

            for(MultipartFile file : files) {
                System.out.println(file.getOriginalFilename());
                // 判断上传文件类型
                String fileName = file.getOriginalFilename();
                String type = file.getContentType();

                Map<String, Object> mapItem = new HashMap<>();

                mapItem.put("fileName", fileName);
                mapItem.put("type", type);
                mapItem.put("size", file.getSize());

                map.put("file" + i, mapItem);
                i++;
            }



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
//            if (tmpFile != null) {
//                tmpFile.delete();
//            }
//            if (dir != null) {
//                dir.delete();
//            }
        }

        return map;
    }

}
