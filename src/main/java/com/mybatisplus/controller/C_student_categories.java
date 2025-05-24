package com.mybatisplus.controller;

import com.mybatisplus.entity.student_categories;
import com.mybatisplus.mapper.M_student_categories;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/student-categories")
public class C_student_categories {
    private static final Logger logger = LoggerFactory.getLogger(C_student_categories.class);
    private final M_student_categories m_student_categories;

    public C_student_categories(M_student_categories m_student_categories) {
        this.m_student_categories = m_student_categories;
    }

    // 创建学生类别
    @PostMapping
    public String createStudentCategory(@RequestBody student_categories category) {
        if (category.getCategoryName() == null || category.getCategoryName().trim().isEmpty()) {
            return "category_name 不能为空";
        }
        try {
            boolean result = m_student_categories.insert(category) > 0;
            return result? "创建成功!" : "创建失败!";
        } catch (Exception e) {
            logger.error("创建学生类别异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除学生类别
    @DeleteMapping("/{id}")
    public String deleteStudentCategory(@PathVariable("id") int id) {
        if (id <= 0) {
            return "student_category_id 必须大于 0";
        }
        try {
            boolean result = m_student_categories.deleteById(id) > 0;
            return result? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除学生类别异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新学生类别
    @PutMapping("/{id}")
    public String updateStudentCategory(@PathVariable("id") int id,
                                        @RequestBody student_categories updatedCategory) {
        if (id <= 0) {
            return "student_category_id 必须大于 0";
        }
        try {
            student_categories existCategory = m_student_categories.selectById(id);
            if (existCategory == null) return "ID不存在";
            if (updatedCategory.getCategoryName() != null) {
                existCategory.setCategoryName(updatedCategory.getCategoryName());
            }
            int affected = m_student_categories.updateById(existCategory);
            return affected > 0? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新学生类别异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有学生类别
    @GetMapping
    public List<student_categories> getAllStudentCategories() {
        return m_student_categories.selectList(null);
    }
}