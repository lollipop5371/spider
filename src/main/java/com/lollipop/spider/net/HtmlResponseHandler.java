package com.lollipop.spider.net;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lzh
 * @date 2019/9/29 16:33
 **/
public class HtmlResponseHandler {
    private static Logger logger = LoggerFactory.getLogger(JsonResponseHandler.class);

    public static ResponseHandler<String> createResponseHandler() {
        return response -> {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                return EntityUtils.toString(entity, "utf-8");
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        };
    }
}
