package com.example.demo.filter;


import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;


/**
 * 加入<dependency> commons-io </dependency>
 */
@Slf4j
@RestController
public class FilterController {

    @SneakyThrows
    @PostMapping("/testRequestWrapper")
    public String testRequestWrapper(HttpServletRequest request) {

        // 獲取header
        String name = request.getHeader("name");

        // 獲取header list
        Enumeration<String> from = request.getHeaders("from");
        List<String> fromList = Collections.list(from);
        log.info("header from: {}", fromList.toString());

        // 獲取Parameter
        String param1 = request.getParameter("param1");
        log.info("parameter param1: {}", param1);

        // 獲取Parameter array
        String[] param2Arr = request.getParameterValues("param2");
        log.info("parameter param2: {}", Arrays.asList(param2Arr).toString());

        //獲取Body
        byte[] body = IOUtils.toByteArray(request.getInputStream());
        String requestBody = new String(body, "UTF-8");


        return "SUCCESS";
    }
}
