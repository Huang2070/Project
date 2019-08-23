package com.huangjin.util;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

import javax.imageio.ImageIO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 11:04 2019-07-29
 */
public class ImageUtil {


    private static final String FONT2 = "http://yunvideo.cn-hangzhou.oss.aliyun-inc.com/font/FZSXSLKJW.ttf";
    private static final String FONT2D = "/home/admin/logs/FZSXSLKJW.ttf";

    private static final String FONT1 = "http://yunvideo.cn-hangzhou.oss.aliyun-inc.com/font/FZZZHUNHJW.ttf";
    private static final String FONT1D = "/home/admin/logs/FZZZHUNHJW.ttf";

    private static final String FONT3 = "http://dandelion-video.oss-cn-beijing.aliyuncs.com/msyhbd.ttf";
    private static final String FONT3D = "/home/admin/logs/msyhbd.ttf";

    private static final String FONT_DEFAULT_OSS = "http://yunvideo.cn-hangzhou.oss.aliyun-inc.com/font/FangZhengLanTingZhongHei_GBK.ttf";
    private static final String FONT_DEFAULT_LOCAL = "/home/admin/logs/FangZhengLanTingZhongHei_GBK.ttf";

    private static final Logger logger = LoggerFactory.getLogger(ImageUtil.class);
    private static Integer SYNC = 1;
    private static int INIT = 0;

    private static final String FONT2_NAME = "方正苏新诗柳楷简体";
    private static final String FONT1_NAME = "方正正准黑简体";
    private static final String FONT_DEFAULT_NAME = "方正兰亭中黑_GBK";
    private static final String FONT3_NAME = "微软雅黑";

    /**
     * 创建图片
     *
     * @param x
     * @param y
     * @param imageObjectList
     * @return
     */
    @SuppressWarnings("unused")
    public static BufferedImage createImage(int x, int y, List<ImageObject> imageObjectList) {

        /* 初始化字体 */
        initFont();

        // 创建空白透明图
        BufferedImage bigImg = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
        final Graphics2D g1 = bigImg.createGraphics();
        bigImg = g1.getDeviceConfiguration().createCompatibleImage(bigImg.getWidth(), bigImg.getHeight(),
            Transparency.TRANSLUCENT);
        g1.dispose();
        final Graphics2D g = bigImg.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_LCD_HRGB);
        // 压入图片元素
        imageObjectList.forEach(imageObject -> {
            if (imageObject.getType().equals(0)) {

                BufferedImage img = imageObject.getImage();
                if(img == null) {
                    // 下载logo图片 重试2次
                    int retryCount = 3;
                    while (--retryCount > 0 && img == null) {
                        img = readImgFromUrl(imageObject.getImageUrl());
                    }
                }
                if (img == null) {
                    return;
                }
                Integer imageX = imageObject.getX();
                Integer imageY = imageObject.getY();
                Integer imgW = img.getWidth();
                Integer imgH = img.getHeight();
                if (imageX < 0) {
                    imageX = x + imageX - imgW;
                }
                if (imageY < 0) {
                    imageY = y + imageY - imgH;
                }
                g.drawImage(img, imageX, imageY, null);

            } else if (imageObject.getType().equals(1)) {
                // 文字
                String str = imageObject.getText();
                Integer fontSize = imageObject.getTextFontSize();
                g.setColor(Color.white);
                String fontString = FONT1_NAME;
                if (imageObject.getTextStyle().equals(2)) {
                    fontString = FONT2_NAME;
                    fontSize += 6;
                }
                if (imageObject.getTextStyle().equals(3)) {
                    fontString = FONT3_NAME;
                }

                fontString = StrUtil.isGB2312Compatible(str) ? fontString : FONT_DEFAULT_NAME;

                Integer textFontStype = imageObject.getTextFontStype();

                Font font = new Font(fontString, textFontStype, fontSize);
                g.setFont(font);
                int textX = imageObject.getX();
                int textY = imageObject.getY();
                int maxX = x * 9 / 10;
                int maxY = y  * 9 / 10;
                int fontHeight = g.getFontMetrics(font).getHeight(); // 字体高度
                int height = fontHeight - 5;// 字符高度。缩小点间距.
                int fontWidthCN = g.getFontMetrics(font).stringWidth("酷"); // 中文字符的宽度。
                int width = fontWidthCN + 15; // 字符宽度。也是列宽。 增大点。
                if (imageObject.getTextType() == 1) { // 竖行文字
                    int j = -1;
                    int k = str.length();

                    while (++j < k) {
                        char ch = str.charAt(j); // 当前字符
                        // 换行符则直接换行
                        if ('\n' == ch) {
                            textX += width;
                            textY = imageObject.getY();
                            continue;
                        }
                        // 以数字或字母开启一个数字
                        if (StrUtil.isDigitOrEnglishCharacter(ch)) {
                            StringBuffer sb = new StringBuffer();
                            sb.append(ch);
                            if (j + 1 < k) {
                                ch = str.charAt(j + 1); // 下一个字符.
                                while (j + 1  < k && StrUtil.isVisibleAscii(ch)) {
                                    sb.append(ch);
                                    j ++;
                                    // 下一个字符是字母或数字，但是当前字符不是字母或数字，认为发现一个单词分隔点。 标点等符号不出现在单词开头或行开头。
                                    // 当前单词结束。
                                    if (j + 1 >= k || !StrUtil.isDigitOrEnglishCharacter(ch) && StrUtil.isDigitOrEnglishCharacter(str.charAt(j + 1))) {
                                        break;
                                    }
                                    ch = str.charAt(j + 1); // 下一个字符.

                                }
                            }
                            if (textY + g.getFontMetrics(font).stringWidth(sb.toString()) >= maxY) { // 换行
                                textX += width;
                                textY = imageObject.getY();
                            }

                            // 调整纵向横排的位置
                            textY -=  height * 3 / 4;
                            textX -=  (fontWidthCN - fontHeight) / 2;

                            g.rotate(Math.PI / 2, textX, textY);
                            g.drawString(sb.toString(), textX, textY);
                            g.rotate(Math.PI * 3 / 2, textX, textY);
                            textY += height * 3 / 4;
                            textY += g.getFontMetrics(font).stringWidth(sb.toString());
                            textX +=  (fontWidthCN - height) / 2;
                        } else {
                            if (textY + fontWidthCN >= maxY) { // 换行
                                textX += width;
                                textY = imageObject.getY();
                            }

                            // 行首忽略空格。
                            if (ch != 32 ||  !imageObject.getY().equals(textY)) {
                                g.drawString("" + ch, textX, textY);
                                textY += ch == 32 ? height / 2 : height;
                            }
                            // // 冒号，且剩余字符较长，则另起一行。
                            if ((ch == ':' || ch == '：') && k - j > 7) {
                                textX += width;
                                textY = imageObject.getY();
                            }
                        }
                    }
                } else if (str != null) { // 横排文字
                    String[] lines = str.split("\n");
                    for (int i = 0; i < lines.length; i ++) {
                        g.drawString(lines[i], textX, textY);
                        textY += height;
                    }
                }
            }
        });
        g.dispose();

        logger.info("create image success");
        return bigImg;
    }

    /**
     * 读取网络图片
     *
     * @param img
     * @return
     */
    public static BufferedImage readImgFromUrl(String img) {
        /* */
        URL url = null;
        BufferedImage smallImg = null;
        try {
            url = new URL(img);

            HttpURLConnection httpUrl = null;
            httpUrl = (HttpURLConnection)url.openConnection();
            httpUrl.connect();
            httpUrl.getInputStream();

            smallImg = ImageIO.read(httpUrl.getInputStream());
        } catch (Exception e) {
            return null;
        }

        return smallImg;
    }


    public static Integer getImageSize(BufferedImage image) {
        ByteArrayOutputStream out =new ByteArrayOutputStream();
        try {
            //png 为要保存的图片格式
            ImageIO.write(image,"png", out);

            byte[] barray = out.toByteArray();
            return barray.length;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }

    /**
     *
     * 初始化字体
     */
    private static void initFont() {
        /* 字体初始化 */
        if (INIT == 1) {
            return;
        }
        synchronized (SYNC) {
            if (INIT == 1) {
                return;
            }
            try {
                File f = new File(FONT1D);
                if (!f.exists()) {
                    AliDownUtil.download(FONT1, f);
                }
                File f2 = new File(FONT2D);
                if (!f2.exists()) {
                    AliDownUtil.download(FONT2, f2);
                }

                File f3 = new File(FONT3D);
                if (!f3.exists()) {
                    AliDownUtil.download(FONT3, f3);
                }

                File defaultFontFile = new File(FONT_DEFAULT_LOCAL);
                if (!defaultFontFile.exists()) {
                    AliDownUtil.download(FONT_DEFAULT_OSS, defaultFontFile);
                }

                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();

                Font newFont = Font.createFont(Font.TRUETYPE_FONT, f);
                ge.registerFont(newFont);

                Font newFont2 = Font.createFont(Font.TRUETYPE_FONT, f2);
                ge.registerFont(newFont2);

                Font newFont3 = Font.createFont(Font.TRUETYPE_FONT, f3);
                ge.registerFont(newFont3);

                Font fontDefault = Font.createFont(Font.TRUETYPE_FONT, defaultFontFile);
                ge.registerFont(fontDefault);

                INIT = 1;

                logger.info("load font success:" + newFont.getFontName(new Locale("zh", "CN")) + " : "
                    + newFont2.getFontName(new Locale("zh", "CN")));

            } catch (Exception e) {
                logger.error("Load Font error!", e);
            }
        }

    }






    public static class ImageObject {
        /**
         * 宽高，如果为负值，则为相对右边和下边的值
         */
        private Integer x;
        private Integer y;
        /**
         * 类型，0图片，1文字
         */
        private Integer type;

        private String imageUrl;

        private BufferedImage image;

        private String text;

        /**
         * 文字类型，0横版，1竖版
         */
        private Integer textType = 1;
        /**
         * 文字风格，1现代，2古代
         */
        private Integer textStyle = 1;

        private Integer textFontSize = 60;

        private Integer textFontStype = Font.PLAIN;

        public Integer getX() {
            return x;
        }

        public void setX(Integer x) {
            this.x = x;
        }

        public Integer getY() {
            return y;
        }

        public void setY(Integer y) {
            this.y = y;
        }

        public Integer getType() {
            return type;
        }

        public void setType(Integer type) {
            this.type = type;
        }

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public Integer getTextType() {
            return textType;
        }

        public void setTextType(Integer textType) {
            this.textType = textType;
        }

        public Integer getTextStyle() {
            return textStyle;
        }

        public void setTextStyle(Integer textStyle) {
            this.textStyle = textStyle;
        }

        public Integer getTextFontSize() {
            return textFontSize;
        }

        public void setTextFontSize(Integer textFontSize) {
            this.textFontSize = textFontSize;
        }

        public Integer getTextFontStype() {
            return textFontStype;
        }

        public void setTextFontStype(Integer textFontStype) {
            this.textFontStype = textFontStype;
        }

        public BufferedImage getImage() {
            return image;
        }

        public void setImage(BufferedImage image) {
            this.image = image;
        }
    }
}
