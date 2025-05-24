package com.mybatisplus.controller;

import com.mybatisplus.entity.marital_statuses;
import com.mybatisplus.mapper.M_marital_statuses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/marital-statuses")
public class C_marital_statuses {
    private static final Logger logger = LoggerFactory.getLogger(C_marital_statuses.class);
    private final M_marital_statuses m_marital_statuses;

    public C_marital_statuses(M_marital_statuses m_marital_statuses) {
        this.m_marital_statuses = m_marital_statuses;
    }

    // 创建婚姻状态
    @PostMapping
    public String createMaritalStatus(@RequestBody marital_statuses status) {
        if (status.getStatusDescription() == null || status.getStatusDescription().trim().isEmpty()) {
            return "status_description 不能为空";
        }
        if (status.getStatusCode() == null) {
            return "status_code 不能为空";
        }
        try {
            boolean result = m_marital_statuses.insert(status) > 0;
            return result ? "创建成功!" : "创建失败!";
        } catch (Exception e) {
            logger.error("创建婚姻状态异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除婚姻状态
    @DeleteMapping("/{id}")
    public String deleteMaritalStatus(@PathVariable("id") int id) {
        if (id <= 0) {
            return "ID 必须大于 0";
        }
        try {
            boolean result = m_marital_statuses.deleteById(id) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除婚姻状态异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新婚姻状态
    @PutMapping("/{id}")
    public String updateMaritalStatus(@PathVariable("id") int id,
                                      @RequestBody marital_statuses updatedStatus) {
        if (id <= 0) {
            return "ID 必须大于 0";
        }
        try {
            marital_statuses existStatus = m_marital_statuses.selectById(id);
            if (existStatus == null) return "ID不存在";

            if (updatedStatus.getStatusDescription() != null) {
                existStatus.setStatusDescription(updatedStatus.getStatusDescription());
            }
            if (updatedStatus.getStatusCode() != null) {
                existStatus.setStatusCode(updatedStatus.getStatusCode());
            }

            int affected = m_marital_statuses.updateById(existStatus);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新婚姻状态异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有婚姻状态
    @GetMapping
    public List<marital_statuses> getAllMaritalStatuses() {
        return m_marital_statuses.selectList(null);
    }
}