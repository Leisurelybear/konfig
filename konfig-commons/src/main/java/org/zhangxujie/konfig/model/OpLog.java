package org.zhangxujie.konfig.model;

public class OpLog {
    private Integer id;

    private String log;

    private Integer updateAccountId;

    private String updateUsername;

    private Long updateTime;

    private String opType;

    private String opTable;

    private String opField;

    private String dataBefore;

    private String dataAfter;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log == null ? null : log.trim();
    }

    public Integer getUpdateAccountId() {
        return updateAccountId;
    }

    public void setUpdateAccountId(Integer updateAccountId) {
        this.updateAccountId = updateAccountId;
    }

    public String getUpdateUsername() {
        return updateUsername;
    }

    public void setUpdateUsername(String updateUsername) {
        this.updateUsername = updateUsername == null ? null : updateUsername.trim();
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getOpType() {
        return opType;
    }

    public void setOpType(String opType) {
        this.opType = opType == null ? null : opType.trim();
    }

    public String getOpTable() {
        return opTable;
    }

    public void setOpTable(String opTable) {
        this.opTable = opTable == null ? null : opTable.trim();
    }

    public String getOpField() {
        return opField;
    }

    public void setOpField(String opField) {
        this.opField = opField == null ? null : opField.trim();
    }

    public String getDataBefore() {
        return dataBefore;
    }

    public void setDataBefore(String dataBefore) {
        this.dataBefore = dataBefore == null ? null : dataBefore.trim();
    }

    public String getDataAfter() {
        return dataAfter;
    }

    public void setDataAfter(String dataAfter) {
        this.dataAfter = dataAfter == null ? null : dataAfter.trim();
    }
}