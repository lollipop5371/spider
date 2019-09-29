package com.lollipop.spider.net;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LocalHttpClient {

    private static final Logger logger = LoggerFactory.getLogger(LocalHttpClient.class);

    private static int timeout = 15000;

    private static int retryExecutionCount = 2;

    protected static CloseableHttpClient httpClient = HttpClientFactory.createHttpClient(100, 10, timeout, retryExecutionCount);


    /**
     * @param timeout timeout
     * @since 2.7.0
     */
    public static void setTimeout(int timeout) {
        LocalHttpClient.timeout = timeout;
    }

    /**
     * @param retryExecutionCount retryExecutionCount
     * @since 2.7.0
     */
    public static void setRetryExecutionCount(int retryExecutionCount) {
        LocalHttpClient.retryExecutionCount = retryExecutionCount;
    }


    public static CloseableHttpResponse execute(HttpUriRequest request) {
        try {
            return httpClient.execute(request, HttpClientContext.create());
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
        return null;
    }


    public static <T> T execute(HttpUriRequest request, ResponseHandler<T> responseHandler) {
        try {
            return httpClient.execute(request, responseHandler, HttpClientContext.create());
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return null;
    }


    /**
     * 数据返回自动JSON对象解析
     *
     * @param request request
     * @param clazz   clazz
     * @return result
     */
    public static <T> T executeJsonResult(HttpUriRequest request, Class<T> clazz) {
        return execute(request, JsonResponseHandler.createResponseHandler(clazz));
    }


    public static String executeHtmlResult(HttpUriRequest request){
        return execute(request,HtmlResponseHandler.createResponseHandler());
    }
}
