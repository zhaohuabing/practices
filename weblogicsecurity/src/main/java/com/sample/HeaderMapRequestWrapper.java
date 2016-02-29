package com.sample;


import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;

import java.net.MalformedURLException;
import java.net.URL;

import java.net.URLConnection;

import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * allow adding additional header entries to a request
 *
 * @author wf
 *
 */
public class HeaderMapRequestWrapper extends HttpServletRequestWrapper {
    static int begin =0;
    /**
     * construct a wrapper for this request
     *
     * @param request
     */
    public HeaderMapRequestWrapper(HttpServletRequest request) {
        super(request);
    }

    private Map<String, String> headerMap = new HashMap<String, String>();

    /**
     * add a header with given name and value
     *
     * @param name
     * @param value
     */
    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {

        String headerValue = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            headerValue = headerMap.get(name);
        }
        
        if(begin==0)
        {
            begin=1;
        Enumeration<String> headerName = getHeaderNames();
        while(headerName.hasMoreElements()) {
            String header = headerName.nextElement();
            System.out.println("header:" + header + " value:" +getHeader(header));
        }
        }
            
        
        return headerValue;
    }
    
    public static void main(String[] args) throws MalformedURLException,
                                                  IOException {
        URL url = new URL("http://127.0.0.1:7101/OpssSamplePOC-ViewController-context-root/faces/testRoles.jspx");
        URLConnection connection = url.openConnection( );
        connection.setRequestProperty("SamplePerimeterAtnToken","welcome");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                                           connection.getInputStream( )));
        
        // Read the input stream
        in.close( );
    }

    /**
     * get the Header names
     */
    @Override
    public Enumeration<String> getHeaderNames() {
        List<String> names = Collections.list(super.getHeaderNames());
        for (String name : headerMap.keySet()) {
            names.add(name);
        }
        return Collections.enumeration(names);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> values = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            values.add(headerMap.get(name));
        }
        return Collections.enumeration(values);
    }

}

