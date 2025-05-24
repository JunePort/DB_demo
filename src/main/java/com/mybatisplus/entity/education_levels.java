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
@TableName("education_levels")  // 精确映射数据库表
public class education_levels {
    @TableId(value = "education_level_id", type = IdType.AUTO)  // 自增主键配置
    private Integer educationLevelId;
    private String levelName;
}