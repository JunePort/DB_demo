package com.mybatisplus.controller;

import java.util.List;
import com.mybatisplus.entity.majors;
import com.mybatisplus.mapper.M_majors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class C_majors {
    final M_majors m_majors;
    public C_majors(M_majors m_majors) {
        this.m_majors = m_majors;
    }

    @RequestMapping("/M_insert")
    public String insert(
            @RequestParam("major_id") int majorId,
            @RequestParam("major_name") String majorName,
            @RequestParam("department") String department) {
        try {
            majors newMajor = new majors(majorId, majorName, department);
            boolean result = m_majors.insert(newMajor) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @RequestMapping("/M_delete")
    public String delete(
            @RequestParam("major_id") int majorId) {
        try {
            boolean result = m_majors.deleteById(majorId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @RequestMapping("/M_update")
    public String update(
            @RequestParam("major_id") int majorId,
            @RequestParam(value = "major_name", required = false) String majorName,
            @RequestParam(value = "department", required = false) String department) {
        try {
            majors existMajor = m_majors.selectById(majorId);
            if (existMajor == null) return "ID不存在";
            if (majorName != null) existMajor.setMajorName(majorName);
            if (department != null) existMajor.setDepartment(department);
            int affected = m_majors.updateById(existMajor);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @RequestMapping("/M_select")
    public List<majors> select() {
        return m_majors.selectList(null);
    }
}

// http://localhost:8081/M_insert?major_name=Computer Science&department=Engineering
// http://localhost:8081/M_delete?major_id=1
// http://localhost:8081/M_update?major_id=1&department=Computer Science
// http://localhost:8081/M_select