package org.zhangxujie.konfig.mapper;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.jdbc.SQL;
import org.zhangxujie.konfig.model.CfgAudit;
import org.zhangxujie.konfig.model.CfgAuditExample.Criteria;
import org.zhangxujie.konfig.model.CfgAuditExample.Criterion;
import org.zhangxujie.konfig.model.CfgAuditExample;

public class CfgAuditSqlProvider {

    public String countByExample(CfgAuditExample example) {
        SQL sql = new SQL();
        sql.SELECT("count(*)").FROM("cfg_audit");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String deleteByExample(CfgAuditExample example) {
        SQL sql = new SQL();
        sql.DELETE_FROM("cfg_audit");
        applyWhere(sql, example, false);
        return sql.toString();
    }

    public String insertSelective(CfgAudit record) {
        SQL sql = new SQL();
        sql.INSERT_INTO("cfg_audit");
        
        if (record.getId() != null) {
            sql.VALUES("id", "#{id,jdbcType=INTEGER}");
        }
        
        if (record.getCfgCollectionId() != null) {
            sql.VALUES("cfg_collection_id", "#{cfgCollectionId,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.VALUES("status", "#{status,jdbcType=INTEGER}");
        }
        
        if (record.getApplicantAid() != null) {
            sql.VALUES("applicant_aid", "#{applicantAid,jdbcType=INTEGER}");
        }
        
        if (record.getReviewerAid() != null) {
            sql.VALUES("reviewer_aid", "#{reviewerAid,jdbcType=INTEGER}");
        }
        
        if (record.getSubmitTime() != null) {
            sql.VALUES("submit_time", "#{submitTime,jdbcType=BIGINT}");
        }
        
        if (record.getHandleTime() != null) {
            sql.VALUES("handle_time", "#{handleTime,jdbcType=BIGINT}");
        }
        
        return sql.toString();
    }

    public String selectByExample(CfgAuditExample example) {
        SQL sql = new SQL();
        if (example != null && example.isDistinct()) {
            sql.SELECT_DISTINCT("id");
        } else {
            sql.SELECT("id");
        }
        sql.SELECT("cfg_collection_id");
        sql.SELECT("status");
        sql.SELECT("applicant_aid");
        sql.SELECT("reviewer_aid");
        sql.SELECT("submit_time");
        sql.SELECT("handle_time");
        sql.FROM("cfg_audit");
        applyWhere(sql, example, false);
        
        if (example != null && example.getOrderByClause() != null) {
            sql.ORDER_BY(example.getOrderByClause());
        }
        
        return sql.toString();
    }

    public String updateByExampleSelective(Map<String, Object> parameter) {
        CfgAudit record = (CfgAudit) parameter.get("record");
        CfgAuditExample example = (CfgAuditExample) parameter.get("example");
        
        SQL sql = new SQL();
        sql.UPDATE("cfg_audit");
        
        if (record.getId() != null) {
            sql.SET("id = #{record.id,jdbcType=INTEGER}");
        }
        
        if (record.getCfgCollectionId() != null) {
            sql.SET("cfg_collection_id = #{record.cfgCollectionId,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{record.status,jdbcType=INTEGER}");
        }
        
        if (record.getApplicantAid() != null) {
            sql.SET("applicant_aid = #{record.applicantAid,jdbcType=INTEGER}");
        }
        
        if (record.getReviewerAid() != null) {
            sql.SET("reviewer_aid = #{record.reviewerAid,jdbcType=INTEGER}");
        }
        
        if (record.getSubmitTime() != null) {
            sql.SET("submit_time = #{record.submitTime,jdbcType=BIGINT}");
        }
        
        if (record.getHandleTime() != null) {
            sql.SET("handle_time = #{record.handleTime,jdbcType=BIGINT}");
        }
        
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByExample(Map<String, Object> parameter) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_audit");
        
        sql.SET("id = #{record.id,jdbcType=INTEGER}");
        sql.SET("cfg_collection_id = #{record.cfgCollectionId,jdbcType=INTEGER}");
        sql.SET("status = #{record.status,jdbcType=INTEGER}");
        sql.SET("applicant_aid = #{record.applicantAid,jdbcType=INTEGER}");
        sql.SET("reviewer_aid = #{record.reviewerAid,jdbcType=INTEGER}");
        sql.SET("submit_time = #{record.submitTime,jdbcType=BIGINT}");
        sql.SET("handle_time = #{record.handleTime,jdbcType=BIGINT}");
        
        CfgAuditExample example = (CfgAuditExample) parameter.get("example");
        applyWhere(sql, example, true);
        return sql.toString();
    }

    public String updateByPrimaryKeySelective(CfgAudit record) {
        SQL sql = new SQL();
        sql.UPDATE("cfg_audit");
        
        if (record.getCfgCollectionId() != null) {
            sql.SET("cfg_collection_id = #{cfgCollectionId,jdbcType=INTEGER}");
        }
        
        if (record.getStatus() != null) {
            sql.SET("status = #{status,jdbcType=INTEGER}");
        }
        
        if (record.getApplicantAid() != null) {
            sql.SET("applicant_aid = #{applicantAid,jdbcType=INTEGER}");
        }
        
        if (record.getReviewerAid() != null) {
            sql.SET("reviewer_aid = #{reviewerAid,jdbcType=INTEGER}");
        }
        
        if (record.getSubmitTime() != null) {
            sql.SET("submit_time = #{submitTime,jdbcType=BIGINT}");
        }
        
        if (record.getHandleTime() != null) {
            sql.SET("handle_time = #{handleTime,jdbcType=BIGINT}");
        }
        
        sql.WHERE("id = #{id,jdbcType=INTEGER}");
        
        return sql.toString();
    }

    protected void applyWhere(SQL sql, CfgAuditExample example, boolean includeExamplePhrase) {
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