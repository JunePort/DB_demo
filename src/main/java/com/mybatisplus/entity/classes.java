package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("classes")
public class classes {
    @TableId(value = "class_id", type = IdType.AUTO)
    private Integer classId;

    @TableField("class_name")
    private String className;

    @TableField("college_code")
    private String collegeCode;


    @TableField("major_id")
    private Integer majorId;


    @TableField("grade_year")
    private String gradeYear;

    // 关联实体（非数据库字段）
    @TableField(exist = false)
    private colleges collegeInfo;  // 关联学院信息

    @TableField(exist = false)
    private majors majorInfo;      // 关联专业信息

    public classes(int classId, String className, String collegeCode, int majorId, String gradeYear) {
    }
}