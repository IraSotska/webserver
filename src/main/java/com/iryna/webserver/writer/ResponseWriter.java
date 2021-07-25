package com.iryna.webserver.writer;

import com.iryna.webserver.HttpStatus;
import com.iryna.webserver.entity.Response;
import java.io.IOException;
import java.io.Writer;

public class ResponseWriter {


    public void writeResponse(Writer writer, Response response) throws IOException {

        writer.write("HTTP/1.1 " + response.getHttpStatus() +
                "\n" + "\n");
        String contentLine;
        while ((contentLine = response.getContentReader().readContent()) != null) {
            writer.write(contentLine);
        }
    }

    public void writeErrorResponse(Writer writer, HttpStatus httpStatus) {
        try {
            writer.write("HTTP/1.1 " + HttpStatus.OK +  "\n" + "\n");
            writer.write(String.valueOf(httpStatus));
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }
}
