package com.amt.jama.connect.pools;


import com.amt.jama.connect.constants.HttpCodeEnum;
import com.amt.jama.connect.constants.HttpConstants;
import com.amt.jama.connect.exception.HttpException;
import org.apache.http.*;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.*;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.*;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicHeaderElementIterator;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;
import java.nio.charset.CodingErrorAction;
import java.util.Map;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * HttpPool工具
 *
 * @author wikle.wang
 *
 */
public class HttpPoolUtils {
    /**
     * log日志
     */
    public final static Logger LOG = Logger.getLogger(HttpPoolUtils.class);
    /**
     * Http连接池
     */
    private PoolingHttpClientConnectionManager connManager = null;
    /**
     * HttpClient
     */
    private CloseableHttpClient httpclient = null;

    /**
     * 默认UA
     */
    private String defaultUserAgent = HttpConstants.DEFAULT_USER_AGENT;
    /**
     * 默认编码
     */
    private String defaultEncoding = HttpConstants.UTF_8;
    /**
     * 最大连接数
     */
    private int maxTotalConnections = 5 * 100;
    /**
     * 每个路由最大连接数
     */
    private int maxPerRoute = 5 * 10;
    /**
     * 最大连接路由数
     */
    private int maxRoute = 10;
    /**
     * 连接超时时间
     */
    private int connectTimeout = 9 * 1000;
    /**
     * 等待数据超时时间
     */
    private int soTimeout = 9 * 1000;
    /**
     * 连接池连接不足超时等待时间
     */
    private int connManagerTimeout = 100;
    /**
     *
     */
    private int idleTimeout = 9 * 1000;
    /**
     *
     */
    private int intervalTime = 1000 * 60 * 60;

    /**
     *
     */
    private String home = null;

    /**
     * Timeout字符串
     */
    private String timeout = "timeout";

    /**
     * Http请求默认配置参数
     */
    private RequestConfig defaultRequestConfig = null;
    // 相当于线程锁,用于线程安全
    private final static Object syncLock = new Object();
    private static ScheduledExecutorService monitorExecutor;

    private HttpPoolUtils() {
    }

    private static class SingletonHolder {
        private static final HttpPoolUtils httpPoolUtils = new HttpPoolUtils();
    }


    public static HttpPoolUtils getInstance(String url) throws Exception {
        HttpPoolUtils httpPoolUtils = SingletonHolder.httpPoolUtils;
        httpPoolUtils.afterPropertiesSet(url);
        return httpPoolUtils;
    }

    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied (and
     * satisfied BeanFactoryAware and ApplicationContextAware).
     * <p>
     * This method allows the bean instance to perform initialization only possible
     * when all bean properties have been set and to throw an exception in the event
     * of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such as failure to set an
     *                   essential property) or if initialization fails.
     */
    private void afterPropertiesSet(String url) throws Exception {
        this.initRequestConfig();
        this.home = url;
        this.initHttpPool(url);
    }

    /**
     * 请求获得可用的HttpClient
     *
     * @return CloseableHttpClient
     */
    private CloseableHttpClient getHttpclient(String url) throws Exception {
        if (httpclient == null) {
            synchronized (syncLock) {
                if (httpclient == null) {
                    // 初始化
                    this.initHttpPool(url);
                    //开启监控线程,对异常和空闲线程进行关闭
                    this.startMonitor();
                }
            }
        }
        return httpclient;
    }

    private void startMonitor() {
        //开启监控线程,对异常和空闲线程进行关闭
        monitorExecutor = new ScheduledThreadPoolExecutor(1);
        monitorExecutor.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                //关闭异常连接
                connManager.closeExpiredConnections();
                //关闭5s空闲的连接
                connManager.closeIdleConnections(idleTimeout, TimeUnit.MILLISECONDS);
                LOG.info("close expired and idle for over 5s connection");
            }
        }, intervalTime, intervalTime, TimeUnit.MILLISECONDS);
    }

    /**
     * 设置KeepAlive的策略
     *
     * @return ConnectionKeepAliveStrategy
     */
    public ConnectionKeepAliveStrategy getKeepAliveStrategy() {
        return new ConnectionKeepAliveStrategy() {
            public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
                HeaderElementIterator it = new BasicHeaderElementIterator(
                        response.headerIterator(HTTP.CONN_KEEP_ALIVE));
                while (it.hasNext()) {
                    HeaderElement he = it.nextElement();
                    String param = he.getName();
                    String value = he.getValue();
                    if (value != null && param.equalsIgnoreCase(timeout)) {
                        try {
                            return Long.parseLong(value) * 1000;
                        } catch (NumberFormatException ignore) {
                        }
                    }
                }
                return 1000 * 60 * 10;
            }
        };
    }

    /**
     * POST请求
     *
     * @param url         请求地址
     * @param data        请求参数
     * @param entity      请求携带的Entity
     * @param contentType 请求要求的返回数据类型
     * @param headers     请求携带的Header信息
     * @return result
     * @throws Exception 抛出
     */
    public String post(String url, String data, HttpEntity entity, String contentType, Map<String, String> headers)
            throws Exception {
        return this.http(HttpConstants.POST, url, data, entity, contentType, headers);
    }

    /**
     * GET请求
     *
     * @param url         请求地址
     * @param contentType 请求要求的返回数据类型
     * @param headers     请求携带的Header信息
     * @return result
     * @throws Exception 抛出
     */
    public String get(String url, String contentType, Map<String, String> headers) throws Exception {
        return this.http(HttpConstants.GET, url, null, null, contentType, headers);
    }

    /**
     * PUT请求
     *
     * @param url         请求地址
     * @param data        请求参数
     * @param entity      请求携带的Entity
     * @param contentType 请求要求的返回数据类型
     * @param headers     请求携带的Header信息
     * @return result
     * @throws Exception 抛出
     */
    public String put(String url, String data, StringEntity entity, String contentType, Map<String, String> headers)
            throws Exception {
        return this.http(HttpConstants.PUT, url, data, entity, contentType, headers);
    }

    /**
     * PATCH请求
     *
     * @param url         请求地址
     * @param data        请求参数
     * @param entity      请求携带的Entity
     * @param contentType 请求要求的返回数据类型
     * @param headers     请求携带的Header信息
     * @return result
     * @throws Exception 抛出
     */
    public String patch(String url, String data, StringEntity entity, String contentType, Map<String, String> headers)
            throws Exception {
        return this.http(HttpConstants.PATCH, url, data, entity, contentType, headers);
    }

    /**
     * DELETE请求
     *
     * @param url         请求地址
     * @param contentType 请求要求的返回数据类型
     * @param headers     请求携带的Header信息
     * @return result
     * @throws Exception 抛出
     */
    public String delete(String url, String contentType, Map<String, String> headers) throws Exception {
        return this.http(HttpConstants.DELETE, url, null, null, contentType, headers);
    }

    /**
     * 关闭连接池
     * @throws Exception 抛出
     */
    public void destroy() throws Exception{
        if(monitorExecutor!=null && !monitorExecutor.isShutdown()) {
            monitorExecutor.shutdown();
        }
        if (httpclient != null) {
            httpclient.close();
        }
        if(connManager!=null) {
            connManager.close();
        }
    }

    /**
     * Http请求
     *
     * @param method      请求Method
     * @param url         请求地址
     * @param data        请求参数
     * @param entity      请求携带的Entity
     * @param contentType 请求要求的返回数据类型
     * @param headers     请求携带的Header信息
     * @return String
     * @throws Exception 抛出
     */
    private String http(String method, String url, String data, HttpEntity entity, String contentType, Map<String, String> headers) throws Exception {
        HttpRequestBase http = getHttpRequest(method, this.home + url);
        this.setHeaders(http, headers);
        this.setEntity(http, data, entity, contentType);
        http.setConfig(this.getDefaultRequestConfig());
        return this.execute(http);
    }

    /**
     * 执行Http请求
     *
     * @param http HttpRequestBase
     * @return Result
     * @throws Exception 抛出
     */
    private String execute(HttpRequestBase http) throws Exception {
        CloseableHttpResponse response = null;
        String result = null;
        try {
            LOG.info("HttpClient execute : " + http.getURI());
            response = this.getHttpclient(http.getURI().toString()).execute(http);
            result = this.getData(response);
        } catch (Exception e) {
            throw e;
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            http.reset();
        }
        return result;
    }

    /**
     * 处理Code并获得数据
     *
     * @param response CloseableHttpResponse
     * @return data
     * @throws Exception 抛出
     */
    private String getData(CloseableHttpResponse response) throws Exception {
        String data = null;
        int statusCode = response.getStatusLine().getStatusCode();
        HttpEntity responseEntity = response.getEntity();
        if (responseEntity != null) {
            data = EntityUtils.toString(responseEntity, HttpConstants.UTF_8);
        }
        if (statusCode < HttpCodeEnum.CODE_200.getCode() || statusCode >= HttpCodeEnum.CODE_300.getCode()) {
            throw new HttpException(statusCode, data);
        }
        return data;
    }

    /**
     * 设置Header
     *
     * @param http    http
     * @param headers headers
     */
    private void setHeaders(HttpRequestBase http, Map<String, String> headers) {
        if (headers != null && headers.size() > 0) {
            for (Map.Entry<String, String> entry : headers.entrySet()) {
                http.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    /**
     * 设置Entity
     *
     * @param http        http
     * @param data        data
     * @param entity      entity
     * @param contentType contentType
     */
    private void setEntity(HttpRequestBase http, String data, HttpEntity entity, String contentType) {
        if (data != null) {
            entity = new StringEntity(data, HttpConstants.UTF_8);
            ((StringEntity) entity).setContentType(
                    (contentType != null && contentType.length() > 0) ? contentType : HttpConstants.APPLICATION_JSON);
        }
        if (entity != null) {
            if (http instanceof HttpEntityEnclosingRequestBase) {
                ((HttpEntityEnclosingRequestBase) http).setEntity(entity);
            }
        }
    }

    /**
     * 获得Http
     *
     * @param method Method
     * @param url    url
     * @return HttpRequestBase
     */
    private HttpRequestBase getHttpRequest(String method, String url) {
        HttpRequestBase httpRequest = null;
        if (HttpConstants.DELETE.equalsIgnoreCase(method)) {
            httpRequest = new HttpDelete(url);
        } else if (HttpConstants.POST.equalsIgnoreCase(method)) {
            httpRequest = new HttpPost(url);
        } else if (HttpConstants.PUT.equalsIgnoreCase(method)) {
            httpRequest = new HttpPut(url);
        } else if (HttpConstants.GET.equalsIgnoreCase(method)) {
            httpRequest = new HttpGet(url);
        }
        return httpRequest;
    }

    /**
     * 初始化HttpPool
     */
    private CloseableHttpClient initHttpPool(String url) throws Exception {
        try {
            SSLContext sslContext = SSLContexts.custom().build();
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder
                    .<ConnectionSocketFactory>create()
                    .register(HttpConstants.HTTP, PlainConnectionSocketFactory.INSTANCE)
                    .register(HttpConstants.HTTPS, new SSLConnectionSocketFactory(sslContext))
                    .build();

            connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);

            SocketConfig socketConfig = SocketConfig
                    .custom()
                    .setTcpNoDelay(true)
                    .build();

            connManager.setDefaultSocketConfig(socketConfig);

            MessageConstraints messageConstraints = MessageConstraints
                    .custom()
                    .build();

            ConnectionConfig connectionConfig = ConnectionConfig
                    .custom()
                    .setMalformedInputAction(CodingErrorAction.IGNORE)
                    .setUnmappableInputAction(CodingErrorAction.IGNORE)
                    .setCharset(Consts.UTF_8)
                    .setMessageConstraints(messageConstraints).build();

            connManager.setDefaultConnectionConfig(connectionConfig);
            connManager.setMaxTotal(maxTotalConnections);
            connManager.setDefaultMaxPerRoute(maxPerRoute);
            connManager.setMaxPerRoute(new HttpRoute(new HttpHost(url)), maxRoute);

            HttpRequestRetryHandler httpRequestRetryHandler = new HttpRequestRetryHandler() {
                public boolean retryRequest(IOException exception, int executionCount, HttpContext context) {
                    // 如果已经重试了5次，就放弃
                    if (executionCount >= 5) {
                        return false;
                    }
                    // 如果服务器丢掉了连接，那么就重试
                    if (exception instanceof NoHttpResponseException) {
                        return true;
                    }
                    // 不要重试SSL握手异常
                    if (exception instanceof SSLHandshakeException) {
                        return false;
                    }
                    // 超时
                    if (exception instanceof InterruptedIOException) {
                        return false;
                    }
                    // 目标服务器不可达
                    if (exception instanceof UnknownHostException) {
                        return false;
                    }
                    // 连接被拒绝
                    if (exception instanceof ConnectTimeoutException) {
                        return false;
                    }
                    // SSL握手异常
                    if (exception instanceof SSLException) {
                        return false;
                    }

                    HttpClientContext clientContext = HttpClientContext.adapt(context);
                    HttpRequest request = clientContext.getRequest();
                    // 如果请求是幂等的，就再次尝试
                    if (!(request instanceof HttpEntityEnclosingRequest)) {
                        return true;
                    }
                    return false;
                }
            };

            httpclient = HttpClients.custom().setConnectionManager(connManager).setRetryHandler(httpRequestRetryHandler)
                    // .setKeepAliveStrategy(getKeepAliveStrategy())
                    .setConnectionReuseStrategy(new DefaultConnectionReuseStrategy())
                    // .setUserAgent(defaultUserAgent)
                    .build();
        } catch (Exception e) {
            LOG.error("Some error is init, please check it.", e);
            throw e;
        }
        return httpclient;
    }

    /**
     * 初始化Request Config
     */
    private void initRequestConfig() {
        defaultRequestConfig = RequestConfig.custom()
                .setSocketTimeout(soTimeout)
                .setConnectTimeout(connectTimeout)
                .setConnectionRequestTimeout(connManagerTimeout)
                .setExpectContinueEnabled(false)
                .build();
    }


    private RequestConfig getDefaultRequestConfig() {
        return defaultRequestConfig;
    }

    public void setDefaultRequestConfig(RequestConfig defaultRequestConfig) {
        this.defaultRequestConfig = defaultRequestConfig;
    }

    public String getDefaultUserAgent() {
        return defaultUserAgent;
    }

    public void setDefaultUserAgent(String defaultUserAgent) {
        this.defaultUserAgent = defaultUserAgent;
    }

    public String getDefaultEncoding() {
        return defaultEncoding;
    }

    public void setDefaultEncoding(String defaultEncoding) {
        this.defaultEncoding = defaultEncoding;
    }

    public int getMaxTotalConnections() {
        return maxTotalConnections;
    }

    public void setMaxTotalConnections(int maxTotalConnections) {
        this.maxTotalConnections = maxTotalConnections;
    }

    public int getMaxPerRoute() {
        return maxPerRoute;
    }

    public void setMaxPerRoute(int maxPerRoute) {
        this.maxPerRoute = maxPerRoute;
    }

    public int getConnectTimeout() {
        return connectTimeout;
    }

    public void setConnectTimeout(int connectTimeout) {
        this.connectTimeout = connectTimeout;
    }

    public int getSoTimeout() {
        return soTimeout;
    }

    public void setSoTimeout(int soTimeout) {
        this.soTimeout = soTimeout;
    }

    public int getConnManagerTimeout() {
        return connManagerTimeout;
    }

    public void setConnManagerTimeout(int connManagerTimeout) {
        this.connManagerTimeout = connManagerTimeout;
    }

    public PoolingHttpClientConnectionManager getConnManager() {
        return connManager;
    }

    public void setConnManager(PoolingHttpClientConnectionManager connManager) {
        this.connManager = connManager;
    }

    public void setHttpclient(CloseableHttpClient httpclient) {
        this.httpclient = httpclient;
    }

    public int getMaxRoute() {
        return maxRoute;
    }

    public void setMaxRoute(int maxRoute) {
        this.maxRoute = maxRoute;
    }

    public int getIdleTimeout() {
        return idleTimeout;
    }

    public void setIdleTimeout(int idleTimeout) {
        this.idleTimeout = idleTimeout;
    }

    public int getIntervalTime() {
        return intervalTime;
    }

    public void setIntervalTime(int intervalTime) {
        this.intervalTime = intervalTime;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }
}
