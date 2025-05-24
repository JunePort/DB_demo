package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("colleges")  // 指定对应的数据库表名
public class colleges {
    @TableId(value = "college_code", type = IdType.ASSIGN_ID) // 根据varchar类型使用ASSIGN_ID
    private String collegeCode;

    private String collegeName;

}