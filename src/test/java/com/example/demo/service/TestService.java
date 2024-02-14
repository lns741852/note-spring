package com.example.demo.service;

import com.example.demo.async.Feature;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@SpringBootTest
@Slf4j
public class TestService {


    @Test
    public void waitAllFunctionComplete() throws Exception {
        Feature feature = new Feature();
        feature.waitAllCompletableThenRunAsync();
    }
}
