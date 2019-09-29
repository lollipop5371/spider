package com.lollipop.spider.api;

import com.lollipop.spider.net.BaseSpiderHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;


public class WikiApi {
    public static <T> T get(String keyword, BaseSpiderHandler baseSpiderHandler) {
        Document doc = null;
        try {
            doc = Jsoup.connect("https://wiki.52poke.com/wiki/" + keyword)
                    .postDataCharset("GBK")
                    .ignoreHttpErrors(true)
                    .get();
            Optional<BaseSpiderHandler<T>> optional = Optional.ofNullable(baseSpiderHandler);
            if (optional.isPresent() && doc != null) {
                return optional.get().parseData(doc);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
