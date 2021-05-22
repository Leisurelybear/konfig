package org.zhangxujie.konfig.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.zhangxujie.konfig.model.CfgPermission;
import org.zhangxujie.konfig.model.CfgPermissionExample.Criteria;
import org.zhangxujie.konfig.model.CfgPermissionExample.Criterion;
import org.zhangxujie.konfig.model.CfgPermissionExample;

public class CfgPermissionSqlProvider {

    public String countByExample(CfgPermissionExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("cfg_permission");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CfgPermissionExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("cfg_permission");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CfgPermission record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cfg_permission");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.VALUES("type", "#{type,jdbcType=INTEGER}");
        }
        
        if (record.getAccountId() != null) {
            sql.VALUES("account_id", "#{accountId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.VALUES("group_id", "#{groupId,jdbcType=INTEGER}");
        }
        
        if (record.getCollectionId() != null) {
            sql.VALUES("collection_id", "#{collectionId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateUsername() != null) {
            sql.VALUES("create_username", "#{createUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateAccountId() != null) {
            sql.VALUES("create_account_id", "#{createAccountId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDel() != null) {
            sql.VALUES("is_del", "#{isDel,jdbcType=INTEGER}");
        }
        
        return sql.toString();
    }

    public String selectByExample(CfgPermissionExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("type");
        sql.SELECT("account_id");
        sql.SELECT("group_id");
        sql.SELECT("collection_id");
        sql.SELECT("create_time");
        sql.SELECT("create_username");
        sql.SELECT("create_account_id");
        sql.SELECT("is_del");
        sql.FROM("cfg_permission");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CfgPermission record = (CfgPermission) parameter.get("record");
        CfgPermissionExample example = (CfgPermissionExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("cfg_permission");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getType() != null) {
            sql.SET("type = #{record.type,jdbcType=INTEGER}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{record.accountId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.SET("group_id = #{record.groupId,jdbcType=INTEGER}");
        }
        
        if (record.getCollectionId() != null) {
            sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateUsername() != null) {
            sql.SET("create_username = #{record.createUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateAccountId() != null) {
            sql.SET("create_account_id = #{record.createAccountId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDel() != null) {
            sql.SET("is_del = #{record.isDel,jdbcType=INTEGER}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_permission");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("type = #{record.type,jdbcType=INTEGER}");
        sql.SET("account_id = #{record.accountId,jdbcType=INTEGER}");
        sql.SET("group_id = #{record.groupId,jdbcType=INTEGER}");
        sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        sql.SET("create_time = #{record.createTime,jdbcType=BIGINT}");
        sql.SET("create_username = #{record.createUsername,jdbcType=VARCHAR}");
        sql.SET("create_account_id = #{record.createAccountId,jdbcType=INTEGER}");
        sql.SET("is_del = #{record.isDel,jdbcType=INTEGER}");
        
        CfgPermissionExample example = (CfgPermissionExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CfgPermission record) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_permission");
        
        if (record.getType() != null) {
            sql.SET("type = #{type,jdbcType=INTEGER}");
        }
        
        if (record.getAccountId() != null) {
            sql.SET("account_id = #{accountId,jdbcType=INTEGER}");
        }
        
        if (record.getGroupId() != null) {
            sql.SET("group_id = #{groupId,jdbcType=INTEGER}");
        }
        
        if (record.getCollectionId() != null) {
            sql.SET("collection_id = #{collectionId,jdbcType=INTEGER}");
        }
        
        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }
        
        if (record.getCreateUsername() != null) {
            sql.SET("create_username = #{createUsername,jdbcType=VARCHAR}");
        }
        
        if (record.getCreateAccountId() != null) {
            sql.SET("create_account_id = #{createAccountId,jdbcType=INTEGER}");
        }
        
        if (record.getIsDel() != null) {
            sql.SET("is_del = #{isDel,jdbcType=INTEGER}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CfgPermissionExample example, boolean includeExamplePhrase) {
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