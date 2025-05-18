package com.mybatisplus.controller;

import java.util.List;
import com.mybatisplus.entity.genders;
import com.mybatisplus.mapper.M_genders;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class C_genders {
    final M_genders m_genders;
    public C_genders(M_genders m_genders) {
        this.m_genders = m_genders;
    }

    @RequestMapping("/G_insert")
    public String insert(
            @RequestParam("gender_id") int genderId,
            @RequestParam("gender_name") String genderName) {
        try {
            genders newGender = new genders(genderId, genderName);
            boolean result = m_genders.insert(newGender) > 0;
            return result ? "插入成功!" : "插入失败!";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @RequestMapping("/G_delete")
    public String delete(
            @RequestParam("gender_id") int genderId) {
        try {
            boolean result = m_genders.deleteById(genderId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @RequestMapping("/G_update")
    public String update(
            @RequestParam("gender_id") int genderId,
            @RequestParam(value = "gender_name", required = false) String genderName) {
        try {
            genders existGender = m_genders.selectById(genderId);
            if (existGender == null) return "ID不存在";
            if (genderName != null) existGender.setGenderName(genderName);
            int affected = m_genders.updateById(existGender);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    @RequestMapping("/G_select")
    public List<genders> select() {
        return m_genders.selectList(null);
    }
}

// http://localhost:8081/G_insert?gender_id=1&gender_name=Male
// http://localhost:8081/G_delete?gender_id=1
// http://localhost:8081/G_update?gender_id=1&gender_name=女
// http://localhost:8081/G_select