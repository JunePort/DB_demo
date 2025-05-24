package com.mybatisplus.controller;

import com.mybatisplus.entity.countries;
import com.mybatisplus.mapper.M_countries;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class C_countries {
    private static final Logger logger = LoggerFactory.getLogger(C_countries.class);
    private final M_countries m_countries;

    public C_countries(M_countries m_countries) {
        this.m_countries = m_countries;
    }

    // 添加国别信息
    @PostMapping("/Country_insert")
    public String insert(
            @RequestParam("country_name") String countryName) {
        try {
            countries newCountry = new countries();
            newCountry.setCountryName(countryName);
            boolean result = m_countries.insert(newCountry) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            logger.error("插入操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除国别信息
    @DeleteMapping("/Country_delete")
    public String delete(
            @RequestParam("country_id") int countryId) {
        try {
            boolean result = m_countries.deleteById(countryId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新国别信息
    @PutMapping("/Country_update")
    public String update(
            @RequestParam("country_id") int countryId,
            @RequestParam(value = "country_name", required = false) String countryName) {
        try {
            countries existCountry = m_countries.selectById(countryId);
            if (existCountry == null) return "ID不存在";
            if (countryName != null) {
                existCountry.setCountryName(countryName);
            }
            int affected = m_countries.updateById(existCountry);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新操作出现异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有国别信息
    @GetMapping("/Country_select")
    public List<countries> select() {
        return m_countries.selectList(null);
    }
}