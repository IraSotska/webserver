package com.iryna.webserver.reader;

import java.io.*;

public class ContentReader {

    private BufferedReader bufferedReader;

    public void setBufferedReader(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    public String readContent() throws IOException {
        return bufferedReader.readLine();
    }
}
