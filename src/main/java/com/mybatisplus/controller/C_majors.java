package com.mybatisplus.controller;

import com.mybatisplus.entity.majors;
import com.mybatisplus.mapper.M_majors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class C_majors {
    private static final Logger logger = LoggerFactory.getLogger(C_majors.class);
    private final M_majors m_majors;

    public C_majors(M_majors m_majors) {
        this.m_majors = m_majors;
    }

    // 10.1 添加专业信息
    @RequestMapping(value = "/Major_insert", method = {RequestMethod.GET, RequestMethod.POST})
    public String insert(
            @RequestParam("major_code") String majorCode,
            @RequestParam("major_name") String majorName,
            @RequestParam("default_program_duration") int defaultProgramDuration,
            @RequestParam("default_education_level_id") int defaultEducationLevelId) {
        if (majorCode == null || majorCode.trim().isEmpty()) {
            return "major_code 不能为空";
        }
        if (majorName == null || majorName.trim().isEmpty()) {
            return "major_name 不能为空";
        }
        if (defaultProgramDuration <= 0) {
            return "default_program_duration 必须大于 0";
        }
        if (defaultEducationLevelId <= 0) {
            return "default_education_level_id 必须大于 0";
        }
        try {
            majors newMajor = new majors();
            newMajor.setMajorCode(majorCode);
            newMajor.setMajorName(majorName);
            newMajor.setDefaultProgramDuration(defaultProgramDuration);
            newMajor.setDefaultEducationLevelId(defaultEducationLevelId);
            boolean result = m_majors.insert(newMajor) > 0;
            return result? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除方法
    @RequestMapping("/majors_delete")
    public String delete(
            @RequestParam("major_id") int majorId) {
        if (majorId <= 0) {
            return "major_id 必须大于 0";
        }
        try {
            boolean result = m_majors.deleteById(majorId) > 0;
            return result? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新方法
    @RequestMapping("/majors_update")
    public String update(
            @RequestParam("major_id") int majorId,
            @RequestParam(value = "major_code", required = false) String majorCode,
            @RequestParam(value = "major_name", required = false) String majorName,
            @RequestParam(value = "default_program_duration", required = false) Integer defaultProgramDuration,
            @RequestParam(value = "default_education_level_id", required = false) Integer defaultEducationLevelId) {
        if (majorId <= 0) {
            return "major_id 必须大于 0";
        }
        if (majorCode != null && majorCode.length() != 2) {
            return "专业代码必须是2位字符";
        }
        try {
            majors existMajor = m_majors.selectById(majorId);
            if (existMajor == null) return "ID不存在";
            if (majorCode != null && !majorCode.trim().isEmpty()) {
                existMajor.setMajorCode(majorCode);
            }
            if (majorName != null && !majorName.trim().isEmpty()) {
                existMajor.setMajorName(majorName);
            }
            if (defaultProgramDuration != null && defaultProgramDuration > 0) {
                existMajor.setDefaultProgramDuration(defaultProgramDuration);
            }
            if (defaultEducationLevelId != null && defaultEducationLevelId > 0) {
                existMajor.setDefaultEducationLevelId(defaultEducationLevelId);
            }
            int affected = m_majors.updateById(existMajor);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 10.4 查询所有专业信息
    @GetMapping("/Major_select")
    public List<majors> select() {
        return m_majors.selectList(null);
    }

    // 10.5 根据专业ID查询专业信息
    @GetMapping("/Major_select_by_id")
    public majors selectById(@RequestParam("major_id") int majorId) {
        if (majorId <= 0) {
            return null;
        }
        return m_majors.selectById(majorId);
    }
}