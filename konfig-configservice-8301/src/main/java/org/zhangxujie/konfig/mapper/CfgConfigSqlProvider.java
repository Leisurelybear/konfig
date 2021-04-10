package org.zhangxujie.konfig.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.zhangxujie.konfig.model.CfgConfig;
import org.zhangxujie.konfig.model.CfgConfigExample.Criteria;
import org.zhangxujie.konfig.model.CfgConfigExample.Criterion;
import org.zhangxujie.konfig.model.CfgConfigExample;

public class CfgConfigSqlProvider {

    public String countByExample(CfgConfigExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("cfg_config");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CfgConfigExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("cfg_config");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CfgConfig record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cfg_config");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getCollectionId() != null) {
            sql.VALUES("collection_id", "#{collectionId,jdbcType=INTEGER}");
        }

        if (record.getCfgName() != null) {
            sql.VALUES("cfg_name", "#{cfgName,jdbcType=VARCHAR}");
        }

        if (record.getCfgKey() != null) {
            sql.VALUES("cfg_key", "#{cfgKey,jdbcType=VARCHAR}");
        }

        if (record.getIsDel() != null) {
            sql.VALUES("is_del", "#{isDel,jdbcType=INTEGER}");
        }

        if (record.getIsDraft() != null) {
            sql.VALUES("is_draft", "#{isDraft,jdbcType=INTEGER}");
        }

        if (record.getUpdateUsername() != null) {
            sql.VALUES("update_username", "#{updateUsername,jdbcType=VARCHAR}");
        }

        if (record.getCreateUsername() != null) {
            sql.VALUES("create_username", "#{createUsername,jdbcType=VARCHAR}");
        }

        if (record.getUpdateTime() != null) {
            sql.VALUES("update_time", "#{updateTime,jdbcType=BIGINT}");
        }

        if (record.getCreateTime() != null) {
            sql.VALUES("create_time", "#{createTime,jdbcType=BIGINT}");
        }

        if (record.getCfgValue() != null) {
            sql.VALUES("cfg_value", "#{cfgValue,jdbcType=LONGVARCHAR}");
        }

        return sql.toString();
    }

    public String selectByExampleWithBLOBs(CfgConfigExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("collection_id");
        sql.SELECT("cfg_name");
        sql.SELECT("cfg_key");
        sql.SELECT("is_del");
        sql.SELECT("is_draft");
        sql.SELECT("update_username");
        sql.SELECT("create_username");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.SELECT("cfg_value");
        sql.FROM("cfg_config");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String selectByExample(CfgConfigExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("collection_id");
        sql.SELECT("cfg_name");
        sql.SELECT("cfg_key");
        sql.SELECT("is_del");
        sql.SELECT("is_draft");
        sql.SELECT("update_username");
        sql.SELECT("create_username");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("cfg_config");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CfgConfig record = (CfgConfig) parameter.get("record");
        CfgConfigExample example = (CfgConfigExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("cfg_config");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getCollectionId() != null) {
            sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        }

        if (record.getCfgName() != null) {
            sql.SET("cfg_name = #{record.cfgName,jdbcType=VARCHAR}");
        }

        if (record.getCfgKey() != null) {
            sql.SET("cfg_key = #{record.cfgKey,jdbcType=VARCHAR}");
        }

        if (record.getIsDel() != null) {
            sql.SET("is_del = #{record.isDel,jdbcType=INTEGER}");
        }

        if (record.getIsDraft() != null) {
            sql.SET("is_draft = #{record.isDraft,jdbcType=INTEGER}");
        }

        if (record.getUpdateUsername() != null) {
            sql.SET("update_username = #{record.updateUsername,jdbcType=VARCHAR}");
        }

        if (record.getCreateUsername() != null) {
            sql.SET("create_username = #{record.createUsername,jdbcType=VARCHAR}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{record.updateTime,jdbcType=BIGINT}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{record.createTime,jdbcType=BIGINT}");
        }

        if (record.getCfgValue() != null) {
            sql.SET("cfg_value = #{record.cfgValue,jdbcType=LONGVARCHAR}");
        }

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExampleWithBLOBs(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_config");

        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        sql.SET("cfg_name = #{record.cfgName,jdbcType=VARCHAR}");
        sql.SET("cfg_key = #{record.cfgKey,jdbcType=VARCHAR}");
        sql.SET("is_del = #{record.isDel,jdbcType=INTEGER}");
        sql.SET("is_draft = #{record.isDraft,jdbcType=INTEGER}");
        sql.SET("update_username = #{record.updateUsername,jdbcType=VARCHAR}");
        sql.SET("create_username = #{record.createUsername,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=BIGINT}");
        sql.SET("cfg_value = #{record.cfgValue,jdbcType=LONGVARCHAR}");

        CfgConfigExample example = (CfgConfigExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_config");

        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        sql.SET("cfg_name = #{record.cfgName,jdbcType=VARCHAR}");
        sql.SET("cfg_key = #{record.cfgKey,jdbcType=VARCHAR}");
        sql.SET("is_del = #{record.isDel,jdbcType=INTEGER}");
        sql.SET("is_draft = #{record.isDraft,jdbcType=INTEGER}");
        sql.SET("update_username = #{record.updateUsername,jdbcType=VARCHAR}");
        sql.SET("create_username = #{record.createUsername,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=BIGINT}");

        CfgConfigExample example = (CfgConfigExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CfgConfig record) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_config");

        if (record.getCollectionId() != null) {
            sql.SET("collection_id = #{collectionId,jdbcType=INTEGER}");
        }

        if (record.getCfgName() != null) {
            sql.SET("cfg_name = #{cfgName,jdbcType=VARCHAR}");
        }

        if (record.getCfgKey() != null) {
            sql.SET("cfg_key = #{cfgKey,jdbcType=VARCHAR}");
        }

        if (record.getIsDel() != null) {
            sql.SET("is_del = #{isDel,jdbcType=INTEGER}");
        }

        if (record.getIsDraft() != null) {
            sql.SET("is_draft = #{isDraft,jdbcType=INTEGER}");
        }

        if (record.getUpdateUsername() != null) {
            sql.SET("update_username = #{updateUsername,jdbcType=VARCHAR}");
        }

        if (record.getCreateUsername() != null) {
            sql.SET("create_username = #{createUsername,jdbcType=VARCHAR}");
        }

        if (record.getUpdateTime() != null) {
            sql.SET("update_time = #{updateTime,jdbcType=BIGINT}");
        }

        if (record.getCreateTime() != null) {
            sql.SET("create_time = #{createTime,jdbcType=BIGINT}");
        }

        if (record.getCfgValue() != null) {
            sql.SET("cfg_value = #{cfgValue,jdbcType=LONGVARCHAR}");
        }

        sql.WHERE("id = #{id,jdbcType=INTEGER}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, CfgConfigExample example, boolean includeExamplePhrase) {
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