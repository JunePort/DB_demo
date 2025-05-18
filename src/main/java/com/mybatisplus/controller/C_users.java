package com.mybatisplus.controller;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import com.mybatisplus.entity.users;
import com.mybatisplus.mapper.M_users;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class C_users {
    final M_users m_users;
    public C_users(M_users m_users) {
        this.m_users = m_users;
    }

    // 新增用户（自增ID无需传参）
    @RequestMapping("/U_insert")
    public String insert(
            @RequestParam("user_id") int userId,
            @RequestParam("username") String username,
            @RequestParam("password_hash") String passwordHash,
            @RequestParam("role_id") int roleId,
            @RequestParam("full_name") String fullName,
            @RequestParam(value = "student_id_link", required = false) String studentIdLink,
            @RequestParam(value = "is_active", defaultValue = "1") byte isActive,
            @RequestParam("created_at") Date createdAt,
            @RequestParam("updated_at") Date updatedAt)
    {
        try {
            users newUser = new users(userId, username, passwordHash, roleId,
                    studentIdLink, fullName, isActive,
                    createdAt, updatedAt);
            boolean result = m_users.insert(newUser) > 0;
            return result ? "用户创建成功!" : "操作失败";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 删除用户
    @RequestMapping("/U_delete")
    public String delete(@RequestParam("user_id") int userId) {
        try {
            boolean result = m_users.deleteById(userId) > 0;
            return result ? "用户已删除" : "用户ID不存在";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 更新用户信息（支持部分更新）
    @RequestMapping("/U_update")
    public String update(
            @RequestParam("user_id") int userId,
            @RequestParam(value = "username", required = false) String username,
            @RequestParam(value = "password_hash", required = false) String passwordHash,
            @RequestParam(value = "role_id", required = false) Integer roleId,
            @RequestParam(value = "student_id_link", required = false) String studentIdLink,
            @RequestParam(value = "full_name", required = false) String fullName,
            @RequestParam(value = "is_active", required = false) Byte isActive,
            @RequestParam("created_at") Date createdAt,
            @RequestParam("updated_at") Date updatedAt) {
        try {
            users existUser = m_users.selectById(userId);
            if (existUser == null) return "用户不存在";

            if (username != null) existUser.setUsername(username);
            if (passwordHash != null) existUser.setPasswordHash(passwordHash);
            if (roleId != null) existUser.setRoleId(roleId);
            if (studentIdLink != null) existUser.setStudentIdLink(studentIdLink);
            if (fullName != null) existUser.setFullName(fullName);
            if (isActive != null) existUser.setIsActive(isActive);
            if (createdAt!=null) existUser.setCreatedAt(createdAt);
            if (updatedAt!=null) existUser.setUpdatedAt(updatedAt);

            int affected = m_users.updateById(existUser);
            return affected > 0 ? "更新成功" : "无变更";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 查询所有用户
    @RequestMapping("/U_select")
    public List<users> select() {
        return m_users.selectList(null);
    }
}

//  http://localhost:8081/U_insert?username=john_doe&password_hash=5f4dcc3b5aa765d61d8327deb882cf99&role_id=2&full_name=John Doe
// http://localhost:8081/U_insert?username=stu_001&password_hash=...&role_id=3&student_id_link=2023001&full_name=张三
// http://localhost:8081/U_update?user_id=5&is_active=0
// http://localhost:8081/U_update?user_id=2&password_hash=新哈希值
// http://localhost:8081/U_select