package com.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.entity.students;
import com.mybatisplus.mapper.M_students;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/students")
public class C_students {
    private static final Logger logger = LoggerFactory.getLogger(C_students.class);
    private final M_students m_students;

    public C_students(M_students m_students) {
        this.m_students = m_students;
    }

    // 14.1 添加学生信息
    @PostMapping("/Student_insert")
    public String createStudent(@RequestBody students student) {
        // 检查所有必需字段
        if (student.getStudentId() == null || student.getStudentId().trim().isEmpty()) {
            return "student_id 不能为空";
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            return "name 不能为空";
        }
        if (student.getGenderId() == null) {
            return "gender_id 不能为空";
        }
        if (student.getCountryId() == null) {
            return "country_id 不能为空";
        }
        if (student.getBirthDate() == null) {
            return "birth_date 不能为空";
        }
        if (student.getEthnicityId() == null) {
            return "ethnicity_id 不能为空";
        }
        if (student.getMaritalStatusId() == null) {
            return "marital_status_id 不能为空";
        }
        if (student.getPoliticalStatusId() == null) {
            return "political_status_id 不能为空";
        }
        if (student.getEnrollmentDate() == null) {
            return "enrollment_date 不能为空";
        }
        if (student.getAdmissionMethodId() == null) {
            return "admission_method_id 不能为空";
        }
        if (student.getCollegeCode() == null || student.getCollegeCode().trim().isEmpty()) {
            return "college_code 不能为空";
        }
        if (student.getMajorId() == null) {
            return "major_id 不能为空";
        }
        if (student.getProgramDuration() == null) {
            return "program_duration 不能为空";
        }
        if (student.getEducationLevelId() == null) {
            return "education_level_id 不能为空";
        }
        if (student.getGradeYear() == null || student.getGradeYear().trim().isEmpty()) {
            return "grade_year 不能为空";
        }
        if (student.getClassId() == null) {
            return "class_id 不能为空";
        }

        try {
            boolean result = m_students.insert(student) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("创建学生信息异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 14.2 删除学生信息
    @DeleteMapping("/Student_delete")
    public String deleteStudent(@RequestParam("student_id") String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return "student_id 不能为空";
        }
        try {
            LambdaQueryWrapper<students> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(students::getStudentId, studentId);
            boolean result = m_students.delete(wrapper) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除学生信息异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 14.3 更新学生信息
    @PutMapping("/Student_update")
    public String updateStudent(@RequestParam("student_id") String studentId,
                                @RequestBody students updatedStudent) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return "student_id 不能为空";
        }
        try {
            LambdaQueryWrapper<students> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(students::getStudentId, studentId);
            students existStudent = m_students.selectOne(wrapper);
            if (existStudent == null) return "ID不存在";

            // 更新所有提供的字段
            if (updatedStudent.getName() != null) {
                existStudent.setName(updatedStudent.getName());
            }
            if (updatedStudent.getGenderId() != null) {
                existStudent.setGenderId(updatedStudent.getGenderId());
            }
            if (updatedStudent.getCountryId() != null) {
                existStudent.setCountryId(updatedStudent.getCountryId());
            }
            if (updatedStudent.getBirthDate() != null) {
                existStudent.setBirthDate(updatedStudent.getBirthDate());
            }
            if (updatedStudent.getEthnicityId() != null) {
                existStudent.setEthnicityId(updatedStudent.getEthnicityId());
            }
            if (updatedStudent.getMaritalStatusId() != null) {
                existStudent.setMaritalStatusId(updatedStudent.getMaritalStatusId());
            }
            if (updatedStudent.getPoliticalStatusId() != null) {
                existStudent.setPoliticalStatusId(updatedStudent.getPoliticalStatusId());
            }
            if (updatedStudent.getIdCardNo() != null) {
                existStudent.setIdCardNo(updatedStudent.getIdCardNo());
            }
            if (updatedStudent.getStudentCategoryId() != null) {
                existStudent.setStudentCategoryId(updatedStudent.getStudentCategoryId());
            }
            if (updatedStudent.getEnrollmentDate() != null) {
                existStudent.setEnrollmentDate(updatedStudent.getEnrollmentDate());
            }
            if (updatedStudent.getAdmissionMethodId() != null) {
                existStudent.setAdmissionMethodId(updatedStudent.getAdmissionMethodId());
            }
            if (updatedStudent.getCollegeCode() != null) {
                existStudent.setCollegeCode(updatedStudent.getCollegeCode());
            }
            if (updatedStudent.getMajorId() != null) {
                existStudent.setMajorId(updatedStudent.getMajorId());
            }
            if (updatedStudent.getProgramDuration() != null) {
                existStudent.setProgramDuration(updatedStudent.getProgramDuration());
            }
            if (updatedStudent.getEducationLevelId() != null) {
                existStudent.setEducationLevelId(updatedStudent.getEducationLevelId());
            }
            if (updatedStudent.getGradeYear() != null) {
                existStudent.setGradeYear(updatedStudent.getGradeYear());
            }
            if (updatedStudent.getClassId() != null) {
                existStudent.setClassId(updatedStudent.getClassId());
            }
            if (updatedStudent.getCounselorId() != null) {
                existStudent.setCounselorId(updatedStudent.getCounselorId());
            }
            if (updatedStudent.getLatestTransferDate() != null) {
                existStudent.setLatestTransferDate(updatedStudent.getLatestTransferDate());
            }
            if (updatedStudent.getRemarks() != null) {
                existStudent.setRemarks(updatedStudent.getRemarks());
            }

            int affected = m_students.updateById(existStudent);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新学生信息异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 14.4 查询所有学生信息
    @GetMapping("/Student_select")
    public List<students> getAllStudents() {
        return m_students.selectList(null);
    }

    // 14.5 根据学号查询学生信息
    @GetMapping("/Student_select_by_id")
    public students getStudentById(@RequestParam("student_id") String studentId) {
        if (studentId == null || studentId.trim().isEmpty()) {
            return null;
        }
        LambdaQueryWrapper<students> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(students::getStudentId, studentId);
        return m_students.selectOne(wrapper);
    }

    // 保留原有的分页查询方法
    @GetMapping("/query")
    public List<students> queryStudents(
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "major_id", required = false) Integer majorId,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        Page<students> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<students> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(name != null && !name.isEmpty(), students::getName, name)
                .eq(majorId != null, students::getMajorId, majorId)
                .orderByDesc(students::getCreatedAt);

        IPage<students> resultPage = m_students.selectPage(page, wrapper);
        return resultPage.getRecords();
    }
}