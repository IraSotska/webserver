package com.iryna.webserver.parser;

import com.iryna.webserver.HttpMethod;
import com.iryna.webserver.entity.Request;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestParser {

    public Request parseRequest(BufferedReader reader) throws IOException {
        Request request = new Request();
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            if (line.contains("HTTP")) {
                String[] res = line.split(" ");
                request.setHttpMethod(HttpMethod.valueOf(res[0]));
                request.setUri(res[1]);
            } else {
                String[] res = line.split(": ");
                request.getHeaders().put(res[0], res[1]);
            }
        }
        return request;
    }
}
