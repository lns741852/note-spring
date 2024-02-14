package com.example.demo.springboot;

import com.example.demo.DemoApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;

@Service
public class Restart{

    @Autowired
    private ConfigurableApplicationContext applicationContext;


    public void restart(){
        ApplicationArguments args = applicationContext.getBean(ApplicationArguments.class);
        Thread thread = new Thread(()->{
            applicationContext.close();
            SpringApplication.run(DemoApplication.class,args.getSourceArgs());
        });

        //關閉守護進程
        thread.setDaemon(false);
        thread.start();
    }

}