package com.capgemini.callforcode.reusable.auth.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuthFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthFilter.class);
    private static final int VALUE_CHARACTER_DATATYPE = 128;
    private static final String EXCEPTION_MESSAGE = "Exception is";

    public void destroy() {

        // Do nothing because of X and Y

    }

    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        ServletRequest req1 = req;
        HttpServletRequest request = (HttpServletRequest) req1;
        HttpServletResponse response = (HttpServletResponse) res;
        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        req1 = wrappedRequest;

        if ("GET".equalsIgnoreCase(request.getMethod())) {

            requestQueryString(req1, res, chain, request, response);
        } else if ("POST".equalsIgnoreCase(request.getMethod())) {

            requestPOSTMethod(req1, res, chain, response, wrappedRequest);

        }

    }

    public void requestPOSTMethod(ServletRequest req, ServletResponse res, FilterChain chain,
            HttpServletResponse response, MultiReadHttpServletRequest wrappedRequest)
            throws IOException, ServletException {
        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = wrappedRequest.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[VALUE_CHARACTER_DATATYPE];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            LOGGER.info(EXCEPTION_MESSAGE, ex);
            throw ex;
        }

        body = stringBuilder.toString();
        String tempBody = body;

        if ((tempBody.matches("(.*)<(.*)>(.*)</(.*)>(.*)")) || (tempBody.matches("(.*)%3C(.*)%3E(.*)%3C/(.*)%3E(.*)"))
                || tempBody.matches("(.*)&lt;(.*)&gt;(.*)&lt;/(.*)&gt;(.*)")) {

            response.setContentType("application/json");
            response.getOutputStream().print("{\"Error Code\":\"422\",\"Error Message\":\"Data is not valid\"}");

        } else {

            chain.doFilter(req, res);
        }
    }

    public void requestQueryString(ServletRequest req, ServletResponse res, FilterChain chain,
            HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getQueryString() == null) {

            LOGGER.info("Security Context before request");
            LOGGER.info("Request for: " + request.getRequestURL());

            chain.doFilter(req, res);

            LOGGER.info("Security Context after request");
            LOGGER.info("Request for: " + request.getRequestURL());
        } else {
            String str = request.getQueryString();

            if (str.matches("(.*)%3C(.*)%3E(.*)%3C/(.*)%3E(.*)") || str.matches("(.*)<(.*)>(.*)</(.*)>(.*)")
                    || str.matches("(.*)&lt;(.*)&gt;(.*)&lt;/(.*)&gt;(.*)")) {

                response.setContentType("application/json");
                response.getOutputStream().print("{\"Error Code\":\"404\",\"Message\":\"Data is not valid\"}");
            } else {
                chain.doFilter(req, res);
            }
        }
    }

    public void init(FilterConfig arg0) throws ServletException {

        // Do nothing because of X and Y
        LOGGER.info("FilterConfig", arg0);
    }

}
