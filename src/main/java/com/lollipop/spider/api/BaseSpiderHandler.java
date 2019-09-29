package com.lollipop.spider.api;

import org.jsoup.nodes.Document;

public interface BaseSpiderHandler<T> {
    T parseData(Document document);
}
