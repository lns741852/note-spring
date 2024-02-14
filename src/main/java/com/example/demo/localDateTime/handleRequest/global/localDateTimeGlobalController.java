package com.example.demo.localDateTime.handleRequest.global;

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
public class localDateTimeGlobalController {

    /**
     *
     * @param time : 可接收下列格式
     *      ISO 8601 :
     *          "2011-12-03T10:15:30+01:00[Europe/Paris]"
     *          "2011-12-03T10:15:30+01:00"
     *       "2011-12-03T10:15:30Z"
     *      RFC 3339 :
     *          "2011-12-03 10:15:30.123Z"
     *          "2011-12-03 10:15:30.123+01:00"
     *      LOCAL TIME :
     *         "2011-12-03 10:15:30"
     *      TIMESTAMP :
     *         "1322819330123"
     */
    @GetMapping("/global")
    public void getTime(@RequestParam
                        LocalDateTime time) {
        log.info(String.valueOf(time));
    }
}
