package com.example.demo.model;

import com.example.demo.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
public class DemoDto extends BaseEntity {
    /**
     *  Validated 驗證
     *
     *   @Email : 信箱
     *   @Pattern(regexp = "\\d+") : 正則表示法。
     *   @Null : Null。
     *   @NotNull : 不為Null。
     *   @NotEmpty : 不可為空。
     *   @NotBlank : 非null 且長度必須大於0。
     *   @Length(min=5, max=10) : 字串長度。
     *   @Min : 字串或數字。
     *   @Max : 字串或數字。
     *   @DecimalMin(value ="12.3") : 字串或數字。
     *   @DecimalMax : 字串或數字。
     *   @Size(min = 3, max =5) : List長度
     *   @Digits(integer, fraction) : 控制小數點。
     *   @AssertTrue : 為true。
     *   @AssertFalse : 為false。
     *   @URL : URL
     */

    private Integer id;

    /**
     * 宣告組別，在controller可指定生效組別，Update在BaseEntity內
     */
    @NotNull(groups = Update.class)
    @JsonProperty(value = "user_name")          //修改JSON傳入名稱
    private String name;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)         //前端只能寫
    private String password;

    @DateTimeFormat(pattern = "yyyy-MM-dd")               //接收時間字串
    private LocalDate effectDate;

}
