package com.mybatisplus.controller;

import java.util.List;
import com.mybatisplus.entity.roles;
import com.mybatisplus.mapper.M_roles;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class C_roles {
    final M_roles m_roles;
    public C_roles(M_roles m_roles) {
        this.m_roles = m_roles;
    }

    // 新增角色（自增ID无需传参）
    @RequestMapping("/R_insert")
    public String insert(
            @RequestParam("role_id") int roleId,
            @RequestParam("role_name") String roleName,
            @RequestParam("description") String description) {
        try {
            roles newRole = new roles(roleId, roleName, description);
            boolean result = m_roles.insert(newRole) > 0;
            return result ? "角色创建成功!" : "操作失败";
        } catch (Exception e) {
            return "错误: " + e.getMessage();
        }
    }

    // 删除角色
    @RequestMapping("/R_delete")
    public String delete(
            @RequestParam("role_id") int roleId) {
        try {
            boolean result = m_roles.deleteById(roleId) > 0;
            return result ? "角色已删除" : "角色ID不存在";
        } catch (Exception e) {
            return "错误: " + e.getMessage();
        }
    }

    // 更新角色信息
    @RequestMapping("/R_update")
    public String update(
            @RequestParam("role_id") int roleId,
            @RequestParam(value = "role_name", required = false) String roleName,
            @RequestParam(value = "description", required = false) String description) {
        try {
            roles existRole = m_roles.selectById(roleId);
            if (existRole == null) return "角色不存在";
            if (roleName != null) existRole.setRoleName(roleName);
            if (description != null) existRole.setDescription(description);
            int affected = m_roles.updateById(existRole);
            return affected > 0 ? "更新成功" : "无变更";
        } catch (Exception e) {
            return "错误: " + e.getMessage();
        }
    }

    // 查询所有角色
    @RequestMapping("/R_select")
    public List<roles> select() {
        return m_roles.selectList(null);
    }
}

// http://localhost:8081/R_insert?role_name=管理员&description=系统管理员权限
// http://localhost:8081/R_delete?role_id=5
// http://localhost:8081/R_update?role_id=1&description=普通用户权限
// http://localhost:8081/R_select