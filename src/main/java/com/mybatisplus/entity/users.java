package com.mybatisplus.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;

import java.time.LocalDateTime;

@Data
@TableName("users")
public class users {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;  // 自增主键


    private String username;


    @TableField("password_hash")
    private String passwordHash;  // BCrypt加密存储


    @TableField("role_id")
    private Integer roleId;


    @TableField("student_id_link")
    private String studentIdLink;  // 关联学生ID


    @TableField("full_name")
    private String fullName;


    private String email;

    @TableField("is_active")
    private Boolean isActive = true;  // 默认激活状态

    @TableField(value = "created_at", fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @TableField(value = "updated_at", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;


}