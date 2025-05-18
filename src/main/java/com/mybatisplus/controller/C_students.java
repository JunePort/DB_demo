package com.mybatisplus.controller;

import java.util.Date;
import java.util.List;
import com.mybatisplus.entity.students;
import com.mybatisplus.mapper.M_students;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class C_students {
    final M_students m_students;
    public C_students(M_students m_students) {
        this.m_students = m_students;
    }

    // 新增学生（注意student_id是手动指定的varchar主键）
    @RequestMapping("/S_insert")
    public String insert(
            @RequestParam("student_id") String studentId,
            @RequestParam("name") String name,
            @RequestParam("gender_id") int genderId,
            @RequestParam("major_id") int majorId,
            @RequestParam("class_id") int classId,
            @RequestParam("enrollment_date") Date enrollmentDate,
            @RequestParam("date_of_birth") Date dateOfBirth,
            @RequestParam("contact_phone") String contactPhone,
            @RequestParam("email") String email) {
        try {
            students student = new students(studentId, name, genderId, majorId, classId,
                    enrollmentDate, dateOfBirth, contactPhone, email,
                    null, null); // 最后两个时间戳由数据库自动生成
            boolean result = m_students.insert(student) > 0;
            return result ? "添加成功!" : "操作失败";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 删除学生
    @RequestMapping("/S_delete")
    public String delete(
            @RequestParam("student_id") String studentId) {
        try {
            boolean result = m_students.deleteById(studentId) > 0;
            return result ? "删除成功" : "学生ID不存在";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 更新学生信息（支持部分字段更新）
    @RequestMapping("/S_update")
    public String update(
            @RequestParam("student_id") String studentId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "gender_id", required = false) Integer genderId,
            @RequestParam(value = "major_id", required = false) Integer majorId,
            @RequestParam(value = "class_id", required = false) Integer classId,
            @RequestParam(value = "enrollment_date", required = false) Date enrollmentDate,
            @RequestParam(value = "date_of_birth", required = false) Date dateOfBirth,
            @RequestParam(value = "contact_phone", required = false) String contactPhone,
            @RequestParam(value = "email", required = false) String email) {
        try {
            students existStudent = m_students.selectById(studentId);
            if (existStudent == null) return "学生不存在";

            if (name != null) existStudent.setName(name);
            if (genderId != null) existStudent.setGenderId(genderId);
            if (majorId != null) existStudent.setMajorId(majorId);
            if (classId != null) existStudent.setClassId(classId);
            if (enrollmentDate != null) existStudent.setEnrollmentDate(enrollmentDate);
            if (dateOfBirth != null) existStudent.setDateOfBirth(dateOfBirth);
            if (contactPhone != null) existStudent.setContactPhone(contactPhone);
            if (email != null) existStudent.setEmail(email);

            int affected = m_students.updateById(existStudent);
            return affected > 0 ? "更新成功" : "无变更";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 查询所有学生
    @RequestMapping("/S_select")
    public List<students> select() {
        return m_students.selectList(null);
    }
}

// http://localhost:8081/S_insert?student_id=2023001&name=张三&gender_id=1&major_id=5&class_id=10&enrollment_date=2023-09-01&date_of_birth=2005-05-15&contact_phone=13800138000&email=zhangsan@example.com
// http://localhost:8081/S_update?student_id=2023001&contact_phone=13812345678
// http://localhost:8081/S_delete?student_id=2023001
// http://localhost:8081/S_select