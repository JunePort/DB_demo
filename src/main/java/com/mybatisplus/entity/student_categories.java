package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("student_categories")  // 精确映射数据库表
public class student_categories {
    @TableId(value = "student_category_id", type = IdType.AUTO)
    private Integer studentCategoryId;  // 自增主键

    @TableField("category_name")
    private String categoryName;  // 学生类别名称
}