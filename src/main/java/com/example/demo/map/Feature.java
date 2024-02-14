package com.example.demo.map;

import com.example.demo.model.DemoDto;
import com.example.demo.model.DemoVo;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Feature {


    /**
     * 找出兩筆List中一樣的資料並進行處理
     */
    void foundSameData(){
        List<DemoDto> dtoList = Arrays.asList( new DemoDto());

        Map<Integer, DemoDto> demoGroupById = dtoList.stream().collect(Collectors.toMap(DemoDto::getId, Function.identity()));

        List<DemoVo> voList = Arrays.asList(new DemoVo());

        for(DemoVo vo : voList){
            DemoDto dto = demoGroupById.get(vo.getId());
            if(Objects.nonNull(dto)){
                System.out.println("找到同一筆資料");
            }else{
                System.out.println("沒找到資料");
            }
        }
    }



}
