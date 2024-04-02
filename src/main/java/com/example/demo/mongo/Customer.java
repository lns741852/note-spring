package com.example.demo.mongo;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Customer {
    @Id
    public String id;
    public String name;
    public String age;
}