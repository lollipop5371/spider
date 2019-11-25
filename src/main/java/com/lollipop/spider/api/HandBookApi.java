package com.lollipop.spider.api;

import com.lollipop.spider.net.BaseSpiderHandler;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.Optional;

/**
 * @author lzh
 * @date 2019/11/25 17:28
 **/
public class HandBookApi {
    public static <T> T gen8Encounter(int page, BaseSpiderHandler baseSpiderHandler) {
        Document doc = null;
        try {
            String path = String.format("https://www.gamersky.com/handbook/201911/1238773_%s.shtml", page);
            if (page == 1) {
                path = "https://www.gamersky.com/handbook/201911/1238773.shtml";
            }
            doc = Jsoup.connect(path)
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
