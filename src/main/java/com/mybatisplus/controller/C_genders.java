package com.mybatisplus.controller;

import com.mybatisplus.entity.genders;
import com.mybatisplus.mapper.M_genders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/G")  // 添加基础路径前缀
public class C_genders {
    private static final Logger logger = LoggerFactory.getLogger(C_genders.class);
    private final M_genders m_genders;

    public C_genders(M_genders m_genders) {
        this.m_genders = m_genders;
    }

    // 插入方法
    @RequestMapping(value = "/insert", method = {RequestMethod.GET, RequestMethod.POST})
    public String insert(
            @RequestParam("gender_name") String genderName) {
        if (genderName == null || genderName.trim().isEmpty()) {
            return "gender_name 不能为空";
        }
        try {
            genders newGender = new genders();
            newGender.setGenderName(genderName);
            boolean result = m_genders.insert(newGender) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除方法
    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.DELETE})
    public String delete(
            @RequestParam("gender_id") int genderId) {
        if (genderId <= 0) {
            return "gender_id 必须大于 0";
        }
        try {
            boolean result = m_genders.deleteById(genderId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新方法
    @RequestMapping(value = "/update", method = {RequestMethod.GET, RequestMethod.PUT})
    public String update(
            @RequestParam("gender_id") int genderId,
            @RequestParam(value = "gender_name", required = false) String genderName) {
        if (genderId <= 0) {
            return "gender_id 必须大于 0";
        }
        try {
            genders existGender = m_genders.selectById(genderId);
            if (existGender == null) return "ID不存在";
            if (genderName != null && !genderName.trim().isEmpty()) {
                existGender.setGenderName(genderName);
            }
            int affected = m_genders.updateById(existGender);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询方法
    @GetMapping("/select")
    public List<genders> select() {
        return m_genders.selectList(null);
    }
}