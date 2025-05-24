package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("counselors")  // 明确映射数据库表
public class counselors {
    @TableId(value = "counselor_id", type = IdType.AUTO)  // 自增主键
    private Integer counselorId;

    @TableField("counselor_name")  // 明确列名映射
    private String counselorName;

    @TableField("contact_info")
    private String contactInfo;

    @TableField("college_code_assignment")  // 外键字段
    private String collegeCodeAssignment;
}