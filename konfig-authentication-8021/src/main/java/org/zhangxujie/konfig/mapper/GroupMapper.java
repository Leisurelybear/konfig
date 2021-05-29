package org.zhangxujie.konfig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.model.GroupExample;

public interface GroupMapper {
    @SelectProvider(type=GroupSqlProvider.class, method="countByExample")
    long countByExample(GroupExample example);

    @DeleteProvider(type=GroupSqlProvider.class, method="deleteByExample")
    int deleteByExample(GroupExample example);

    @Delete({
        "delete from auth_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into auth_group (id, group_name, ",
        "group_role, root_account_id, ",
        "update_time, update_account_id, ",
        "is_del)",
        "values (#{id,jdbcType=INTEGER}, #{groupName,jdbcType=VARCHAR}, ",
        "#{groupRole,jdbcType=INTEGER}, #{rootAccountId,jdbcType=INTEGER}, ",
        "#{updateTime,jdbcType=BIGINT}, #{updateAccountId,jdbcType=INTEGER}, ",
        "#{isDel,jdbcType=INTEGER})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(Group record);

    @InsertProvider(type=GroupSqlProvider.class, method="insertSelective")
    int insertSelective(Group record);

    @SelectProvider(type=GroupSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_role", property="groupRole", jdbcType=JdbcType.INTEGER),
        @Result(column="root_account_id", property="rootAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_account_id", property="updateAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    List<Group> selectByExample(GroupExample example);

    @Select({
        "select",
        "id, group_name, group_role, root_account_id, update_time, update_account_id, ",
        "is_del",
        "from auth_group",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="group_name", property="groupName", jdbcType=JdbcType.VARCHAR),
        @Result(column="group_role", property="groupRole", jdbcType=JdbcType.INTEGER),
        @Result(column="root_account_id", property="rootAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_account_id", property="updateAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    Group selectByPrimaryKey(Integer id);

    @UpdateProvider(type=GroupSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Group record, @Param("example") GroupExample example);

    @UpdateProvider(type=GroupSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") Group record, @Param("example") GroupExample example);

    @UpdateProvider(type=GroupSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Group record);

    @Update({
        "update auth_group",
        "set group_name = #{groupName,jdbcType=VARCHAR},",
          "group_role = #{groupRole,jdbcType=INTEGER},",
          "root_account_id = #{rootAccountId,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "update_account_id = #{updateAccountId,jdbcType=INTEGER},",
          "is_del = #{isDel,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Group record);
}