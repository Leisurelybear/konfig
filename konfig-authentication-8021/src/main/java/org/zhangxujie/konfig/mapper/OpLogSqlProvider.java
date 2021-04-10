package org.zhangxujie.konfig.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.zhangxujie.konfig.model.OpLog;
import org.zhangxujie.konfig.model.OpLogExample.Criteria;
import org.zhangxujie.konfig.model.OpLogExample.Criterion;
import org.zhangxujie.konfig.model.OpLogExample;

public class OpLogSqlProvider {

    public String countByExample(OpLogExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("op_log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(OpLogExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("op_log");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(OpLog record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("op_log");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getLog() != null) {
            sql.VALUES("log", "#{log,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateAccountId() != null) {
            sql.VALUES("update_account_id", "#{updateAccountId,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateUsername() != null) {
            sql.VALUES("update_username", "#{updateUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=BIGINT}");
        }
        
        if (record.getOpType() != null) {
            sql.VALUES("op_type", "#{opType,jdbcType=VARCHAR}");
        }
        
        if (record.getOpTable() != null) {
            sql.VALUES("op_table", "#{opTable,jdbcType=VARCHAR}");
        }
        
        if (record.getOpField() != null) {
            sql.VALUES("op_field", "#{opField,jdbcType=VARCHAR}");
        }
        
        if (record.getDataBefore() != null) {
            sql.VALUES("data_before", "#{dataBefore,jdbcType=VARCHAR}");
        }
        
        if (record.getDataAfter() != null) {
            sql.VALUES("data_after", "#{dataAfter,jdbcType=VARCHAR}");
        }
        
        return sql.toString();
    }

    public String selectByExample(OpLogExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("log");
        sql.SELECT("update_account_id");
        sql.SELECT("update_username");
        sql.SELECT("update_time");
        sql.SELECT("op_type");
        sql.SELECT("op_table");
        sql.SELECT("op_field");
        sql.SELECT("data_before");
        sql.SELECT("data_after");
        sql.FROM("op_log");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        OpLog record = (OpLog) parameter.get("record");
        OpLogExample example = (OpLogExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("op_log");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getLog() != null) {
            sql.SET("log = #{record.log,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateAccountId() != null) {
            sql.SET("update_account_id = #{record.updateAccountId,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateUsername() != null) {
            sql.SET("update_username = #{record.updateUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=BIGINT}");
        }
        
        if (record.getOpType() != null) {
            sql.SET("op_type = #{record.opType,jdbcType=VARCHAR}");
        }
        
        if (record.getOpTable() != null) {
            sql.SET("op_table = #{record.opTable,jdbcType=VARCHAR}");
        }
        
        if (record.getOpField() != null) {
            sql.SET("op_field = #{record.opField,jdbcType=VARCHAR}");
        }
        
        if (record.getDataBefore() != null) {
            sql.SET("data_before = #{record.dataBefore,jdbcType=VARCHAR}");
        }
        
        if (record.getDataAfter() != null) {
            sql.SET("data_after = #{record.dataAfter,jdbcType=VARCHAR}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("op_log");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("log = #{record.log,jdbcType=VARCHAR}");
        sql.SET("update_account_id = #{record.updateAccountId,jdbcType=INTEGER}");
        sql.SET("update_username = #{record.updateUsername,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=BIGINT}");
        sql.SET("op_type = #{record.opType,jdbcType=VARCHAR}");
        sql.SET("op_table = #{record.opTable,jdbcType=VARCHAR}");
        sql.SET("op_field = #{record.opField,jdbcType=VARCHAR}");
        sql.SET("data_before = #{record.dataBefore,jdbcType=VARCHAR}");
        sql.SET("data_after = #{record.dataAfter,jdbcType=VARCHAR}");
        
        OpLogExample example = (OpLogExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(OpLog record) {
        SQL sql = new SQL();
        sql.UPDATE("op_log");
        
        if (record.getLog() != null) {
            sql.SET("log = #{log,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateAccountId() != null) {
            sql.SET("update_account_id = #{updateAccountId,jdbcType=INTEGER}");
        }
        
        if (record.getUpdateUsername() != null) {
            sql.SET("update_username = #{updateUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }
        
        if (record.getOpType() != null) {
            sql.SET("op_type = #{opType,jdbcType=VARCHAR}");
        }
        
        if (record.getOpTable() != null) {
            sql.SET("op_table = #{opTable,jdbcType=VARCHAR}");
        }
        
        if (record.getOpField() != null) {
            sql.SET("op_field = #{opField,jdbcType=VARCHAR}");
        }
        
        if (record.getDataBefore() != null) {
            sql.SET("data_before = #{dataBefore,jdbcType=VARCHAR}");
        }
        
        if (record.getDataAfter() != null) {
            sql.SET("data_after = #{dataAfter,jdbcType=VARCHAR}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, OpLogExample example, boolean includeExamplePhrase) {
        if (example == null) {
            return;
        }
        
        String parmPhrase1;
        String parmPhrase1_th;
        String parmPhrase2;
        String parmPhrase2_th;
        String parmPhrase3;
        String parmPhrase3_th;
        if (includeExamplePhrase) {
            parmPhrase1 = "%s #{example.oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{example.oredCriteria[%d].allCriteria[%d].value} and #{example.oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{example.oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{example.oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{example.oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{example.oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        } else {
            parmPhrase1 = "%s #{oredCriteria[%d].allCriteria[%d].value}";
            parmPhrase1_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s}";
            parmPhrase2 = "%s #{oredCriteria[%d].allCriteria[%d].value} and #{oredCriteria[%d].criteria[%d].secondValue}";
            parmPhrase2_th = "%s #{oredCriteria[%d].allCriteria[%d].value,typeHandler=%s} and #{oredCriteria[%d].criteria[%d].secondValue,typeHandler=%s}";
            parmPhrase3 = "#{oredCriteria[%d].allCriteria[%d].value[%d]}";
            parmPhrase3_th = "#{oredCriteria[%d].allCriteria[%d].value[%d],typeHandler=%s}";
        }
        
        StringBuilder sb = new StringBuilder();
        List<Criteria> oredCriteria = example.getOredCriteria();
        boolean firstCriteria = true;
        for (int i = 0; i < oredCriteria.size(); i++) {
            Criteria criteria = oredCriteria.get(i);
            if (criteria.isValid()) {
                if (firstCriteria) {
                    firstCriteria = false;
                } else {
                    sb.append(" or ");
                }
                
                sb.append('(');
                List<Criterion> criterions = criteria.getAllCriteria();
                boolean firstCriterion = true;
                for (int j = 0; j < criterions.size(); j++) {
                    Criterion criterion = criterions.get(j);
                    if (firstCriterion) {
                        firstCriterion = false;
                    } else {
                        sb.append(" and ");
                    }
                    
                    if (criterion.isNoValue()) {
                        sb.append(criterion.getCondition());
                    } else if (criterion.isSingleValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase1, criterion.getCondition(), i, j));
                        } else {
                            sb.append(String.format(parmPhrase1_th, criterion.getCondition(), i, j,criterion.getTypeHandler()));
                        }
                    } else if (criterion.isBetweenValue()) {
                        if (criterion.getTypeHandler() == null) {
                            sb.append(String.format(parmPhrase2, criterion.getCondition(), i, j, i, j));
                        } else {
                            sb.append(String.format(parmPhrase2_th, criterion.getCondition(), i, j, criterion.getTypeHandler(), i, j, criterion.getTypeHandler()));
                        }
                    } else if (criterion.isListValue()) {
                        sb.append(criterion.getCondition());
                        sb.append(" (");
                        List<?> listItems = (List<?>) criterion.getValue();
                        boolean comma = false;
                        for (int k = 0; k < listItems.size(); k++) {
                            if (comma) {
                                sb.append(", ");
                            } else {
                                comma = true;
                            }
                            if (criterion.getTypeHandler() == null) {
                                sb.append(String.format(parmPhrase3, i, j, k));
                            } else {
                                sb.append(String.format(parmPhrase3_th, i, j, k, criterion.getTypeHandler()));
                            }
                        }
                        sb.append(')');
                    }
                }
                sb.append(')');
            }
        }
        
        if (sb.length() > 0) {
            sql.WHERE(sb.toString());
        }
    }
}