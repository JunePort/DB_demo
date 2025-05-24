package com.mybatisplus.controller;

import com.mybatisplus.entity.colleges;
import com.mybatisplus.mapper.M_colleges;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class C_colleges {
    private static final Logger logger = LoggerFactory.getLogger(C_colleges.class);
    private final M_colleges m_colleges;

    public C_colleges(M_colleges m_colleges) {
        this.m_colleges = m_colleges;
    }

    // 9.1 添加学院信息
    @RequestMapping(value = "/College_insert", method = {RequestMethod.GET, RequestMethod.POST})
    public String insert(
            @RequestParam("college_code") String collegeCode,
            @RequestParam("college_name") String collegeName) {
        try {
            colleges newCollege = new colleges();
            newCollege.setCollegeCode(collegeCode);
            newCollege.setCollegeName(collegeName);
            boolean result = m_colleges.insert(newCollege) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 9.2 删除学院信息
    @RequestMapping(value = "/College_delete", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(
            @RequestParam("college_code") String collegeCode) {
        try {
            boolean result = m_colleges.deleteById(collegeCode) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 9.3 更新学院信息
    @RequestMapping(value = "/College_update", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(
            @RequestParam("college_code") String collegeCode,
            @RequestParam(value = "college_name", required = false) String collegeName) {
        try {
            colleges existCollege = m_colleges.selectById(collegeCode);
            if (existCollege == null) return "ID不存在";
            if (collegeName != null) {
                existCollege.setCollegeName(collegeName);
            }
            int affected = m_colleges.updateById(existCollege);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 9.4 查询所有学院信息
    @GetMapping("/College_select")
    public List<colleges> select() {
        return m_colleges.selectList(null);
    }

    // 9.5 根据学院编码查询学院信息
    @GetMapping("/College_select_by_id")
    public colleges selectById(@RequestParam("college_code") String collegeCode) {
        return m_colleges.selectById(collegeCode);
    }
}