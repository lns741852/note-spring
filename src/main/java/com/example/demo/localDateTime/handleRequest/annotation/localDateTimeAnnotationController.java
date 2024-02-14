package com.example.demo.localDateTime.handleRequest.annotation;



import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@Slf4j
@RestController
@RequestMapping("/api/v1/localDateTime/handleRequest")
public class localDateTimeAnnotationController {

    /**
     *
     * @param time : 可接收String "2019-12-11 13:00:00"
     */
    @GetMapping("/annotation")
    public void getTime(@RequestParam
                        @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime time) {
        log.info(String.valueOf(time));
    }
}
