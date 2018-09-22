package com.quanwc.http;

import javax.annotation.PostConstruct;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * httpClient
 * @author quanwenchao
 * @date 2018/9/22 17:22:45
 */
@Slf4j
@Component
public class HttpClient {

    private static final String UTF8 = "UTF-8";
    private static final String CONTENT_TYPE = "application/json";


    private org.apache.commons.httpclient.HttpClient httpClient;

    @PostConstruct
    public void initHttpClient() {
        httpClient = new org.apache.commons.httpclient.HttpClient();
    }

    /**
     * get请求
     * @param url url
     * @param params
     * @return
     */
    public Response get(String url, NameValuePair[] params) {
        Response response = new Response();

        GetMethod method = new GetMethod(url);
        method.setQueryString(params);
        method.getParams().setContentCharset(UTF8);

        try {
            response.setStatusCode(httpClient.executeMethod(method));
            response.setBody(method.getResponseBodyAsString());
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            method.releaseConnection();
        }
        return null;
    }

    /**
     * post请求
     * @return
     */
    public Response post(String url, NameValuePair[] params, String body) {
        PostMethod method = new PostMethod(url);
        method.setQueryString(params);

        method.getParams().setContentCharset(UTF8);
        try {
            method.setRequestEntity(new StringRequestEntity(body, CONTENT_TYPE, UTF8));
            Response response = new Response();
            response.setStatusCode(httpClient.executeMethod(method));
            response.setBody(method.getResponseBodyAsString());
            return response;
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            method.releaseConnection();
        }
        return null;
    }
}
