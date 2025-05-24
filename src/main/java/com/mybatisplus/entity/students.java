package com.mybatisplus.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("students")
public class students {
    // 主键及基本信息
    @TableId(value = "student_id", type = IdType.INPUT)

    private String studentId;  // 学号


    private String name;  // 姓名


    @TableField("gender_id")
    private Integer genderId;  // 性别ID

    // 身份信息
    @TableField("country_id")
    private Integer countryId;  // 国家ID

    @TableField("birth_date")
    private LocalDate birthDate;  // 出生日期

    @TableField("ethnicity_id")
    private Integer ethnicityId;  // 民族ID

    @TableField("marital_status_id")
    private Integer maritalStatusId;  // 婚姻状况ID

    @TableField("political_status_id")
    private Integer politicalStatusId;  // 政治面貌ID

    @TableField("id_card_no")
    private String idCardNo;  // 身份证号

    // 学籍信息
    @TableField("student_category_id")
    private Integer studentCategoryId;  // 学生类别ID

    @TableField("enrollment_date")
    private LocalDate enrollmentDate;  // 入学日期

    @TableField("admission_method_id")
    private Integer admissionMethodId;  // 录取方式ID

    @TableField("college_code")
    private String collegeCode;  // 学院代码

    @TableField("major_id")
    private Integer majorId;  // 专业ID

    @TableField("program_duration")
    private Integer programDuration;  // 学制年限

    @TableField("education_level_id")
    private Integer educationLevelId;  // 学历层次ID

    @TableField("grade_year")
    private String gradeYear;  // 年级

    @TableField("class_id")
    private Integer classId;  // 班级ID

    @TableField("counselor_id")
    private Integer counselorId;  // 辅导员ID

    // 其他信息
    @TableField("latest_transfer_date")
    private LocalDate latestTransferDate;  // 最近转专业日期

    @TableField("remarks")
    private String remarks;  // 备注

    // 时间戳
    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;  // 创建时间

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;  // 更新时间
}