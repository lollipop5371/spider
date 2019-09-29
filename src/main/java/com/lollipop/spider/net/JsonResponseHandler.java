package com.lollipop.spider.net;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JsonResponseHandler {

    private static Logger logger = LoggerFactory.getLogger(JsonResponseHandler.class);

    public static <T> ResponseHandler<T> createResponseHandler(final Class<T> clazz) {
        return new JsonResponseHandlerImpl<T>(clazz);
    }

    public static class JsonResponseHandlerImpl<T> implements ResponseHandler<T> {

        private Class<T> clazz;

        public JsonResponseHandlerImpl(Class<T> clazz) {
            this.clazz = clazz;
        }

        @Override
        public T handleResponse(HttpResponse response) throws IOException {
            int status = response.getStatusLine().getStatusCode();
            if (status >= 200 && status < 300) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity, "utf-8");
                return JSON.parseObject(str, clazz);
            } else {
                throw new ClientProtocolException("Unexpected response status: " + status);
            }
        }

    }
}
