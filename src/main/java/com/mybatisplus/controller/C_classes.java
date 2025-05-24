package com.mybatisplus.controller;

import java.util.List;
import com.mybatisplus.entity.classes;
import com.mybatisplus.mapper.M_classes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/C")  // 统一前缀
public class C_classes {
    private static final Logger logger = LoggerFactory.getLogger(C_classes.class);
    final M_classes m_classes;

    public C_classes(M_classes m_classes) {
        this.m_classes = m_classes;
    }

    @PostMapping("/insert")  // 明确使用POST
    public String insert(
            @RequestParam(value = "class_id", required = false) Integer classId,  // 改为可选
            @RequestParam("class_name") String className,
            @RequestParam("college_code") String collegeCode,
            @RequestParam("major_id") int majorId,
            @RequestParam("grade_year") String gradeYear) {
        try {
            classes newClass = new classes(classId, className, collegeCode, majorId, gradeYear);
            boolean result = m_classes.insert(newClass) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    @DeleteMapping("/delete")  // 明确使用DELETE
    public String delete(@RequestParam("class_id") int classId) {
        try {
            boolean result0 = m_classes.deleteById(classId) > 0;
            return result0 ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    @PutMapping("/update")  // 明确使用PUT
    public String update(
            @RequestParam("class_id") int classId,
            @RequestParam(value = "class_name", required = false) String className,
            @RequestParam(value = "college_code", required = false) String collegeCode,
            @RequestParam(value = "major_id", required = false) Integer majorId,
            @RequestParam(value = "grade_year", required = false) String gradeYear) {
        try {
            classes existClass = m_classes.selectById(classId);
            if (existClass == null) return "ID不存在";

            if (className != null) existClass.setClassName(className);
            if (collegeCode != null) existClass.setCollegeCode(collegeCode);
            if (majorId != null) existClass.setMajorId(majorId);
            if (gradeYear != null) existClass.setGradeYear(gradeYear);

            int affected = m_classes.updateById(existClass);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    @GetMapping("/select")  // 明确使用GET
    public List<classes> select() {
        return m_classes.selectList(null);
    }
}