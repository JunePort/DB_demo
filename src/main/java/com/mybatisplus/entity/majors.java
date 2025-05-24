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
@TableName("majors")  // 精确映射数据库表
public class majors {
    @TableId(value = "major_id", type = IdType.AUTO)  // 自增主键配置
    private Integer majorId;


    private String majorCode;


    private String majorName;

    private Integer defaultProgramDuration;  // 默认学制年限

    private Integer defaultEducationLevelId;  // 默认学历层次ID
}