package com.mybatisplus.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class student_audit_log {
    @TableId(value = "log_id", type = IdType.AUTO)
    private int logId;
    private String tableName;
    private String rowPkValue;
    private action_type actionType;
    private String oldData;
    private String newData;
    private String changedByDbUser;
    private int applicationUserId;
    private Date changeTimestamp;
    private String transactionId;
    private String remarks;
}