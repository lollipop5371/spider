package com.lollipop.spider.net;

import org.jsoup.nodes.Document;

public interface BaseSpiderHandler<T> {
    T parseData(Document document);
}
