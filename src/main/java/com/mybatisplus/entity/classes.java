package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.time.Year;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class classes {
    @TableId(value = "class_id", type = IdType.AUTO)
    private int classId;
    private String className;
    private int majorId;
    private Year gradeYear;
}