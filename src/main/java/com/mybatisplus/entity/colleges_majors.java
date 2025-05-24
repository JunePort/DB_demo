package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("colleges_majors")  // 指定关联表名
public class colleges_majors {
    private String collegeCode;  // varchar(2) 学院代码

    private Integer majorId;    // int 专业ID

}