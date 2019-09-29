package com.lollipop.spider.net;

import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

/**
 * httpclient 4.3.x
 *
 * @author Yi
 */
public class HttpClientFactory {

    private static final String[] supportedProtocols = new String[]{"TLSv1"};

    public static CloseableHttpClient createHttpClient() {
        return createHttpClient(100, 10, 15000, 2);
    }

    /**
     * @param maxTotal            maxTotal
     * @param maxPerRoute         maxPerRoute
     * @param timeout             timeout
     * @param retryExecutionCount retryExecutionCount
     * @return
     */
    public static CloseableHttpClient createHttpClient(int maxTotal, int maxPerRoute, int timeout, int retryExecutionCount) {
        try {
            SSLContext sslContext = SSLContexts.custom().useSSL().build();
            SSLConnectionSocketFactory sf = new SSLConnectionSocketFactory(sslContext, SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
            PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();
            poolingHttpClientConnectionManager.setMaxTotal(maxTotal);
            poolingHttpClientConnectionManager.setDefaultMaxPerRoute(maxPerRoute);
            SocketConfig socketConfig = SocketConfig.custom().setSoTimeout(timeout).build();
            poolingHttpClientConnectionManager.setDefaultSocketConfig(socketConfig);
            return HttpClientBuilder.create()
                    .setConnectionManager(poolingHttpClientConnectionManager)
                    .setSSLSocketFactory(sf)
                    .setRetryHandler(new HttpRequestRetryHandlerImpl(retryExecutionCount))
                    .build();
        } catch (KeyManagementException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * HttpClient  超时重试
     *
     * @author LiYi
     */
    private static class HttpRequestRetryHandlerImpl implements HttpRequestRetryHandler {

        private int retryExecutionCount;

        public HttpRequestRetryHandlerImpl(int retryExecutionCount) {
            this.retryExecutionCount = retryExecutionCount;
        }

        @Override
        public boolean retryRequest(
                IOException exception,
                int executionCount,
                HttpContext context) {
            if (executionCount > retryExecutionCount) {
                return false;
            }
            if (exception instanceof InterruptedIOException) {
                return false;
            }
            if (exception instanceof UnknownHostException) {
                return false;
            }
            if (exception instanceof SSLException) {
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            boolean idempotent = !(request instanceof HttpEntityEnclosingRequest);
            if (idempotent) {
                // Retry if the request is considered idempotent
                return true;
            }
            return false;
        }

    }

    ;

}
