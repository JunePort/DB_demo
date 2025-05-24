package com.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.mybatisplus.entity.student_major_transfer_history;
import com.mybatisplus.mapper.M_student_major_transfer_history;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class C_student_major_transfer_history {
    private static final Logger logger = LoggerFactory.getLogger(C_student_major_transfer_history.class);
    private final M_student_major_transfer_history m_student_major_transfer_history;

    public C_student_major_transfer_history(M_student_major_transfer_history m_student_major_transfer_history) {
        this.m_student_major_transfer_history = m_student_major_transfer_history;
    }

    // 18.1 添加转专业记录
    @PostMapping("/Transfer_insert")
    public String insertTransferHistory(@RequestBody student_major_transfer_history transferHistory) {
        if (transferHistory.getStudentId() == null || transferHistory.getStudentId().trim().isEmpty()) {
            return "student_id 不能为空";
        }
        if (transferHistory.getNewCollegeCode() == null || transferHistory.getNewCollegeCode().trim().isEmpty()) {
            return "new_college_code 不能为空";
        }
        if (transferHistory.getNewMajorId() == null) {
            return "new_major_id 不能为空";
        }
        if (transferHistory.getNewClassId() == null) {
            return "new_class_id 不能为空";
        }
        if (transferHistory.getTransferDate() == null) {
            return "transfer_date 不能为空";
        }

        try {
            boolean result = m_student_major_transfer_history.insert(transferHistory) > 0;
            return result ? "记录成功!" : "记录失败!";
        } catch (Exception e) {
            logger.error("添加转专业记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 18.2 查询学生转专业历史
    @GetMapping("/Transfer_select_by_student")
    public List<student_major_transfer_history> selectByStudentId(@RequestParam("student_id") String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("student_id 不能为空");
        }

        LambdaQueryWrapper<student_major_transfer_history> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(student_major_transfer_history::getStudentId, studentId)
                .orderByDesc(student_major_transfer_history::getTransferDate);

        return m_student_major_transfer_history.selectList(wrapper);
    }

    // 18.3 查询所有转专业历史
    @GetMapping("/Transfer_select_all")
    public List<student_major_transfer_history> selectAllTransferHistories() {
        LambdaQueryWrapper<student_major_transfer_history> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(student_major_transfer_history::getTransferDate);

        return m_student_major_transfer_history.selectList(wrapper);
    }

    // 保留原有的方法（如果需要）
    @GetMapping("/student-major-transfer-history")
    public List<student_major_transfer_history> getAllStudentMajorTransferHistories() {
        return m_student_major_transfer_history.selectList(null);
    }
}