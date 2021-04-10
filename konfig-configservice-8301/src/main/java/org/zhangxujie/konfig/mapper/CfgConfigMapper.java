package org.zhangxujie.konfig.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.zhangxujie.konfig.model.CfgConfig;
import org.zhangxujie.konfig.model.CfgConfigExample;

public interface CfgConfigMapper {
    @SelectProvider(type=CfgConfigSqlProvider.class, method="countByExample")
    long countByExample(CfgConfigExample example);

    @DeleteProvider(type=CfgConfigSqlProvider.class, method="deleteByExample")
    int deleteByExample(CfgConfigExample example);

    @Delete({
            "delete from cfg_config",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into cfg_config (id, collection_id, ",
            "cfg_name, cfg_key, ",
            "is_del, is_draft, ",
            "update_username, create_username, ",
            "update_time, create_time, ",
            "cfg_value)",
            "values (#{id,jdbcType=INTEGER}, #{collectionId,jdbcType=INTEGER}, ",
            "#{cfgName,jdbcType=VARCHAR}, #{cfgKey,jdbcType=VARCHAR}, ",
            "#{isDel,jdbcType=INTEGER}, #{isDraft,jdbcType=INTEGER}, ",
            "#{updateUsername,jdbcType=VARCHAR}, #{createUsername,jdbcType=VARCHAR}, ",
            "#{updateTime,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT}, ",
            "#{cfgValue,jdbcType=LONGVARCHAR})"
    })
    int insert(CfgConfig record);

    @InsertProvider(type=CfgConfigSqlProvider.class, method="insertSelective")
    int insertSelective(CfgConfig record);

    @SelectProvider(type=CfgConfigSqlProvider.class, method="selectByExampleWithBLOBs")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="cfg_name", property="cfgName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cfg_key", property="cfgKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="cfg_value", property="cfgValue", jdbcType=JdbcType.LONGVARCHAR)
    })
    List<CfgConfig> selectByExampleWithBLOBs(CfgConfigExample example);

    @SelectProvider(type=CfgConfigSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="cfg_name", property="cfgName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cfg_key", property="cfgKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<CfgConfig> selectByExample(CfgConfigExample example);

    @Select({
            "select",
            "id, collection_id, cfg_name, cfg_key, is_del, is_draft, update_username, create_username, ",
            "update_time, create_time, cfg_value",
            "from cfg_config",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="cfg_name", property="cfgName", jdbcType=JdbcType.VARCHAR),
            @Result(column="cfg_key", property="cfgKey", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
            @Result(column="cfg_value", property="cfgValue", jdbcType=JdbcType.LONGVARCHAR)
    })
    CfgConfig selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CfgConfigSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CfgConfig record, @Param("example") CfgConfigExample example);

    @UpdateProvider(type=CfgConfigSqlProvider.class, method="updateByExampleWithBLOBs")
    int updateByExampleWithBLOBs(@Param("record") CfgConfig record, @Param("example") CfgConfigExample example);

    @UpdateProvider(type=CfgConfigSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CfgConfig record, @Param("example") CfgConfigExample example);

    @UpdateProvider(type=CfgConfigSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CfgConfig record);

    @Update({
            "update cfg_config",
            "set collection_id = #{collectionId,jdbcType=INTEGER},",
            "cfg_name = #{cfgName,jdbcType=VARCHAR},",
            "cfg_key = #{cfgKey,jdbcType=VARCHAR},",
            "is_del = #{isDel,jdbcType=INTEGER},",
            "is_draft = #{isDraft,jdbcType=INTEGER},",
            "update_username = #{updateUsername,jdbcType=VARCHAR},",
            "create_username = #{createUsername,jdbcType=VARCHAR},",
            "update_time = #{updateTime,jdbcType=BIGINT},",
            "create_time = #{createTime,jdbcType=BIGINT},",
            "cfg_value = #{cfgValue,jdbcType=LONGVARCHAR}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKeyWithBLOBs(CfgConfig record);

    @Update({
            "update cfg_config",
            "set collection_id = #{collectionId,jdbcType=INTEGER},",
            "cfg_name = #{cfgName,jdbcType=VARCHAR},",
            "cfg_key = #{cfgKey,jdbcType=VARCHAR},",
            "is_del = #{isDel,jdbcType=INTEGER},",
            "is_draft = #{isDraft,jdbcType=INTEGER},",
            "update_username = #{updateUsername,jdbcType=VARCHAR},",
            "create_username = #{createUsername,jdbcType=VARCHAR},",
            "update_time = #{updateTime,jdbcType=BIGINT},",
            "create_time = #{createTime,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CfgConfig record);
}