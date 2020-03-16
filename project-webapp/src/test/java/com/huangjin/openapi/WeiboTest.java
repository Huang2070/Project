package com.huangjin.openapi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import com.google.common.collect.Maps;
import com.huangjin.util.CrawHttpUtil;
import com.huangjin.util.JsonConvertUtil;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import org.apache.commons.lang3.StringUtils;
import org.junit.Test;

/**
 * @Author: 黄金
 * @Description:
 * @Date: Create in 10:18 2019-10-31
 */
public class WeiboTest {


    @Test
    public void test1() throws ParseException {



        Long nextCursor = 0L;

        String accessToken = "2.00cNlGAH09hgAe02269ba8cd0YRFc1";
        String url = "https://c.api.weibo.com/2/statuses/user_timeline/biz.json?access_token=" + accessToken;

        do {
            ReadContext readContext = retryGet(url);

            String error = JsonConvertUtil.readJsonPath(readContext,"$.error","",String.class);
            if(StringUtils.isNotEmpty(error)){
                System.out.println(error);
            }
            int listSize = readContext.read("$.statuses.length()");
            if(listSize < 1) {
                System.out.println(listSize);
            }

            for(int i = 0; i < listSize; i++){

                String prefix = "$.statuses[" + i + "].";

                String jsTimeStr = JsonConvertUtil.readJsonPath(readContext, prefix, "created_at", String.class);

                Date date = new Date(jsTimeStr);

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH点mm分", Locale.CHINA);
                System.out.println(sdf.format(date));

                this.parseData(readContext);
            }

            nextCursor = readContext.read("$.next_cursor");
            accessToken = "2.00P_dWyG09hgAe737741817b3R1D6D";
            url = "https://c.api.weibo.com/2/statuses/user_timeline/biz.json?access_token=" + accessToken;
            url = url + "&max_id=" + nextCursor;
        } while(nextCursor != 0);

    }



    private ReadContext retryGet(String url){

        Map<String, String> header = Maps.newHashMap();
        header.put("User-Agent","Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_0) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.77 Safari/537.36");
        header.put("Cache-Control","no-cache");

        //String resp = HttpUtil.get(url, "", header);
        //String resp = HttpClientUtils.get(url);

        String resp = CrawHttpUtil.getInstance().getByNut(url, null);
        ReadContext readContext = JsonPath.parse(resp);

        return readContext;
    }


    private void parseData(ReadContext readContext) {
        String prefix = "$.statuses[0].url_objects[0].object.object.";
        String title = JsonConvertUtil.readJsonPath(readContext, prefix, "titles[0]", String.class);
        System.out.println(title);

    }
}
