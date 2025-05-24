package com.mybatisplus.controller;

import com.mybatisplus.entity.ethnicities;
import com.mybatisplus.mapper.M_ethnicities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/ethnicities")
public class C_ethnicities {
    private static final Logger logger = LoggerFactory.getLogger(C_ethnicities.class);
    private final M_ethnicities m_ethnicities;

    public C_ethnicities(M_ethnicities m_ethnicities) {
        this.m_ethnicities = m_ethnicities;
    }


    @GetMapping
    public List<ethnicities> getAll() {
        return m_ethnicities.selectList(null);
    }


    @GetMapping("/{id}")
    public ethnicities getById(@PathVariable("id") int ethnicityId) {
        return m_ethnicities.selectById(ethnicityId);
    }


    @PostMapping
    public String create(@RequestBody ethnicities ethnicity) {
        if (ethnicity.getEthnicityName() == null || ethnicity.getEthnicityName().trim().isEmpty()) {
            return "ethnicity_name 不能为空";
        }
        try {
            boolean result = m_ethnicities.insert(ethnicity) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }


    @PutMapping("/{id}")
    public String update(@PathVariable("id") int ethnicityId, @RequestBody ethnicities ethnicity) {
        if (ethnicityId <= 0) {
            return "ethnicity_id 必须大于 0";
        }
        try {
            ethnicities existEthnicity = m_ethnicities.selectById(ethnicityId);
            if (existEthnicity == null) return "ID不存在";

            if (ethnicity.getEthnicityName() != null && !ethnicity.getEthnicityName().trim().isEmpty()) {
                existEthnicity.setEthnicityName(ethnicity.getEthnicityName());
            }

            int affected = m_ethnicities.updateById(existEthnicity);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int ethnicityId) {
        if (ethnicityId <= 0) {
            return "ethnicity_id 必须大于 0";
        }
        try {
            boolean result = m_ethnicities.deleteById(ethnicityId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }
}