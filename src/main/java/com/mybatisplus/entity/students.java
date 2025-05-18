package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class students {
    @TableId(value = "role_id", type = IdType.AUTO)
    private String studentId; // 学生ID
    private String name;      // 姓名
    private int genderId; // 性别ID
    private int majorId;  // 专业ID
    private int classId;  // 班级ID
    private Date enrollmentDate; // 入学日期
    private Date dateOfBirth;    // 出生日期
    private String contactPhone; // 联系电话
    private String email;        // 邮箱
    private Date createdAt;      // 创建时间
    private Date updatedAt;      // 更新时间
}