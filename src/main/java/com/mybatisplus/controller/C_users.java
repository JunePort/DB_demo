package com.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.entity.users;
import com.mybatisplus.mapper.M_users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/users")
public class C_users {
    private static final Logger logger = LoggerFactory.getLogger(C_users.class);
    private final M_users m_users;

    public C_users(M_users m_users) {
        this.m_users = m_users;
    }

    // 16.1 添加用户信息 (注册)
    @GetMapping("/User_insert")
    public String createUserByGet(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam Integer role_id,
            @RequestParam(required = false) String student_id_link,
            @RequestParam(required = false) String full_name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean is_active) {

        users user = new users();
        user.setUsername(username);
        // 实际项目中应该对密码进行哈希处理
        user.setPasswordHash(password);
        user.setRoleId(role_id);
        user.setStudentIdLink(student_id_link);
        user.setFullName(full_name);
        user.setEmail(email);
        user.setIsActive(is_active != null ? is_active : true);

        return createUser(user);
    }

    @PostMapping("/User_insert")
    public String createUserByPost(@RequestBody users user) {
        // 实际项目中应该对密码进行哈希处理
        if (user.getPasswordHash() == null) {
            return "password 不能为空";
        }
        return createUser(user);
    }

    private String createUser(users user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            return "username 不能为空";
        }
        if (user.getRoleId() == null) {
            return "role_id 不能为空";
        }
        try {
            boolean result = m_users.insert(user) > 0;
            return result ? "用户创建成功!" : "用户创建失败!";
        } catch (Exception e) {
            logger.error("创建用户异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 16.2 删除用户信息
    @GetMapping("/User_delete")
    public String deleteUserByGet(@RequestParam("user_id") int userId) {
        return deleteUser(userId);
    }

    @DeleteMapping("/User_delete")
    public String deleteUserByDelete(@RequestParam("user_id") int userId) {
        return deleteUser(userId);
    }

    private String deleteUser(int userId) {
        if (userId <= 0) {
            return "user_id 必须大于 0";
        }
        try {
            boolean result = m_users.deleteById(userId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除用户异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 16.3 更新用户信息
    @GetMapping("/User_update")
    public String updateUserByGet(
            @RequestParam("user_id") int userId,
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer role_id,
            @RequestParam(required = false) String student_id_link,
            @RequestParam(required = false) String full_name,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) Boolean is_active) {

        users updatedUser = new users();
        updatedUser.setUsername(username);
        updatedUser.setRoleId(role_id);
        updatedUser.setStudentIdLink(student_id_link);
        updatedUser.setFullName(full_name);
        updatedUser.setEmail(email);
        updatedUser.setIsActive(is_active);

        return updateUser(userId, updatedUser);
    }

    @PutMapping("/User_update")
    public String updateUserByPut(
            @RequestParam("user_id") int userId,
            @RequestBody users updatedUser) {
        return updateUser(userId, updatedUser);
    }

    private String updateUser(int userId, users updatedUser) {
        if (userId <= 0) {
            return "user_id 必须大于 0";
        }
        try {
            users existUser = m_users.selectById(userId);
            if (existUser == null) return "ID不存在";

            if (updatedUser.getUsername() != null) {
                existUser.setUsername(updatedUser.getUsername());
            }
            if (updatedUser.getRoleId() != null) {
                existUser.setRoleId(updatedUser.getRoleId());
            }
            if (updatedUser.getStudentIdLink() != null) {
                existUser.setStudentIdLink(updatedUser.getStudentIdLink());
            }
            if (updatedUser.getFullName() != null) {
                existUser.setFullName(updatedUser.getFullName());
            }
            if (updatedUser.getEmail() != null) {
                existUser.setEmail(updatedUser.getEmail());
            }
            if (updatedUser.getIsActive() != null) {
                existUser.setIsActive(updatedUser.getIsActive());
            }

            int affected = m_users.updateById(existUser);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新用户异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 16.4 查询所有用户信息
    @GetMapping("/User_select")
    public List<users> getAllUsers() {
        LambdaQueryWrapper<users> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(users.class, info ->
                !info.getColumn().equals("password_hash")); // 不查询password_hash字段
        return m_users.selectList(wrapper);
    }

    // 16.5 根据用户ID查询用户信息
    @GetMapping("/User_select_by_id")
    public users getUserById(@RequestParam("user_id") int userId) {
        if (userId <= 0) {
            return null;
        }
        LambdaQueryWrapper<users> wrapper = new LambdaQueryWrapper<>();
        wrapper.select(users.class, info ->
                        !info.getColumn().equals("password_hash")) // 不查询password_hash字段
                .eq(users::getUserId, userId);
        return m_users.selectOne(wrapper);
    }
}