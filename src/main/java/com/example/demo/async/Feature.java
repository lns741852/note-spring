package com.example.demo.async;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;


@Slf4j
public class Feature {


    public void waitAllCompletableThenRunAsync() throws Exception {
        List<CompletableFuture<Integer>> futures = new ArrayList<>();
        List<Integer> results = new ArrayList<>();

        getList().forEach(i->{
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(()-> {
                try {
                    return sleep((Integer) i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }).thenApply(result -> {
                results.add(result); // 在這裡收集結果
                return result;
            });;
            futures.add(future);
        });
        // 等待所有的 CompletableFuture 完成
        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();


        results.forEach(System.out::println);
    }



    private int sleep(int num) throws InterruptedException {
        Thread.sleep(((int) (Math.random() * 10)) *1000);
        log.info(String.valueOf(num));
        return num;
    }

    private List getList(){
        List numList = new ArrayList();

        numList.add(1);
        numList.add(2);
        numList.add(3);
        numList.add(4);

        return  numList;
    }



}
