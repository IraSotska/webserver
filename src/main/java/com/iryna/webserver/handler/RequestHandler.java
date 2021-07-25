package com.iryna.webserver.handler;

import com.iryna.webserver.HttpStatus;
import com.iryna.webserver.entity.Request;
import com.iryna.webserver.entity.Response;
import com.iryna.webserver.parser.RequestParser;
import com.iryna.webserver.reader.ContentReader;
import com.iryna.webserver.writer.ResponseWriter;

import java.io.*;

public class RequestHandler {
    private BufferedWriter bufferedWriter;
    private BufferedReader bufferedReader;
    private String webAppPath;

    public RequestHandler(BufferedWriter bufferedWriter, BufferedReader bufferedReader, String webAppPath) {
        this.bufferedWriter = bufferedWriter;
        this.bufferedReader = bufferedReader;
        this.webAppPath = webAppPath;
    }

    public void handle() {

        RequestParser requestParser = new RequestParser();
        Request request;
        ResponseWriter responseWriter = new ResponseWriter();
        try {
            request = requestParser.parseRequest(bufferedReader);
        } catch (IOException exception) {
            responseWriter.writeErrorResponse(bufferedWriter, HttpStatus.BAD_REQUEST);
            return;
        }
        Response response = new Response();

        try (InputStreamReader inputStreamReader = new InputStreamReader(new FileInputStream(webAppPath + request.getUri()));
             BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        ) {
            ContentReader contentReader= new ContentReader();
            contentReader.setBufferedReader(bufferedReader);
            response.setContentReader(contentReader);
            response.setHttpStatus(HttpStatus.OK);
            responseWriter.writeResponse(bufferedWriter, response);
        } catch (FileNotFoundException e) {
            responseWriter.writeErrorResponse(bufferedWriter, HttpStatus.NOT_FOUND);
        } catch (IOException exception) {
            responseWriter.writeErrorResponse(bufferedWriter, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
