package com.mybatisplus.controller;

import com.mybatisplus.entity.admission_methods;
import com.mybatisplus.mapper.M_admission_methods;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class C_admission_methods {
    private static final Logger logger = LoggerFactory.getLogger(C_admission_methods.class);
    private final M_admission_methods m_admission_methods;

    public C_admission_methods(M_admission_methods m_admission_methods) {
        this.m_admission_methods = m_admission_methods;
    }

    // 插入方法
    @RequestMapping("/admission_insert")
    public Map<String, Object> insert(
            @RequestParam("method_name") String methodName) {
        Map<String, Object> response = new HashMap<>();
        try {
            admission_methods newAdmissionMethod = new admission_methods();
            newAdmissionMethod.setMethodName(methodName);
            boolean result = m_admission_methods.insert(newAdmissionMethod) > 0;
            response.put("success", result);
            response.put("message", result ? "插入成功!" : "插入失败!");
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            response.put("success", false);
            response.put("message", "error: " + e.getMessage());
        }
        return response;
    }

    // 删除方法
    @RequestMapping("/admission_delete")
    public Map<String, Object> delete(
            @RequestParam("admission_method_id") int admissionMethodId) {
        Map<String, Object> response = new HashMap<>();
        try {
            boolean result = m_admission_methods.deleteById(admissionMethodId) > 0;
            response.put("success", result);
            response.put("message", result ? "删除成功" : "ID不存在");
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            response.put("success", false);
            response.put("message", "error: " + e.getMessage());
        }
        return response;
    }

    // 更新方法
    @RequestMapping("/admission_update")
    public Map<String, Object> update(
            @RequestParam("admission_method_id") int admissionMethodId,
            @RequestParam(value = "method_name", required = false) String methodName) {
        Map<String, Object> response = new HashMap<>();
        try {
            admission_methods existAdmissionMethod = m_admission_methods.selectById(admissionMethodId);
            if (existAdmissionMethod == null) {
                response.put("success", false);
                response.put("message", "ID不存在");
                return response;
            }
            if (methodName != null) {
                existAdmissionMethod.setMethodName(methodName);
            }
            int affected = m_admission_methods.updateById(existAdmissionMethod);
            response.put("success", affected > 0);
            response.put("message", affected > 0 ? "更新成功" : "更新失败");
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            response.put("success", false);
            response.put("message", "error: " + e.getMessage());
        }
        return response;
    }

    // 查询方法
    @RequestMapping("/admission_select")
    public List<admission_methods> select() {
        return m_admission_methods.selectList(null);
    }
}