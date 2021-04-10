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
import org.zhangxujie.konfig.model.Permission;
import org.zhangxujie.konfig.model.PermissionExample;

public interface PermissionMapper {
    @SelectProvider(type=PermissionSqlProvider.class, method="countByExample")
    long countByExample(PermissionExample example);

    @DeleteProvider(type=PermissionSqlProvider.class, method="deleteByExample")
    int deleteByExample(PermissionExample example);

    @Delete({
        "delete from auth_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into auth_permission (id, identity_type, ",
        "identity_id, permission, ",
        "comment, is_del, ",
        "time, operator_account_id)",
        "values (#{id,jdbcType=INTEGER}, #{identityType,jdbcType=VARCHAR}, ",
        "#{identityId,jdbcType=INTEGER}, #{permission,jdbcType=VARCHAR}, ",
        "#{comment,jdbcType=VARCHAR}, #{isDel,jdbcType=INTEGER}, ",
        "#{time,jdbcType=BIGINT}, #{operatorAccountId,jdbcType=INTEGER})"
    })
    int insert(Permission record);

    @InsertProvider(type=PermissionSqlProvider.class, method="insertSelective")
    int insertSelective(Permission record);

    @SelectProvider(type=PermissionSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="identity_type", property="identityType", jdbcType=JdbcType.VARCHAR),
        @Result(column="identity_id", property="identityId", jdbcType=JdbcType.INTEGER),
        @Result(column="permission", property="permission", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.BIGINT),
        @Result(column="operator_account_id", property="operatorAccountId", jdbcType=JdbcType.INTEGER)
    })
    List<Permission> selectByExample(PermissionExample example);

    @Select({
        "select",
        "id, identity_type, identity_id, permission, comment, is_del, time, operator_account_id",
        "from auth_permission",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="identity_type", property="identityType", jdbcType=JdbcType.VARCHAR),
        @Result(column="identity_id", property="identityId", jdbcType=JdbcType.INTEGER),
        @Result(column="permission", property="permission", jdbcType=JdbcType.VARCHAR),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
        @Result(column="time", property="time", jdbcType=JdbcType.BIGINT),
        @Result(column="operator_account_id", property="operatorAccountId", jdbcType=JdbcType.INTEGER)
    })
    Permission selectByPrimaryKey(Integer id);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Permission record, @Param("example") PermissionExample example);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Permission record, @Param("example") PermissionExample example);

    @UpdateProvider(type=PermissionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update auth_permission",
        "set identity_type = #{identityType,jdbcType=VARCHAR},",
          "identity_id = #{identityId,jdbcType=INTEGER},",
          "permission = #{permission,jdbcType=VARCHAR},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=INTEGER},",
          "time = #{time,jdbcType=BIGINT},",
          "operator_account_id = #{operatorAccountId,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Permission record);
}