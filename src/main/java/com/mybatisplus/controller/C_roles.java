package com.mybatisplus.controller;

import com.mybatisplus.entity.roles;
import com.mybatisplus.mapper.M_roles;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/Role")
public class C_roles {
    private static final Logger logger = LoggerFactory.getLogger(C_roles.class);
    private final M_roles m_roles;

    public C_roles(M_roles m_roles) {
        this.m_roles = m_roles;
    }

    // 插入角色
    @PostMapping("/insert")
    public String insertRole(
            @RequestParam String role_name,
            @RequestParam(required = false) String description) {
        if (role_name == null || role_name.trim().isEmpty()) {
            return "role_name 不能为空";
        }
        try {
            roles role = new roles();
            role.setRoleName(role_name);
            if (description != null) {
                role.setDescription(description);
            }
            boolean result = m_roles.insert(role) > 0;
            return result ? "创建成功!" : "创建失败!";
        } catch (Exception e) {
            logger.error("创建角色异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除角色
    @GetMapping("/delete")
    public String deleteRole(@RequestParam int role_id) {
        if (role_id <= 0) {
            return "ID 必须大于 0";
        }
        try {
            boolean result = m_roles.deleteById(role_id) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除角色异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新角色
    @PostMapping("/update")
    public String updateRole(
            @RequestParam int role_id,
            @RequestParam(required = false) String role_name,
            @RequestParam(required = false) String description) {
        if (role_id <= 0) {
            return "ID 必须大于 0";
        }
        try {
            roles existRole = m_roles.selectById(role_id);
            if (existRole == null) return "ID不存在";

            if (role_name != null) {
                existRole.setRoleName(role_name);
            }
            if (description != null) {
                existRole.setDescription(description);
            }
            int affected = m_roles.updateById(existRole);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新角色异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有角色
    @GetMapping("/select")
    public List<roles> selectAllRoles() {
        return m_roles.selectList(null);
    }

    // 按ID查询角色
    @GetMapping("/select_by_id")
    public roles selectRoleById(@RequestParam int role_id) {
        return m_roles.selectById(role_id);
    }
}