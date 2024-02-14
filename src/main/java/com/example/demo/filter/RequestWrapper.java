package com.example.demo.filter;


import lombok.SneakyThrows;
import org.apache.tomcat.util.http.FastHttpDateFormat;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 自訂 RequestWrapper , 由於default HttpServletRequest的特性 => 不可寫入,只讀一次
 */
public class RequestWrapper extends HttpServletRequestWrapper {

    private Map<String, String> headerMap = new HashMap<>();

    private Map<String, String[]> paramMap = new HashMap<>();

    private final String body;

    @SneakyThrows
    public RequestWrapper(HttpServletRequest request) {
        super(request);
        paramMap.putAll(request.getParameterMap());
        StringBuffer sBuffer = new StringBuffer();
        BufferedReader bufferedReader = request.getReader();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sBuffer.append(line);
        }
        body = sBuffer.toString();
    }

    //自訂 header 寫入方法
    public void addHeader(String name, String value) {
        headerMap.put(name, value);
    }

    @Override
    public String getHeader(String name) {
        String value = super.getHeader(name);
        if (headerMap.containsKey(name)) {
            return headerMap.get(name);
        }

        return value;
    }

    @Override
    public Enumeration<String> getHeaderNames() {
        ArrayList<String> nameList = Collections.list(super.getHeaderNames());
        for (String name : headerMap.keySet()) {
            nameList.add(name);
        }

        return Collections.enumeration(nameList);
    }

    @Override
    public Enumeration<String> getHeaders(String name) {
        List<String> valueList = Collections.list(super.getHeaders(name));
        if (headerMap.containsKey(name)) {
            valueList = Arrays.asList(headerMap.get(name));
        }

        return Collections.enumeration(valueList);
    }

    @Override
    public int getIntHeader(String name) {
        return Integer.valueOf(getHeader(name));
    }

    @Override
    public long getDateHeader(String name) {
        String value = getHeader(name);
        if (value == null) {
            return -1L;
        } else {
            long date = FastHttpDateFormat.parseDate(value);
            if (date == -1L) {
                throw new IllegalArgumentException(value);
            } else {
                return date;
            }
        }
    }

    //自訂 parameter 寫入方法
    public void addParameter(String name, String value) {
        String[] valueArr = paramMap.get(name);
        if (valueArr == null) {
            valueArr = new String[]{name};
            paramMap.put(name, valueArr);
        } else {
            String[] newValueArr = new String[valueArr.length + 1];
            newValueArr[0] = value;
            for (int i = 0; i < valueArr.length; i++) {
                newValueArr[i+1] = valueArr[i];
            }
            paramMap.put(name, newValueArr);
        }
    }

    public void addParameter(String name, String[] valueArr) {
        paramMap.put(name, valueArr);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return paramMap;
    }

    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        if (paramMap.containsKey(name)) {
            String[] valueArr = paramMap.get(name);
            if (valueArr != null && valueArr.length != 0) {
                return valueArr[0];
            }
        }

        return value;
    }

    @Override
    public Enumeration<String> getParameterNames() {
        ArrayList<String> paramList = Collections.list(super.getParameterNames());
        for (String name : paramMap.keySet()) {
            paramList.add(name);
        }

        return Collections.enumeration(paramList);
    }

    @Override
    public String[] getParameterValues(String name) {
        String[] valueArr = super.getParameterValues(name);
        if (paramMap.containsKey(name)) {
            return paramMap.get(name);
        }

        return valueArr;
    }

    // 自訂body 寫入方式, 防止存入RequestBody後失效
    @Override
    public ServletInputStream getInputStream() {
        final ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(body.getBytes());
        return new ServletInputStream() {
            @Override
            public int read() {
                return byteArrayInputStream.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener listener) {

            }
        };
    }
    @Override
    public BufferedReader getReader() {
        return new BufferedReader(new InputStreamReader(this.getInputStream()));
    }

}