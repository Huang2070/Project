package com.huangjin.testio;

import java.awt.image.BufferedImage;

import com.huangjin.util.ImageUtil;
import com.huangjin.util.RetryUtil;
import org.apache.curator.retry.RetryNTimes;
import org.junit.Test;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:44 2019-07-29
 */
public class TestImageUtil {

    @Test
    public void test1() {

        String imageUrl = "http://g.hiphotos.baidu.com/image/h%3D300/sign=c029a987df09b3def4bfe268fcbe6cd3/5366d0160924ab18014cefd83bfae6cd7a890b82.jpg";

        String imageUrl2 = "http://liangcang-material-hd2.oss-cn-shanghai.aliyuncs.com/tmp/liangcang/20190727/watermark/293266133/logo_1564231849580.png";
        BufferedImage image = ImageUtil.readImgFromUrl(imageUrl2);

        //System.out.println(ImageUtil.getImageSize(image));
        System.out.println(111);
        RetryUtil.setRetryTimes(5).retry(TestImageUtil.class, "test1");
    }
}
