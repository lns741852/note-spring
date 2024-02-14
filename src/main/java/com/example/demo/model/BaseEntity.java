package com.example.demo.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
//@MappedSuperclas
//@EntityListeners(AuditingEntityListener.class)
public class BaseEntity  {

//    @CreatedBy
//    @Column(name = "create_by", updatable = false)
//    @ApiModelProperty(value = "創建人", hidden = true)
//    private String createBy;
//
//    @LastModifiedBy
//    @Column(name = "update_by")
//    @ApiModelProperty(value = "更新人", hidden = true)
//    private String updateBy;
//
//    @CreationTimestamp
//    @Column(name = "create_time", updatable = false)
//    @ApiModelProperty(value = "創建時間", hidden = true)
//    private Timestamp createTime;
//
//    @UpdateTimestamp
//    @Column(name = "update_time")
//    @ApiModelProperty(value = "更新時間", hidden = true)
//    private Timestamp updateTime;

    /* 分組valid */
    public @interface Create {}

    /* 分組valid */
    public @interface Update {}


}