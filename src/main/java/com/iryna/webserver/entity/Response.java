package com.iryna.webserver.entity;

import com.iryna.webserver.HttpStatus;
import com.iryna.webserver.reader.ContentReader;

public class Response {
    private HttpStatus httpStatus;
    private ContentReader contentReader;

    public ContentReader getContentReader() {
        return contentReader;
    }

    public void setContentReader(ContentReader contentReader) {
        this.contentReader = contentReader;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
