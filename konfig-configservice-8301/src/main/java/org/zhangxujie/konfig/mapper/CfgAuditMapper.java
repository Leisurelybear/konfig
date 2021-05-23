package org.zhangxujie.konfig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.zhangxujie.konfig.model.CfgAudit;
import org.zhangxujie.konfig.model.CfgAuditExample;

public interface CfgAuditMapper {
    @SelectProvider(type=CfgAuditSqlProvider.class, method="countByExample")
    long countByExample(CfgAuditExample example);

    @DeleteProvider(type=CfgAuditSqlProvider.class, method="deleteByExample")
    int deleteByExample(CfgAuditExample example);

    @Delete({
            "delete from cfg_audit",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into cfg_audit (id, cfg_collection_id, ",
            "content, status, ",
            "applicant_aid, reviewer_aid, ",
            "submit_time, handle_time)",
            "values (#{id,jdbcType=INTEGER}, #{cfgCollectionId,jdbcType=INTEGER}, ",
            "#{content,jdbcType=VARCHAR}, #{status,jdbcType=INTEGER}, ",
            "#{applicantAid,jdbcType=INTEGER}, #{reviewerAid,jdbcType=INTEGER}, ",
            "#{submitTime,jdbcType=BIGINT}, #{handleTime,jdbcType=BIGINT})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(CfgAudit record);

    @InsertProvider(type=CfgAuditSqlProvider.class, method="insertSelective")
    int insertSelective(CfgAudit record);

    @SelectProvider(type=CfgAuditSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="cfg_collection_id", property="cfgCollectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
            @Result(column="applicant_aid", property="applicantAid", jdbcType=JdbcType.INTEGER),
            @Result(column="reviewer_aid", property="reviewerAid", jdbcType=JdbcType.INTEGER),
            @Result(column="submit_time", property="submitTime", jdbcType=JdbcType.BIGINT),
            @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.BIGINT)
    })
    List<CfgAudit> selectByExample(CfgAuditExample example);

    @Select({
            "select",
            "id, cfg_collection_id, content, status, applicant_aid, reviewer_aid, submit_time, ",
            "handle_time",
            "from cfg_audit",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="cfg_collection_id", property="cfgCollectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="content", property="content", jdbcType=JdbcType.VARCHAR),
            @Result(column="status", property="status", jdbcType=JdbcType.INTEGER),
            @Result(column="applicant_aid", property="applicantAid", jdbcType=JdbcType.INTEGER),
            @Result(column="reviewer_aid", property="reviewerAid", jdbcType=JdbcType.INTEGER),
            @Result(column="submit_time", property="submitTime", jdbcType=JdbcType.BIGINT),
            @Result(column="handle_time", property="handleTime", jdbcType=JdbcType.BIGINT)
    })
    CfgAudit selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CfgAuditSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CfgAudit record, @Param("example") CfgAuditExample example);

    @UpdateProvider(type=CfgAuditSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CfgAudit record, @Param("example") CfgAuditExample example);

    @UpdateProvider(type=CfgAuditSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CfgAudit record);

    @Update({
            "update cfg_audit",
            "set cfg_collection_id = #{cfgCollectionId,jdbcType=INTEGER},",
            "content = #{content,jdbcType=VARCHAR},",
            "status = #{status,jdbcType=INTEGER},",
            "applicant_aid = #{applicantAid,jdbcType=INTEGER},",
            "reviewer_aid = #{reviewerAid,jdbcType=INTEGER},",
            "submit_time = #{submitTime,jdbcType=BIGINT},",
            "handle_time = #{handleTime,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CfgAudit record);
}