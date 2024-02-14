package com.example.demo.valid;


import com.example.demo.model.BaseEntity;
import com.example.demo.model.DemoDto;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class ValidController {

    /**
     * @Validated(Demo.Update.class) 指定組別
     */
    @PostMapping("/valid")
    public void validDemo(@Validated(BaseEntity.Update.class) @RequestBody DemoDto dto){

    }
}
