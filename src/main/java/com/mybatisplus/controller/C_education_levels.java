package com.mybatisplus.controller;

import com.mybatisplus.entity.education_levels;
import com.mybatisplus.mapper.M_education_levels;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/education-levels")
@CrossOrigin
public class C_education_levels {
    private static final Logger logger = LoggerFactory.getLogger(C_education_levels.class);
    private final M_education_levels m_education_levels;

    public C_education_levels(M_education_levels m_education_levels) {
        this.m_education_levels = m_education_levels;
    }

    // 获取所有培养层次
    @GetMapping
    public ResponseEntity<List<education_levels>> getAll() {
        try {
            List<education_levels> list = m_education_levels.selectList(null);
            return ResponseEntity.ok(list);
        } catch (Exception e) {
            logger.error("查询所有培养层次失败", e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // 根据ID获取单个培养层次
    @GetMapping("/{id}")
    public ResponseEntity<education_levels> getById(@PathVariable("id") int educationLevelId) {
        try {
            education_levels level = m_education_levels.selectById(educationLevelId);
            return level != null ? ResponseEntity.ok(level) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("查询培养层次失败，ID: {}", educationLevelId, e);
            return ResponseEntity.internalServerError().build();
        }
    }

    // 创建培养层次
    @PostMapping
    public ResponseEntity<String> create(@RequestBody education_levels level) {
        try {
            if (level.getLevelName() == null || level.getLevelName().trim().isEmpty()) {
                return ResponseEntity.badRequest().body("level_name不能为空");
            }
            int result = m_education_levels.insert(level);
            return result > 0 ? ResponseEntity.ok("创建成功") : ResponseEntity.internalServerError().body("创建失败");
        } catch (Exception e) {
            logger.error("创建培养层次失败", e);
            return ResponseEntity.internalServerError().body("错误: " + e.getMessage());
        }
    }

    // 更新培养层次
    @PutMapping("/{id}")
    public ResponseEntity<String> update(
            @PathVariable("id") int educationLevelId,
            @RequestBody education_levels level) {
        try {
            education_levels existLevel = m_education_levels.selectById(educationLevelId);
            if (existLevel == null) {
                return ResponseEntity.notFound().build();
            }
            if (level.getLevelName() != null && !level.getLevelName().trim().isEmpty()) {
                existLevel.setLevelName(level.getLevelName());
            }
            int affected = m_education_levels.updateById(existLevel);
            return affected > 0 ? ResponseEntity.ok("更新成功") : ResponseEntity.internalServerError().body("更新失败");
        } catch (Exception e) {
            logger.error("更新培养层次失败，ID: {}", educationLevelId, e);
            return ResponseEntity.internalServerError().body("错误: " + e.getMessage());
        }
    }

    // 删除培养层次
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") int educationLevelId) {
        try {
            if (educationLevelId <= 0) {
                return ResponseEntity.badRequest().body("ID必须大于0");
            }
            int affected = m_education_levels.deleteById(educationLevelId);
            return affected > 0 ? ResponseEntity.ok("删除成功") : ResponseEntity.notFound().build();
        } catch (Exception e) {
            logger.error("删除培养层次失败，ID: {}", educationLevelId, e);
            return ResponseEntity.internalServerError().body("错误: " + e.getMessage());
        }
    }
}