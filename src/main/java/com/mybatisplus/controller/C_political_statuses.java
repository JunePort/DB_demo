package com.mybatisplus.controller;

import com.mybatisplus.entity.political_statuses;
import com.mybatisplus.mapper.M_political_statuses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/political-statuses")
public class C_political_statuses {
    private static final Logger logger = LoggerFactory.getLogger(C_political_statuses.class);
    private final M_political_statuses m_political_statuses;

    public C_political_statuses(M_political_statuses m_political_statuses) {
        this.m_political_statuses = m_political_statuses;
    }

    // 创建政治面貌记录
    @PostMapping
    public String createPoliticalStatus(@RequestBody political_statuses status) {
        if (status.getStatusName() == null || status.getStatusName().trim().isEmpty()) {
            return "status_name 不能为空";
        }
        try {
            boolean result = m_political_statuses.insert(status) > 0;
            return result ? "创建成功!" : "创建失败!";
        } catch (Exception e) {
            logger.error("创建政治面貌记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除政治面貌记录
    @DeleteMapping("/{id}")
    public String deletePoliticalStatus(@PathVariable("id") int id) {
        if (id <= 0) {
            return "ID 必须大于 0";
        }
        try {
            boolean result = m_political_statuses.deleteById(id) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除政治面貌记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新政治面貌记录
    @PutMapping("/{id}")
    public String updatePoliticalStatus(@PathVariable("id") int id,
                                        @RequestBody political_statuses updatedStatus) {
        if (id <= 0) {
            return "ID 必须大于 0";
        }
        try {
            political_statuses existStatus = m_political_statuses.selectById(id);
            if (existStatus == null) return "ID不存在";
            if (updatedStatus.getStatusName() != null) {
                existStatus.setStatusName(updatedStatus.getStatusName());
            }
            int affected = m_political_statuses.updateById(existStatus);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新政治面貌记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有政治面貌记录
    @GetMapping
    public List<political_statuses> getAllPoliticalStatuses() {
        return m_political_statuses.selectList(null);
    }
}