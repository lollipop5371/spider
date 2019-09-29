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
        String res = "<!DOCTYPE html>\n" +
                "<!--STATUS OK--><html> <head><meta http-equiv=content-type content=text/html;charset=utf-8><meta http-equiv=X-UA-Compatible content=IE=Edge><meta content=always name=referrer><link rel=stylesheet type=text/css href=https://ss1.bdstatic.com/5eN1bjq8AAUYm2zgoY3K/r/www/cache/bdorz/baidu.min.css><title>百度一下，你就知道</title></head> <body link=#0000cc> <div id=wrapper> <div id=head> <div class=head_wrapper> <div class=s_form> <div class=s_form_wrapper> <div id=lg> <img hidefocus=true src=//www.baidu.com/img/bd_logo1.png width=270 height=129> </div> <form id=form name=f action=//www.baidu.com/s class=fm> <input type=hidden name=bdorz_come value=1> <input type=hidden name=ie value=utf-8> <input type=hidden name=f value=8> <input type=hidden name=rsv_bp value=1> <input type=hidden name=rsv_idx value=1> <input type=hidden name=tn value=baidu><span class=\"bg s_ipt_wr\"><input id=kw name=wd class=s_ipt value maxlength=255 autocomplete=off autofocus=autofocus></span><span class=\"bg s_btn_wr\"><input type=submit id=su value=百度一下 class=\"bg s_btn\" autofocus></span> </form> </div> </div> <div id=u1> <a href=http://news.baidu.com name=tj_trnews class=mnav>新闻</a> <a href=https://www.hao123.com name=tj_trhao123 class=mnav>hao123</a> <a href=http://map.baidu.com name=tj_trmap class=mnav>地图</a> <a href=http://v.baidu.com name=tj_trvideo class=mnav>视频</a> <a href=http://tieba.baidu.com name=tj_trtieba class=mnav>贴吧</a> <noscript> <a href=http://www.baidu.com/bdorz/login.gif?login&amp;tpl=mn&amp;u=http%3A%2F%2Fwww.baidu.com%2f%3fbdorz_come%3d1 name=tj_login class=lb>登录</a> </noscript> <script>document.write('<a href=\"http://www.baidu.com/bdorz/login.gif?login&tpl=mn&u='+ encodeURIComponent(window.location.href+ (window.location.search === \"\" ? \"?\" : \"&\")+ \"bdorz_come=1\")+ '\" name=\"tj_login\" class=\"lb\">登录</a>');\n" +
                "                </script> <a href=//www.baidu.com/more/ name=tj_briicon class=bri style=\"display: block;\">更多产品</a> </div> </div> </div> <div id=ftCon> <div id=ftConw> <p id=lh> <a href=http://home.baidu.com>关于百度</a> <a href=http://ir.baidu.com>About Baidu</a> </p> <p id=cp>&copy;2017&nbsp;Baidu&nbsp;<a href=http://www.baidu.com/duty/>使用百度前必读</a>&nbsp; <a href=http://jianyi.baidu.com/ class=cp-feedback>意见反馈</a>&nbsp;京ICP证030173号&nbsp; <img src=//www.baidu.com/img/gs.gif> </p> </div> </div> </div> </body> </html>\n";
//        System.out.println(res);
//        Pattern pattern = Pattern.compile("href=(.+?)");
//        Matcher matcher = pattern.matcher(res);
//        if (matcher.find()) {
//            System.out.println(matcher.group(1));
//        }
        Document doc = Jsoup.connect("https://wiki.52poke.com/wiki/百变怪").get();
        //System.out.println(doc);
        Element e1 = doc.getElementById("获得方式").parent().nextElementSibling();
//
        //System.out.println(e1);
        Elements e2 = e1.select(".bgwhite");
        for (Element e3 : e2) {
            //version
            Element version = e3.children().get(0);
            Pattern p0 = Pattern.compile("title=\"(.+?)\"");
            Matcher m0 = p0.matcher(version.html());
            while (m0.find()) {
                System.out.print(m0.group(1) + " ,");
            }
            System.out.print("\n");

            //encounters
            Element encounters = e3.children().get(1);
            Pattern p1 = Pattern.compile("title=\"(.+?)\"");
            Matcher m1 = p1.matcher(encounters.html());
            while (m1.find()) {
                System.out.print(m1.group(1) + " ,");
            }
            System.out.print("\n");


            //encounters-method
            Element encounterWay = e3.children().get(2);
            System.out.println(encounterWay.text());

            System.out.println("===============================");
        }


    }

}
