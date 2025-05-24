package com.mybatisplus.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("political_statuses")  // 精确映射数据库表名
public class political_statuses {
    @TableId(value = "political_status_id", type = IdType.AUTO)
    private Integer politicalStatusId;  // 自增主键

    @TableField("status_name")
    private String statusName;  // 政治状态名称
}