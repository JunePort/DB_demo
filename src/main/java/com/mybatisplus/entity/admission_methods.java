package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class admission_methods {
    @TableId(value = "admission_method_id", type = IdType.AUTO)
    private int admissionMethodId;
    private String methodName;
}