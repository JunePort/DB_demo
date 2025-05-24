package com.mybatisplus.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.entity.student_audit_log;
import com.mybatisplus.mapper.M_student_audit_log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/student-audit-log")
public class C_student_audit_log {
    private static final Logger logger = LoggerFactory.getLogger(C_student_audit_log.class);
    private final M_student_audit_log m_student_audit_log;

    public C_student_audit_log(M_student_audit_log m_student_audit_log) {
        this.m_student_audit_log = m_student_audit_log;
    }

    /* ========== 原有方法保持不变 ========== */

    // 创建审计日志记录
    @PostMapping
    public String createStudentAuditLog(@RequestBody student_audit_log log) {
        if (log.getTableName() == null || log.getTableName().isEmpty()) {
            return "table_name 不能为空";
        }
        if (log.getRowPkValue() == null || log.getRowPkValue().isEmpty()) {
            return "row_pk_value 不能为空";
        }
        if (log.getActionType() == null) {
            return "action_type 不能为空";
        }
        try {
            boolean result = m_student_audit_log.insert(log) > 0;
            return result ? "创建成功!" : "创建失败!";
        } catch (Exception e) {
            logger.error("创建学生审计日志记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 删除审计日志记录
    @DeleteMapping("/{id}")
    public String deleteStudentAuditLog(@PathVariable("id") long logId) {
        if (logId <= 0) {
            return "log_id 必须大于 0";
        }
        try {
            boolean result = m_student_audit_log.deleteById(logId) > 0;
            return result ? "删除成功" : "ID不存在";
        } catch (Exception e) {
            logger.error("删除学生审计日志记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 更新审计日志记录
    @PutMapping("/{id}")
    public String updateStudentAuditLog(@PathVariable("id") long logId,
                                        @RequestBody student_audit_log updatedLog) {
        if (logId <= 0) {
            return "log_id 必须大于 0";
        }
        try {
            student_audit_log existLog = m_student_audit_log.selectById(logId);
            if (existLog == null) return "ID不存在";

            if (updatedLog.getTableName() != null && !updatedLog.getTableName().isEmpty()) {
                existLog.setTableName(updatedLog.getTableName());
            }
            if (updatedLog.getRowPkValue() != null && !updatedLog.getRowPkValue().isEmpty()) {
                existLog.setRowPkValue(updatedLog.getRowPkValue());
            }
            if (updatedLog.getActionType() != null) {
                existLog.setActionType(updatedLog.getActionType());
            }
            if (updatedLog.getChangedByDbUser() != null && !updatedLog.getChangedByDbUser().isEmpty()) {
                existLog.setChangedByDbUser(updatedLog.getChangedByDbUser());
            }
            if (updatedLog.getApplicationUserId() != null) {
                existLog.setApplicationUserId(updatedLog.getApplicationUserId());
            }
            if (updatedLog.getTransactionId() != null && !updatedLog.getTransactionId().isEmpty()) {
                existLog.setTransactionId(updatedLog.getTransactionId());
            }
            if (updatedLog.getRemarks() != null && !updatedLog.getRemarks().isEmpty()) {
                existLog.setRemarks(updatedLog.getRemarks());
            }

            int affected = m_student_audit_log.updateById(existLog);
            return affected > 0 ? "更新成功" : "更新失败";
        } catch (Exception e) {
            logger.error("更新学生审计日志记录异常", e);
            return "error: " + e.getMessage();
        }
    }

    // 查询所有审计日志记录
    @GetMapping
    public List<student_audit_log> getAllStudentAuditLogs() {
        return m_student_audit_log.selectList(null);
    }

    // 分页条件查询审计日志记录
    @GetMapping("/query")
    public List<student_audit_log> queryStudentAuditLogs(
            @RequestParam(value = "table_name", required = false) String tableName,
            @RequestParam(value = "action_type", required = false) String actionType,
            @RequestParam(value = "changed_by_db_user", required = false) String changedByDbUser,
            @RequestParam(value = "application_user_id", required = false) Integer applicationUserId,
            @RequestParam(value = "transaction_id", required = false) String transactionId,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        Page<student_audit_log> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<student_audit_log> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(tableName != null && !tableName.isEmpty(), student_audit_log::getTableName, tableName)
                .eq(actionType != null && !actionType.isEmpty(), student_audit_log::getActionType, actionType)
                .eq(changedByDbUser != null && !changedByDbUser.isEmpty(), student_audit_log::getChangedByDbUser, changedByDbUser)
                .eq(applicationUserId != null, student_audit_log::getApplicationUserId, applicationUserId)
                .eq(transactionId != null && !transactionId.isEmpty(), student_audit_log::getTransactionId, transactionId)
                .orderByDesc(student_audit_log::getChangeTimestamp);

        IPage<student_audit_log> resultPage = m_student_audit_log.selectPage(page, wrapper);
        return resultPage.getRecords();
    }

    /* ========== 新增17.1和17.2需求的方法 ========== */

    // 17.1 查询所有审计日志 (带分页)
    @GetMapping("/Audit_select")
    public IPage<student_audit_log> getAllAuditLogs(
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        Page<student_audit_log> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<student_audit_log> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(student_audit_log::getChangeTimestamp);

        return m_student_audit_log.selectPage(page, wrapper);
    }

    // 17.2 根据条件查询审计日志
    @GetMapping("/Audit_select_filtered")
    public List<student_audit_log> getFilteredAuditLogs(
            @RequestParam(value = "table_name", required = false) String tableName,
            @RequestParam(value = "row_pk_value", required = false) String rowPkValue,
            @RequestParam(value = "action_type", required = false) String actionType,
            @RequestParam(value = "start_timestamp", required = false) String startTimestamp,
            @RequestParam(value = "end_timestamp", required = false) String endTimestamp,
            @RequestParam(value = "application_user_id", required = false) Integer applicationUserId,
            @RequestParam(value = "page", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "size", defaultValue = "10") Integer pageSize) {

        Page<student_audit_log> page = new Page<>(pageNum, pageSize);
        LambdaQueryWrapper<student_audit_log> wrapper = new LambdaQueryWrapper<>();

        // 构建查询条件
        wrapper.eq(tableName != null && !tableName.isEmpty(), student_audit_log::getTableName, tableName)
                .eq(rowPkValue != null && !rowPkValue.isEmpty(), student_audit_log::getRowPkValue, rowPkValue)
                .eq(actionType != null && !actionType.isEmpty(), student_audit_log::getActionType, actionType)
                .eq(applicationUserId != null, student_audit_log::getApplicationUserId, applicationUserId)
                .orderByDesc(student_audit_log::getChangeTimestamp);

        // 处理时间范围查询
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            if (startTimestamp != null && !startTimestamp.isEmpty()) {
                Date startDate = dateFormat.parse(startTimestamp);
                wrapper.ge(student_audit_log::getChangeTimestamp, startDate);
            }
            if (endTimestamp != null && !endTimestamp.isEmpty()) {
                Date endDate = dateFormat.parse(endTimestamp);
                wrapper.le(student_audit_log::getChangeTimestamp, endDate);
            }
        } catch (ParseException e) {
            logger.error("时间格式解析错误", e);
            throw new IllegalArgumentException("时间格式不正确，请使用 YYYY-MM-DD HH:MM:SS 格式");
        }

        IPage<student_audit_log> resultPage = m_student_audit_log.selectPage(page, wrapper);
        return resultPage.getRecords();
    }
}