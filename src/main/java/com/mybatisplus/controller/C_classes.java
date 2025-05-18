package com.mybatisplus.controller;


import java.util.List;
import com.mybatisplus.entity.classes;
import com.mybatisplus.mapper.M_classes;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;

@CrossOrigin
@RestController
public class C_classes {
    final M_classes m_classes;
    public C_classes(M_classes m_classes) {
        this.m_classes = m_classes;
    }
    // 原insert方法不变
    @RequestMapping("/C_insert")
    public String insert(
            @RequestParam("class_id") int classId,
            @RequestParam("class_name") String className,
            @RequestParam("major_id") int majorId,
            @RequestParam("grade_year") Year gradeYear) {
        try {
            classes newClass = new classes(classId, className, majorId, gradeYear);
            boolean result = m_classes.insert(newClass) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    // 新增delete方法
    @RequestMapping("/C_delete")
    public String delete(
            @RequestParam("class_id") int classId) {
        try {
            boolean result0 = m_classes.deleteById(classId)>0;
            return  result0 ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    // 新增update方法
    @RequestMapping("/C_update")
    public String update(
            @RequestParam("class_id") int classId,
            @RequestParam(value = "class_name", required = false) String className,
            @RequestParam(value = "major_id", required = false) Integer majorId,
            @RequestParam(value = "grade_year", required = false) Year gradeYear) {
        try {
            // 先查询原有数据
            classes existClass = m_classes.selectById(classId);
            if (existClass == null) return "ID不存在";
            // 仅更新非空参数
            if (className != null) existClass.setClassName(className);
            if (majorId != null) existClass.setMajorId(majorId);
            if (gradeYear != null) existClass.setGradeYear(gradeYear);
            int affected = m_classes.updateById(existClass);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }
    // 原select方法不变
    @RequestMapping("/C_select")
    public List<classes> select() {
        return m_classes.selectList(null);
    }
}
//http://localhost:8081/C_insert?class_id=4&class_name=Class1&major_id=5&grade_year=2023
//http://localhost:8081/C_delete?class_id=4
//http://localhost:8081/C_update?class_id=4&class_name=NewClass
//http://localhost:8081/C_select
