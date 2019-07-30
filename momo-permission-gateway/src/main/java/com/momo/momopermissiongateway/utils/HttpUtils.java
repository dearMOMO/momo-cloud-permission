package com.momo.momopermissiongateway.utils;

/**
 * Created by 李杰 on 2018/10/21.
 */

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * HTTP 请求工具类
 */
public class HttpUtils {
    public static void main(String[] args) {
        System.out.println(HttpUtils.doGet("http://111.231.117.239:8090/a"));
        System.out.println(HttpUtils.doPut("http://111.231.117.239:8090/a", ""));
    }

    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    private static PoolingHttpClientConnectionManager connMgr;
    private static RequestConfig requestConfig;
    private static final int MAX_TIMEOUT = 120000000;

    static {
        // 设置连接池
        connMgr = new PoolingHttpClientConnectionManager();
        // 设置连接池大小
        connMgr.setMaxTotal(100);
        connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

        RequestConfig.Builder configBuilder = RequestConfig.custom();
        // 设置连接超时
        configBuilder.setConnectTimeout(MAX_TIMEOUT);
        // 设置读取超时
        configBuilder.setSocketTimeout(MAX_TIMEOUT);
        // 设置从连接池获取连接实例的超时
        configBuilder.setConnectionRequestTimeout(MAX_TIMEOUT);
        // 在提交请求之前 测试连接是否可用  超时时间
        connMgr.setValidateAfterInactivity(5000);
        requestConfig = configBuilder.build();
    }

    /**
     * 发送 GET 请求（HTTP），不带输入数据
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {
        return doGet(url, new HashMap<String, Object>());
    }

    /**
     * 发送 GET 请求（HTTP），K-V形式
     *
     * @param url
     * @param params
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {

        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpclient.execute(httpGet);

            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static JSONObject doGet(String url, String token) {
        return doGet(url, new HashMap<String, Object>(), token);
    }

    public static JSONObject doGet(String url, Map<String, Object> params, String token) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader("X-Auth-Token", token);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONObject fromObject = JSONObject.parseObject(result);
        return fromObject;
    }

    public static JSONArray doGet2(String url, String token) {
        return doGet2(url, new HashMap<String, Object>(), token);
    }

    public static JSONArray doGet2(String url, Map<String, Object> params, String token) {
        String apiUrl = url;
        StringBuffer param = new StringBuffer();
        int i = 0;
        for (String key : params.keySet()) {
            if (i == 0) {
                param.append("?");
            } else {
                param.append("&");
            }
            param.append(key).append("=").append(params.get(key));
            i++;
        }
        apiUrl += param;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader("X-Auth-Token", token);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return JSONObject.parseArray(result);
    }

    public static String doGet3(String url, String token) {
        String apiUrl = url;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            httpGet.setHeader("X-Auth-Token", token);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String doGet3(String urls) {
        String apiUrl = urls;
        String result = null;
        CloseableHttpClient httpclient = HttpClients.createDefault();
        try {
            HttpGet httpGet = new HttpGet(apiUrl);
            HttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity, "UTF-8");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     *
     * @param apiUrl
     * @param json   json对象
     * @return
     */
    public static String doPostString(String apiUrl, String json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse resp = null;
        String response = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            resp = httpClient.execute(httpPost);
            HttpEntity entity = resp.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
            logger.info("HttpUtils doPostString请求 得到的相应 ： " + httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    public static String doPost2(String apiUrl, List<BasicNameValuePair> listParams) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            //StringEntity stringEntity = new StringEntity(xml, "UTF-8");// 解决中文乱码问题
            UrlEncodedFormEntity urlFormEntity = new UrlEncodedFormEntity(listParams);
            httpPost.setEntity(urlFormEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式 ，带身份令牌的head
     *
     * @param apiUrl
     * @param json
     * @param tokenKey
     * @param tokenValue
     * @return
     */
    public static String doPost(String apiUrl, String json, String tokenKey, String tokenValue) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse resp = null;
        JSONObject response = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json, "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json;charset=UTF-8");
            httpPost.setEntity(stringEntity);
            httpPost.setHeader(tokenKey,tokenValue);
            resp = httpClient.execute(httpPost);
            HttpEntity entity = resp.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式 ，带身份令牌的head
     *
     * @param apiUrl
     * @param json
     * @param token
     * @return
     */
    public static JSONObject doPost(String apiUrl, JSONObject json, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse resp = null;
        JSONObject response = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpPost);
            System.out.println("resp: " + resp);
            HttpEntity entity = resp.getEntity();
            if (entity == null || entity.getContentLength() <= 0) {
                response = new JSONObject();
                response.put("state", "删除成功！");
            } else {
                httpStr = EntityUtils.toString(entity, "UTF-8");
                response = JSONObject.parseObject(httpStr);
                System.out.println("httpStr: " + httpStr);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式 ，带身份令牌的head,但没有返回值
     *
     * @param apiUrl
     * @param json
     * @param token
     * @return
     */
    public static void doPost(String apiUrl, String token, JSONObject json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(apiUrl);
        httpPost.setConfig(requestConfig);
        StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        httpPost.setEntity(stringEntity);
        httpPost.setHeader("X-Auth-Token", token);
        try {
            CloseableHttpResponse execute = httpClient.execute(httpPost);
            HttpEntity httpEntity = execute.getEntity();
            System.out.println("httpEntity:" + httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }

        httpPost.abort();
    }

    public static void doPost(String apiUrl, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(token, "UTF-8");// 解决中文乱码问题
            httpPost.setEntity(stringEntity);
            System.out.println(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
            System.out.println("httpStr: " + httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static String doPost2(String apiUrl, JSONObject json, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse resp = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            httpPost.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpPost);
            HttpEntity entity = resp.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    System.out.println("45");
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    System.out.println("66");
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    public static String doPost2(String apiUrl, String xml) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            httpPost.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(xml, "UTF-8");// 解决中文乱码问题
            httpPost.setEntity(stringEntity);
            System.out.println(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 PUT 请求（HTTP），JSON形式 ，带身份令牌的head
     *
     * @param apiUrl
     * @param json
     * @param token
     * @return
     */
    public static JSONObject doPut(String apiUrl, String token, JSONObject json) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPut httpPut = new HttpPut(apiUrl);
        CloseableHttpResponse resp = null;
        JSONObject response = null;
        try {
            httpPut.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
            httpPut.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpPut);
            System.out.println("resp: " + resp);
            HttpEntity entity = resp.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
            response = JSONObject.parseObject(httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    public static JSONObject doPut(String apiUrl, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String httpStr = null;
        HttpPut httpPut = new HttpPut(apiUrl);
        CloseableHttpResponse resp = null;
        JSONObject response = null;
        try {
            httpPut.setConfig(requestConfig);
            StringEntity stringEntity = new StringEntity(token.toString(), "UTF-8");// 解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPut.setEntity(stringEntity);
            httpPut.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpPut);
            HttpEntity entity = resp.getEntity();
            httpStr = EntityUtils.toString(entity, "UTF-8");
            response = JSONObject.parseObject(httpStr);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return response;
    }

    /**
     * 发送 DELETE 请求（HTTP），JSON形式 ，带身份令牌的head
     *
     * @param url
     * @param token
     */
    public static void deleteCloud(String url, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse resp = null;
        httpDelete.setConfig(requestConfig);
        try {
            httpDelete.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpDelete);
            System.out.println(resp);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 发送 DELETE 请求（HTTP），JSON形式 ，带身份令牌的head
     *
     * @param url
     * @param token
     * @return
     */
    public static JSONObject doDelete(String url, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse resp = null;
        httpDelete.setConfig(requestConfig);
        String httpStr = null;
        JSONObject jsonObject = null;
        try {
            httpDelete.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpDelete);
            HttpEntity entity = resp.getEntity();
            if (entity == null || entity.getContentLength() == 0) {
                jsonObject = new JSONObject();
                jsonObject.put("state", "删除成功！");
            } else {
                httpStr = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(httpStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                    resp.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }

    /**
     * 发送 DELETE 请求（HTTP），
     *
     * @param url
     * @param token
     * @return
     */
    public static JSONObject delete(String url, String token) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(url);
        CloseableHttpResponse resp = null;
        httpDelete.setConfig(requestConfig);
        String httpStr = null;
        JSONObject jsonObject = null;
        try {
            httpDelete.setHeader("X-Auth-Token", token);
            resp = httpClient.execute(httpDelete);
            HttpEntity entity = resp.getEntity();
            if (entity == null) {
                jsonObject = null;
            } else {
                httpStr = EntityUtils.toString(entity, "UTF-8");
                jsonObject = JSONObject.parseObject(httpStr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resp != null) {
                try {
                    EntityUtils.consume(resp.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonObject;
    }

    /**
     * 创建
     *
     * @param url   请求url
     * @param json  请求参数
     * @param autho 认证value
     * @return
     */
    public static JSONObject doPostNat(String url, JSONObject json, String autho) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost post = new HttpPost(url);
        // 设置请求配置
        post.setConfig(requestConfig);
        // 设置请求头
        post.setHeader("Authorization", autho);
        // 设置请求体
        StringEntity stringEntity = new StringEntity(json.toString(), "UTF-8");// 解决中文乱码问题
        stringEntity.setContentEncoding("UTF-8");
        stringEntity.setContentType("application/json");
        post.setEntity(stringEntity);
        try {
            CloseableHttpResponse execute = httpClient.execute(post);
            HttpEntity entity = execute.getEntity();
            String string = EntityUtils.toString(entity, "UTF-8");
            System.out.println("大伟哥返回结果集" + string);
            return JSONObject.parseObject(string);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * nat64的查询
     *
     * @param url
     * @param ipv4Str
     * @param autho
     * @return
     */
    public static JSONObject doGetNat(String url, String ipv4Str, String autho) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet post = new HttpGet(url + "/" + ipv4Str);
        // 设置请求配置
        post.setConfig(requestConfig);
        // 设置请求头
        post.setHeader("Authorization", autho);
        CloseableHttpResponse execute;
        try {
            execute = httpClient.execute(post);
            HttpEntity entity = execute.getEntity();
            String string = EntityUtils.toString(entity);
            return JSONObject.parseObject(string);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 删除nat
     *
     * @param url   请求路径
     * @param ipStr 删除的IP值
     * @param autho 认证值
     * @return
     */
    public static JSONObject doDeleteNat(String url, String ipStr, String autho) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpDelete delete = new HttpDelete(url + "/" + ipStr);
        delete.setConfig(requestConfig);
        delete.setHeader("Authorization", autho);
        try {
            CloseableHttpResponse execute = httpClient.execute(delete);
            HttpEntity entity = execute.getEntity();
            String string = EntityUtils.toString(entity);
            System.out.println("删除浮动ip策略返回结果集" + string);
            return JSONObject.parseObject(string);
        } catch (ClientProtocolException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 建立ssl连接时应用
     *
     * @return
     */
    // private static CloseableHttpClient getClient(){
    // SSLContext sslcontext = null;
    // try {
    // sslcontext = SSLContexts.custom().loadTrustMaterial(null, new
    // TrustStrategy(){
    // public boolean isTrusted(X509Certificate[] chain, String authType) throws
    // CertificateException {
    // return true;
    // }
    // }).build();
    // } catch (KeyManagementException e) {
    // e.printStackTrace();
    // } catch (NoSuchAlgorithmException e) {
    // e.printStackTrace();
    // } catch (KeyStoreException e) {
    // e.printStackTrace();
    // }
    // SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
    // sslcontext,
    // new String[] { "TLSv1" },
    // null,
    // SSLConnectionSocketFactory.getDefaultHostnameVerifier());
    // CloseableHttpClient httpClient
    // =HttpClients.custom().setSSLSocketFactory(sslsf).build();
    // return httpClient;
    // }
}
