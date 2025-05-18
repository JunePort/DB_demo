package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class users {
    @TableId(value = "user_id", type = IdType.AUTO)
    private int userId;
    private String username;
    private String passwordHash;
    private int roleId;
    private String studentIdLink;
    private String fullName;
    private int isActive;
    private Date createdAt;
    private Date updatedAt;
}