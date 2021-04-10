package org.zhangxujie.konfig.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.model.CfgCollectionExample.Criteria;
import org.zhangxujie.konfig.model.CfgCollectionExample.Criterion;
import org.zhangxujie.konfig.model.CfgCollectionExample;

public class CfgCollectionSqlProvider {

    public String countByExample(CfgCollectionExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("cfg_config_collection");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CfgCollectionExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("cfg_config_collection");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CfgCollection record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cfg_config_collection");

        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }

        if (record.getCollectionId() != null) {
            sql.VALUES("collection_id", "#{collectionId,jdbcType=INTEGER}");
        }

        if (record.getcName() != null) {
            sql.VALUES("c_name", "#{cName,jdbcType=VARCHAR}");
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

        return sql.toString();
    }

    public String selectByExample(CfgCollectionExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("collection_id");
        sql.SELECT("c_name");
        sql.SELECT("is_del");
        sql.SELECT("is_draft");
        sql.SELECT("update_username");
        sql.SELECT("create_username");
        sql.SELECT("update_time");
        sql.SELECT("create_time");
        sql.FROM("cfg_config_collection");
        applyWhere(sql, example, false);

        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }

        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CfgCollection record = (CfgCollection) parameter.get("record");
        CfgCollectionExample example = (CfgCollectionExample) parameter.get("example");

        SQL sql = new SQL();
        sql.UPDATE("cfg_config_collection");

        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }

        if (record.getCollectionId() != null) {
            sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        }

        if (record.getcName() != null) {
            sql.SET("c_name = #{record.cName,jdbcType=VARCHAR}");
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

        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_config_collection");

        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("collection_id = #{record.collectionId,jdbcType=INTEGER}");
        sql.SET("c_name = #{record.cName,jdbcType=VARCHAR}");
        sql.SET("is_del = #{record.isDel,jdbcType=INTEGER}");
        sql.SET("is_draft = #{record.isDraft,jdbcType=INTEGER}");
        sql.SET("update_username = #{record.updateUsername,jdbcType=VARCHAR}");
        sql.SET("create_username = #{record.createUsername,jdbcType=VARCHAR}");
        sql.SET("update_time = #{record.updateTime,jdbcType=BIGINT}");
        sql.SET("create_time = #{record.createTime,jdbcType=BIGINT}");

        CfgCollectionExample example = (CfgCollectionExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CfgCollection record) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_config_collection");

        if (record.getCollectionId() != null) {
            sql.SET("collection_id = #{collectionId,jdbcType=INTEGER}");
        }

        if (record.getcName() != null) {
            sql.SET("c_name = #{cName,jdbcType=VARCHAR}");
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

        sql.WHERE("id = #{id,jdbcType=INTEGER}");

        return sql.toString();
    }

    protected void applyWhere(SQL sql, CfgCollectionExample example, boolean includeExamplePhrase) {
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