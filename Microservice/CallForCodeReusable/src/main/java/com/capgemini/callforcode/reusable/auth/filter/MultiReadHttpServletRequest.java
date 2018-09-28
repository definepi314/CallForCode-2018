package com.capgemini.callforcode.reusable.auth.filter;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class MultiReadHttpServletRequest extends HttpServletRequestWrapper {

    private StringBuilder body;

    public MultiReadHttpServletRequest(HttpServletRequest request) throws IOException {
        super(request);
        body = new StringBuilder("");
        BufferedReader bufferedReader = request.getReader();

        Scanner sc = new Scanner(bufferedReader);
        while (sc.hasNext()) {
            body.append(sc.nextLine());
        }
        sc.close();
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.toString().getBytes());
        return new ServletInputStream() {
            public int read() throws IOException {
                return byteArrayInputStream.read();
            }
        };
    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }
}
