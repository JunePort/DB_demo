package com.mybatisplus.controller;

import java.util.List;

import com.mybatisplus.entity.action_type;
import com.mybatisplus.entity.student_audit_log;
import com.mybatisplus.mapper.M_student_audit_log;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class C_student_audit_log {
    final M_student_audit_log m_auditLog;
    public C_student_audit_log(M_student_audit_log m_auditLog) {
        this.m_auditLog = m_auditLog;
    }

    // 审计日志插入（通常由系统自动调用）
    @RequestMapping("/L_insert")
    public String insert(
            @RequestParam("table_name") String tableName,
            @RequestParam("row_pk_value") String rowPkValue,
            @RequestParam("action_type") action_type actionType,
            @RequestParam(value = "old_data", required = false) String oldData,
            @RequestParam(value = "new_data", required = false) String newData,
            @RequestParam("changed_by_db_user") String dbUser,
            @RequestParam("application_user_id") int appUserId,
            @RequestParam(value = "transaction_id", required = false) String transactionId,
            @RequestParam(value = "remarks", required = false) String remarks) {
        try {
            student_audit_log log = new student_audit_log();
            log.setTableName(tableName);
            log.setRowPkValue(rowPkValue);
            log.setActionType(actionType);
            log.setOldData(oldData);
            log.setNewData(newData);
            log.setChangedByDbUser(dbUser);
            log.setApplicationUserId(appUserId);
            log.setTransactionId(transactionId);
            log.setRemarks(remarks);

            boolean result = m_auditLog.insert(log) > 0;
            return result ? "操作已记录" : "日志写入失败";
        } catch (Exception e) {
            return "error: " + e.getMessage();
        }
    }

    // 查询所有审计日志（建议添加分页参数）
    @RequestMapping("/L_select")
    public List<student_audit_log> select() {
        return m_auditLog.selectList(null);
    }

    // 通常审计日志不需要删除和更新操作
}

// http://localhost:8081/L_insert?table_name=students&row_pk_value=2023001&action_type=UPDATE&old_data={"name":"张三"}&new_data={"name":"张四"}&changed_by_db_user=admin&application_user_id=1

// http://localhost:8081/L_select