package com.example.demo.set;

import com.example.demo.model.DemoDto;
import lombok.SneakyThrows;

import java.util.*;
import java.util.function.Function;

public class Feature {

    @SneakyThrows
    private static <T,U> void checkDuplicate(List<T> list, Function<T, U> fun){
        Set set = new HashSet<>();
        for(T t : list){
            U u = fun.apply(t);
            if(!set.add(u))
                throw new Exception("重複");
        }
    }

    public static void main(String[] args) {

        ArrayList<Object> objects = new ArrayList<>();
        DemoDto demoDto = new DemoDto();
        demoDto.setName("Hee");
        DemoDto demoDto2 = new DemoDto();
        demoDto2.setName("Hee1");

        List<DemoDto> list = Arrays.asList(demoDto, demoDto2);

        checkDuplicate(list, DemoDto::getName);
    }


}
