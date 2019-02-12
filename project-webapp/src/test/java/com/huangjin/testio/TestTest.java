package com.huangjin.testio;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.huangjin.domain.Aaa;
import com.huangjin.domain.User;
import com.huangjin.util.TimeUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.springframework.util.AntPathMatcher;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by huang on 2016-11-8.
 */
public class TestTest {
    public static void main(String[] args) {
        try {
            BufferedInputStream in = new BufferedInputStream(System.in);

            byte[] bytes = new byte[10];
            int bytesReads = in.read(bytes);

            while(bytesReads != -1) {
                String str = new String(bytes);
                if(str.substring(0, 4).equals("exit")) {
                    System.exit(0);
                } else {
                    System.out.println(str.substring(0, bytesReads));
                }

                bytesReads = in.read(bytes);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void testFileReader() {
        FileReader f = null;
        try {
            StringBuffer str = new StringBuffer();
            char[] buf = new char[1024];
            f = new FileReader("C:\\Users\\huang\\Desktop\\sdf.txt");
            while(f.read(buf) > 0) {
                str.append(buf);
            }
            str.toString();
            System.out.println(str);
            f.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }



    @Test
    public void testFilter() {
        PrintStream ps = null;
        try {
            FileOutputStream fos = new FileOutputStream("C:\\Users\\huang\\Desktop\\sdf.txt", true);
            ps = new PrintStream(fos);
            ps.println("123");
            ps.println(new TestTest());
            ps.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testFileWriter() {
        try {
            //打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
            FileWriter writer = new FileWriter("C:\\Users\\huang\\Desktop\\sdf.txt", true);
            writer.write("321");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testBasic() throws IOException {
        File file = new File("C:\\Users\\huang\\Desktop\\sdf.txt");
        File tmpFile = new File("C:\\Users\\huang\\Desktop\\ddd.txt");
        FileUtils.copyFile(file, tmpFile); //生成临时文件

        System.out.println(file.getName());
    }


    @Test
    public void testTime() {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.set(2018, 2, 1, 1, 1, 1);
        long start = calendarStart.getTimeInMillis();

        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.set(2018, 3, 1, 1, 1, 1);
        long end = calendarEnd.getTimeInMillis();

        System.out.println(start);
        System.out.println(end);
    }

    @Test
    public void testJoin() {
        System.out.println(Joiner.on(",").join(Lists.newArrayList(0)) + ",0");
    }

    @Test
    public void test() {
        Map<Long, Long> testMap = Maps.newHashMap();
        testMap.put(1L, 2L);
        testMap.put(3L, 4l);
        List<Long> editGoodsIds = Lists.newArrayList();
        for(Map.Entry<Long, Long> entry : testMap.entrySet()) {
            editGoodsIds.add(entry.getValue());
        }
        System.out.println(editGoodsIds);
    }

    @Test
    public void testStream() {
        List<User> userList = Lists.newArrayList();
        User user1 = new User("huangjin", "1");
        User user2 = new User("huangjin", "2");
        User user3 = new User("huangjin", "3");
        User user4 = new User("lmf", "4");
        User user5 = new User("lmf", "5");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        Map<String, List<User>> userMap = userList.stream().collect(Collectors.groupingBy(User::getUsername));
        System.out.println(userMap);

        List<String> passwordList = Lists.newArrayList();
        passwordList.addAll(userList.stream().map(User::getPassword).collect(Collectors.toList()));
        System.out.println(passwordList);
    }

    @Test
    public void testJsoup() {
        String html = "<!DOCTYPE html>\n" +
                "<html lang=\"zh-CN\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n" +
                "    <title></title>\n" +
                "</head>\n" +
                "    <body>\n" +
                "        <div>\n" +
                "            <form name=\"itemForm\" id=\"itemForm\" method=\"post\" action=\"/uploadAndSaveImg\" enctype=\"multipart/form-data\">\n" +
                "                <input name=\"imgFile\" type=\"file\" multiple=\"multiple\">\n" +
                "                <input type=\"submit\" value=\"确定\">\n" +
                "                <p>\n" +
                "                    <video src=\"/i/movie.ogg\" controls=\"controls\">\n" +
                "                        your browser does not support the video tag\n" +
                "                    </video>\n" +
                "\n" +
                "                    <video src=\"/i/movie.hehe\" controls=\"controls\">\n" +
                "                        your browser does not support the video tag\n" +
                "                    </video>\n" +
                "                </p>\n" +
                "            </form>\n" +
                "        </div>\n" +
                "    </body>\n" +
                "</html>";


        Document doc = Jsoup.parse(html);
        Elements videoElements = doc.select("image").remove();
        for(Element element : videoElements) {
            String src = element.attr("src");
            System.out.println(src);
        }
    }

    @Test
    public void test12() {
        List<Long> longList = Lists.newArrayList();
        Object obj = "1";
        longList.add((Long)obj);
        System.out.println(longList);
    }

    @Test
    public void test13() {
        Aaa aaa = new Aaa();
        User use = aaa.getUser();
        System.out.println(use.getPassword());
    }

    @Test
    public void test14() {
        User user1 = new User();
        user1.setUsername("huangjin");
        user1.setPassword("1");

        User user2 = new User();
        user2.setUsername("huangjin");
        user1.setPassword("2");

        User user3 = new User();
        user3.setUsername("liumingfang");
        user1.setPassword("3");

        Set<User> sets = Sets.newHashSet();
        sets.add(user1);
        sets.add(user2);
        sets.add(user3);

        System.out.println(sets.size());
        for(User user : sets) {
            System.out.println(user.getUsername());
        }
    }

    @Test
    public void test15() {
        Set<Integer> sets = Sets.newHashSet();
        System.out.println(sets.add(1));
        System.out.println(sets.add(1));
    }

    @Test
    public void test16() {
        Map<Long, Object> map = Maps.newHashMap();
        map.put(1L, "1");
        String result = (String)map.get(2L);
        System.out.println(result);
    }

    @Test
    public void test17() {
        try {
            String filePath = this.getClass().getResource("/offshelf.txt").getPath();
            List<String> lines = this.readFile(filePath);
            for(String str : lines) {
                System.out.println(str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void test18() {
        String filePath = this.getClass().getResource("/").getPath();
        System.out.println(filePath);
    }

    @Test
    public void test19() {
        PrintStream psError = null;
        PrintStream psInfo = null;

        try {
            String sourcesPath = this.getClass().getResource("/").getPath();

            FileOutputStream fosError = new FileOutputStream(sourcesPath + "errorLog.log", true); //错误日志文件
            FileOutputStream fosInfo = new FileOutputStream(sourcesPath + "runLog.log", true); //日志文件

            psError = new PrintStream(fosError);
            psInfo = new PrintStream(fosInfo);

            psError.println("123");
            psInfo.println("123");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            psError.close();
            psInfo.close();
        }
    }

    @Test
    public void test20() {
        User use = new User();
        System.out.println(use.getId() == 0);
    }

    @Test
    public void test21() {
        List<Integer> list1 = Lists.newArrayList(1,2,3,4,5);

        List<Integer> list2 = Lists.newArrayList(1,2,3,4,5);

        list1.removeAll(list2);

        System.out.println(list1);
        System.out.println(list2);
    }

    @Test
    public void test22() {
        List<User> users = Lists.newArrayList();
        User user1 = new User("1", "2");
        User user2 = new User("3", "4");
        users.add(user1);
        users.add(user2);

        Map<String, User> userMap = Maps.uniqueIndex(users, new Function<User, String>() {
            @Override
            public String apply(User user) {
                return user.getUsername();
            }
        });

        for(Map.Entry<String, User> entry : userMap.entrySet()) {
            System.out.println(entry.getKey());
        }
    }

    @Test
    public void test23() {
        String a = "1";
        String b = "2";
        Set<String> aaa = Sets.newHashSet();
        System.out.println(aaa.add(a));
        System.out.println(aaa.add(b));
        System.out.println(aaa.add(a));
    }

    @Test
    public void test24() {
        long timeMills = System.currentTimeMillis();
        System.out.println(String.valueOf(timeMills) + RandomUtils.nextInt(1, 1000));
        System.out.println(RandomUtils.nextInt(1, 1000));

    }

    @Test
    public void test25() {
        String s1 = new String("aaa");
        String s2 = "aaa";
        System.out.println(s1 == s2);    // false

        s1 = new String("bbb").intern();
        s2 = "bbb";
        System.out.println(s1 == s2);    // true

        s1 = "ccc";
        s2 = "ccc";
        System.out.println(s1 == s2);    // true

        s1 = new String("ddd").intern();
        s2 = new String("ddd").intern();
        System.out.println(s1 == s2);    // true

        s1 = "ab" + "cd";
        s2 = "abcd";
        System.out.println(s1 == s2);    // true

        String temp = "hh";
        s1 = "a" + temp;
        // 如果调用s1.intern 则最终返回true
        s2 = "ahh";
        System.out.println(s1 == s2);    // false

        temp = "hh".intern();
        s1 = "a" + temp;
        s2 = "ahh";
        System.out.println(s1 == s2);    // false

        temp = "hh".intern();
        s1 = ("a" + temp).intern();
        s2 = "ahh";
        System.out.println(s1 == s2);    // true

        s1 = new String("1");    // 同时会生成堆中的对象 以及常量池中1的对象，但是此时s1是指向堆中的对象的
        s1.intern();            // 常量池中的已经存在
        s2 = "1";
        System.out.println(s1 == s2);    // false

        String s3 = new String("1") + new String("1");    // 此时生成了四个对象 常量池中的"1" + 2个堆中的"1" + s3指向的堆中的对象（注此时常量池不会生成"11"）
        s3.intern();    // jdk1.7之后，常量池不仅仅可以存储对象，还可以存储对象的引用，会直接将s3的地址存储在常量池
        String s4 = "11";    // jdk1.7之后，常量池中的地址其实就是s3的地址
        System.out.println(s3 == s4); // jdk1.7之前false， jdk1.7之后true

        s3 = new String("2") + new String("2");
        s4 = "22";        // 常量池中不存在22，所以会新开辟一个存储22对象的常量池地址
        s3.intern();    // 常量池22的地址和s3的地址不同
        System.out.println(s3 == s4); // false

        // 对于什么时候会在常量池存储字符串对象，我想我们可以基本得出结论: 1. 显示调用String的intern方法的时候; 2. 直接声明字符串字面常量的时候，例如: String a = "aaa";
        // 3. 字符串直接常量相加的时候，例如: String c = "aa" + "bb";  其中的aa/bb只要有任何一个不是字符串字面常量形式，都不会在常量池生成"aabb". 且此时jvm做了优化，不会同时生成"aa"和"bb"在字符串常量池中
    }

    @Test
    public void test26() {
        String str = "huangjin111";
        for (int i = str.length(); --i>=0;){
            if (!Character.isDigit(str.charAt(i))){
                System.out.println(false);
            }
        }
    }

    @Test
    public void test27() {
        Set<Integer> sets = Sets.newHashSet(1,2,3,4,5,6,7,8,9,0);

        List<List<Integer>> list = Lists.partition(Lists.newArrayList(sets), 10);

        System.out.println(list);

    }


    @Test
    public void test28() {
        List<User> users = Lists.newArrayList();
        User user1 = new User("huangjin", "1");
        User user2 = new User("huangjin", "2");
        User user3 = new User("huangjin", "3");
        User user4 = new User("lmf", "4");
        User user5 = new User("lmf", "5");
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        users.add(user5);

        for(User user : users) {
            user.setUsername("hehe");
        }

        for(User user : users) {
            System.out.println(user.toString());
        }
    }

    @Test
    public void test29() {
        Map<String, Integer> map = Maps.newHashMap();
        map.put("1-1", 1);
        map.put("1-2", 1);
        map.put("2-1", 2);
        map.put("3-1", 3);

        List<Integer> list = Lists.newArrayList(map.values());
        Set<Integer> set = Sets.newHashSet(map.values());
        System.out.println(list);
        System.out.println(set);
    }


    @Test
    public void test31() {
        Object ddd = 2018435.0;

        System.out.println(Math.round(Double.parseDouble(ddd.toString())));
    }

    @Test
    public void test33() {
        String url = "/inperoid/query/**";
        String matchUrl = "/inperoid/query/";

        AntPathMatcher pathMatcher = new AntPathMatcher();
        System.out.println(pathMatcher.match(url, matchUrl));
    }

    @Test
    public void test35() {
        int threads = Math.min(Runtime.getRuntime().availableProcessors() * 2, 10);
        System.out.println(Runtime.getRuntime().availableProcessors());
        System.out.println(threads);
    }

    @Test
    public void test37() throws IOException {
        List<String> list = this.readFile("C:\\Users\\huang\\Downloads\\bbb.sql");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\ddd.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);
        for(String str : list) {
            String goodsId = str.substring(0, str.indexOf("\t"));
            String title = str.substring(str.indexOf("\t") + 1);

            String udpateSql = "update tb_goods_label_edit set invoice_title = '" + title + "' where edit_goods_id = " + goodsId + ";";
            bw.write(udpateSql);
            bw.newLine();
        }

        bw.close();
        fw.close();
    }


    /**
     * 逐行读取文件
     * @param path
     * @return
     * @throws IOException
     */
    private static List<String> readFile(String path) throws IOException {
        List<String> list = Lists.newArrayList();
        FileInputStream fis = new FileInputStream(path);
        //防止路径乱码,如果utf-8乱码,改GBK
        InputStreamReader isr = new InputStreamReader(fis, "UTF-8");
        BufferedReader br = new BufferedReader(isr);
        String line = "";
        while ((line = br.readLine()) != null) {
            list.add(line);
        }
        br.close();
        isr.close();
        fis.close();
        return list;
    }

    /**
     * Lists.newArrayList如果参数是null, 则会生成一个包含null的list, 而不是一个空list
     */
    @Test
    public void test39() {
        Map<Integer, Long> map = Maps.newHashMap();
        long l = map.get(123);

        List list = Lists.newArrayList(l);
        List list2 = Arrays.asList(l);
        System.out.println(list);
    }

    /**
     * new一个对象, 不会new里面的对象
     */
    @Test
    public void test41() {
        User use = new User();
        System.out.println(use);
    }

    /**
     * 计算Date类型时间差(天数)
     * @throws ParseException
     */
    @Test
    public void test43() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse("2018-06-06 11:30:24");
        Long offShelfDays = (new Date().getTime() - time.getTime()) / (1000 * 60 * 60 * 24);
        System.out.println(offShelfDays);

    }


    @Test
    public void test44() {
        boolean result = TimeUtil.isValidDate("2019-01-19 12:00:00", "yyyy-MM-dd HH:mm:ss");
        System.out.println(result);
    }

    @Test
    public void test45() {
        String title = "考拉测试商品,请勿购买,性能测试";
        if(title.indexOf("考拉测试商品") != -1) {
            System.out.println(true);
        }

        System.out.println(title.indexOf("aaa"));
    }

    @Test
    public void test47() throws IOException {
        List<String> list = this.readFile("C:\\Users\\huang\\Downloads\\1.txt");
        FileWriter fw = new FileWriter("C:\\Users\\huang\\Downloads\\2.sql", true);
        BufferedWriter bw = new BufferedWriter(fw);

        String sql = "insert into tb_forbid_rule (id, supplier_id, brand_id, category_id, edit_goods_id, forbid_reason, status, type, operator) values ";
        bw.write(sql);
        bw.newLine();
        for(String str : list) {
            String insert = "(seq," + str + ",";
            insert = insert + "0,0,0,'贸易模式不合规人工禁发',3,1,'operator'),";
            bw.write(insert);
            bw.newLine();
        }
        bw.close();
        fw.close();
    }

    @Test
    public void test49() {
        String str = "1,2,3,4,5";

        List<String> strList = Arrays.asList(str);

        System.out.println(strList);
    }

    @Test
    public void test51() {
        String str = "我是中国人";
        System.out.println(str.length());

        String str2 = "111abc";
        System.out.println(str2.length());

        String str3 = "  sd sd d  ";
        System.out.println("|" + str3.trim() + "|");
    }

    @Test
    public void test53() {
        User user = new User();
        System.out.println(user.getId());
        System.out.println(user.getMoney());
    }


    @Test
    public void test55() {
        Object obj = null;
        String str = (String)obj;
        System.out.println(str);
    }

    @Test
    public void test57() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 0);
        System.out.println(cal.getTime());

    }

    @Test
    public void test59() throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date time = df.parse("2018-06-06 11:11:24");

        Calendar c1 = Calendar.getInstance();
        c1.setTime(time);

        Calendar now = Calendar.getInstance(); //当前时间
        Integer timeDiffHour = now.get(Calendar.HOUR_OF_DAY) - c1.get(Calendar.HOUR_OF_DAY);
        Integer timeDiffMinute = now.get(Calendar.MINUTE) - c1.get(Calendar.MINUTE);
        System.out.println(timeDiffHour);
        System.out.println(timeDiffMinute);
    }


}
