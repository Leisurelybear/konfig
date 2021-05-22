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
import org.zhangxujie.konfig.model.CfgPermission;
import org.zhangxujie.konfig.model.CfgPermissionExample;

public interface CfgPermissionMapper {
    @SelectProvider(type=CfgPermissionSqlProvider.class, method="countByExample")
    long countByExample(CfgPermissionExample example);

    @DeleteProvider(type=CfgPermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(CfgPermissionExample example);

    @Delete({
        "delete from cfg_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into cfg_permission (id, type, ",
        "account_id, group_id, ",
        "collection_id, create_time, ",
        "create_username, create_account_id, ",
        "is_del)",
        "values (#{id,jdbcType=INTEGER}, #{type,jdbcType=INTEGER}, ",
        "#{accountId,jdbcType=INTEGER}, #{groupId,jdbcType=INTEGER}, ",
        "#{collectionId,jdbcType=INTEGER}, #{createTime,jdbcType=BIGINT}, ",
        "#{createUsername,jdbcType=VARCHAR}, #{createAccountId,jdbcType=INTEGER}, ",
        "#{isDel,jdbcType=INTEGER})"
    })
    int insert(CfgPermission record);

    @InsertProvider(type=CfgPermissionSqlProvider.class, method="insertSelective")
    int insertSelective(CfgPermission record);

    @SelectProvider(type=CfgPermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_account_id", property="createAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    List<CfgPermission> selectByExample(CfgPermissionExample example);

    @Select({
        "select",
        "id, type, account_id, group_id, collection_id, create_time, create_username, ",
        "create_account_id, is_del",
        "from cfg_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="type", property="type", jdbcType=JdbcType.INTEGER),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
        @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT),
        @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="create_account_id", property="createAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    CfgPermission selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CfgPermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CfgPermission record, @Param("example") CfgPermissionExample example);

    @UpdateProvider(type=CfgPermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CfgPermission record, @Param("example") CfgPermissionExample example);

    @UpdateProvider(type=CfgPermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CfgPermission record);

    @Update({
        "update cfg_permission",
        "set type = #{type,jdbcType=INTEGER},",
          "account_id = #{accountId,jdbcType=INTEGER},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "collection_id = #{collectionId,jdbcType=INTEGER},",
          "create_time = #{createTime,jdbcType=BIGINT},",
          "create_username = #{createUsername,jdbcType=VARCHAR},",
          "create_account_id = #{createAccountId,jdbcType=INTEGER},",
          "is_del = #{isDel,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CfgPermission record);
}