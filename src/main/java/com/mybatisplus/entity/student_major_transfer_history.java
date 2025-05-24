package com.mybatisplus.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
        import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("student_major_transfer_history")
public class student_major_transfer_history {
    @TableId(value = "transfer_id", type = IdType.AUTO)
    private Integer transferId;  // 自增主键

    @TableField("student_id")
    private String studentId;  // 学号

    // 原专业信息
    @TableField("previous_college_code")
    private String previousCollegeCode;

    @TableField("previous_major_id")
    private Integer previousMajorId;

    @TableField("previous_class_id")
    private Integer previousClassId;

    // 新专业信息
    @TableField("new_college_code")
    private String newCollegeCode;

    @TableField("new_major_id")
    private Integer newMajorId;

    @TableField("new_class_id")
    private Integer newClassId;

    @TableField("transfer_date")
    private LocalDate transferDate;  // 转专业日期

    @TableField("reason")
    private String reason;  // 转专业原因

    @TableField("approved_by")
    private String approvedBy;  // 审批人

    @TableField(value = "recorded_at", fill = FieldFill.INSERT)
    private LocalDateTime recordedAt;  // 记录时间戳
}