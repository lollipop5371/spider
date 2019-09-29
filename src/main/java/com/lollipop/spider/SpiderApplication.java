package com.lollipop.spider;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SpringBootApplication
@Slf4j
public class SpiderApplication {

    /**
     * https://blog.csdn.net/sbsujjbcy/article/details/44853967
     * https://blog.csdn.net/wangjunjun2008/article/details/50513528
     */

    public static void main(String[] args) throws IOException {
//        HttpUriRequest httpUriRequest = RequestBuilder.get()
//                .setUri("https://www.baidu.com")
//                .build();
//
//        String res = LocalHttpClient.executeHtmlResult(httpUriRequest);

        Document doc = Jsoup.connect("https://wiki.52poke.com/wiki/拉普拉斯")
                .postDataCharset("GBK")
                .get();
        Element e1 = doc.getElementById("获得方式").parent().nextElementSibling();
//
        // System.out.println(e1);
        Elements e2 = e1.select(".bgwhite");
        for (Element e3 : e2) {
            //version
            Element version = e3.children().get(0);
            //Pattern p0 = Pattern.compile("title=\"(.+?)\"");
            System.out.println(version.text());
//            if (true) {
//                break;
//            }
//            Pattern p0 = Pattern.compile("&#160;(.+?)&#160;");
//            Matcher m0 = p0.matcher(version.html());
//            while (m0.find()) {
//                System.out.print(m0.group(1) + " ,");
//            }
//            System.out.print("\n");

            //encounters
            Element encounters = e3.children().get(1);
//            Pattern p1 = Pattern.compile("title=\"(.+?)\"");
//            Matcher m1 = p1.matcher(encounters.html());
//            while (m1.find()) {
//                System.out.print(m1.group(1) + " ,");
//            }
            System.out.println(encounters.text());


            //encounters-method
            Element encounterWay = e3.children().get(2);
            System.out.println(encounterWay.text());

            //remark
            Element remark = e3.children().get(3);
            System.out.println(remark.text());


            System.out.println("===============================");
        }


    }

}
