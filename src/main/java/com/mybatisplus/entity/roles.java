package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("roles")  // 精确映射数据库表
public class roles {
    @TableId(value = "role_id", type = IdType.AUTO)
    private Integer roleId;  // 自增主键

    @TableField("role_name")
    private String roleName;  // 角色名称（带索引）

    @TableField("description")
    private String description;  // 角色描述（text类型）
}