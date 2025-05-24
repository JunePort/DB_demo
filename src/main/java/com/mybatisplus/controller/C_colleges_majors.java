package com.mybatisplus.controller;

import com.mybatisplus.entity.colleges_majors;
import com.mybatisplus.mapper.M_colleges_majors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class C_colleges_majors {
    private static final Logger logger = LoggerFactory.getLogger(C_colleges_majors.class);
    private final M_colleges_majors m_colleges_majors;

    public C_colleges_majors(M_colleges_majors m_colleges_majors) {
        this.m_colleges_majors = m_colleges_majors;
    }

    // 12.1 添加学院与专业关联
    @RequestMapping(value = "/CM_insert", method = {RequestMethod.GET, RequestMethod.POST})
    public String insert(
            @RequestParam("college_code") String collegeCode,
            @RequestParam("major_id") int majorId) {
        try {
            colleges_majors newRecord = new colleges_majors();
            newRecord.setCollegeCode(collegeCode);
            newRecord.setMajorId(majorId);
            boolean result = m_colleges_majors.insert(newRecord) > 0;
            return result ? "关联成功!" : "关联失败!";
        } catch (Exception e) {
            logger.error("关联操作异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 12.2 删除学院与专业关联
    @RequestMapping(value = "/CM_delete", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(
            @RequestParam("college_code") String collegeCode,
            @RequestParam("major_id") int majorId) {
        try {
            Map<String, Object> condition = new HashMap<>();
            condition.put("college_code", collegeCode);
            condition.put("major_id", majorId);

            boolean result = m_colleges_majors.deleteByMap(condition) > 0;
            return result ? "解除关联成功" : "关联不存在";
        } catch (Exception e) {
            logger.error("解除关联操作异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 12.3 查询所有学院与专业关联
    @GetMapping("/CM_select")
    public List<colleges_majors> selectAll() {
        return m_colleges_majors.selectList(null);
    }

    // 12.4 查询指定学院的所有专业关联
    @GetMapping("/CM_select_by_college")
    public List<colleges_majors> selectByCollege(
            @RequestParam("college_code") String collegeCode) {
        Map<String, Object> condition = new HashMap<>();
        condition.put("college_code", collegeCode);
        return m_colleges_majors.selectByMap(condition);
    }
}