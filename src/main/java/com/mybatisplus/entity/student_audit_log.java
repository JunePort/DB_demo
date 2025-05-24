package com.mybatisplus.entity;

import lombok.Data;
import com.baomidou.mybatisplus.annotation.*;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import java.time.LocalDateTime;

@Data
@TableName(value = "student_audit_log", autoResultMap = true)
public class student_audit_log {
    @TableId(value = "log_id", type = IdType.AUTO)
    private Long logId;

    @TableField("table_name")
    private String tableName;

    @TableField("row_pk_value")
    private String rowPkValue;

    @TableField("action_type")
    private ActionType actionType;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object oldData;

    @TableField(typeHandler = JacksonTypeHandler.class)
    private Object newData;

    @TableField("changed_by_db_user")
    private String changedByDbUser;

    @TableField("application_user_id")
    private Integer applicationUserId;

    @TableField(value = "change_timestamp", fill = FieldFill.INSERT)
    private LocalDateTime changeTimestamp;

    @TableField("transaction_id")
    private String transactionId;

    @TableField("remarks")
    private String remarks;

    public enum ActionType {
        INSERT, UPDATE, DELETE
    }
}