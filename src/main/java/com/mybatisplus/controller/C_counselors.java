package com.mybatisplus.controller;

import com.mybatisplus.entity.counselors;
import com.mybatisplus.mapper.M_counselors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class C_counselors {
    private static final Logger logger = LoggerFactory.getLogger(C_counselors.class);
    private final M_counselors m_counselors;

    public C_counselors(M_counselors m_counselors) {
        this.m_counselors = m_counselors;
    }

    // 插入方法
    @RequestMapping("/Counselor_insert")
    public String insert(
            @RequestParam("counselor_name") String counselorName,
            @RequestParam(value = "contact_info", required = false) String contactInfo,
            @RequestParam(value = "college_code_assignment", required = false) String collegeCodeAssignment) {
        try {
            counselors newCounselor = new counselors();
            newCounselor.setCounselorName(counselorName);
            newCounselor.setContactInfo(contactInfo);
            newCounselor.setCollegeCodeAssignment(collegeCodeAssignment);
            boolean result = m_counselors.insert(newCounselor) > 0;
            return result? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除方法
    @RequestMapping("/Counselor_delete")
    public String delete(
            @RequestParam("counselor_id") int counselorId) {
        try {
            boolean result = m_counselors.deleteById(counselorId) > 0;
            return result? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新方法
    @RequestMapping("/Counselor_update")
    public String update(
            @RequestParam("counselor_id") int counselorId,
            @RequestParam(value = "counselor_name", required = false) String counselorName,
            @RequestParam(value = "contact_info", required = false) String contactInfo,
            @RequestParam(value = "college_code_assignment", required = false) String collegeCodeAssignment) {
        try {
            counselors existCounselor = m_counselors.selectById(counselorId);
            if (existCounselor == null) return "ID不存在";
            if (counselorName != null) {
                existCounselor.setCounselorName(counselorName);
            }
            if (contactInfo != null) {
                existCounselor.setContactInfo(contactInfo);
            }
            if (collegeCodeAssignment != null) {
                existCounselor.setCollegeCodeAssignment(collegeCodeAssignment);
            }
            int affected = m_counselors.updateById(existCounselor);
            return affected > 0? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有辅导员
    @RequestMapping("/Counselor_select")
    public List<counselors> selectAll() {
        return m_counselors.selectList(null);
    }

    // 按ID查询辅导员
    @RequestMapping("/Counselor_select_by_id")
    public Object selectById(@RequestParam("counselor_id") int counselorId) {
        try {
            counselors counselor = m_counselors.selectById(counselorId);
            if (counselor == null) {
                return "ID不存在";
            }
            return counselor;
        } catch (Exception e) {
            logger.error("按ID查询操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }
}