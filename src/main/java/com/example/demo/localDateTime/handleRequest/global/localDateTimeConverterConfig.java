package com.example.demo.localDateTime.handleRequest.global;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;

@Configuration
public class localDateTimeConverterConfig {
    @Bean
    public Converter<String, LocalDateTime> localDateTimeConvert() {
        return new Converter<String, LocalDateTime>() {
            @Override
            public LocalDateTime convert(String source) {
                if (StringUtils.isEmpty(source)) {
                    return null;
                }
                return LocalDateTimeUtils.convert(source.trim());
            }
        };
    }


}
