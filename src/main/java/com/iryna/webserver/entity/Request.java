package com.iryna.webserver.entity;

import com.iryna.webserver.HttpMethod;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String uri;
    private Map<String, String> headers;
    private HttpMethod httpMethod;

    public Request() {
        headers = new HashMap<>();
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public HttpMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(HttpMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

}
