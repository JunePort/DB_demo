package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("marital_statuses")  // 精确映射数据库表
public class marital_statuses {
    @TableId(value = "marital_status_id", type = IdType.AUTO)
    private Integer maritalStatusId;  // 自增主键


    @TableField("status_description")
    private String statusDescription;  // 婚姻状态描述


    @TableField("status_code")
    private String statusCode;        // 状态代码（如S/M/D）
}