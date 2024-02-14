package com.example.demo.model;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DemoVo {

    @JsonIgnore                         //不會包含在response JSON
    private Integer id;

    @JsonProperty(value = "user_name")          //修改JSON傳入名稱
    private String chName;


    @JsonProperty(access = JsonProperty.Access.READ_ONLY)         //前端只能讀
    @JsonFormat(pattern ="yyyy-MM-dd HH:mm:ss z", timezone="GMT=+8")        //時間回傳格是 +8小時
    private LocalDateTime updateTime;




}
