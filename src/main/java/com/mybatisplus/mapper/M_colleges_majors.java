package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.colleges_majors;

public interface M_colleges_majors extends BaseMapper<colleges_majors> {
    int delete(colleges_majors entityToDelete);
}
