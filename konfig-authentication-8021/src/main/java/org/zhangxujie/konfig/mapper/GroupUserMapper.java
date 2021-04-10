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
import org.zhangxujie.konfig.model.GroupUser;
import org.zhangxujie.konfig.model.GroupUserExample;

public interface GroupUserMapper {
    @SelectProvider(type=GroupUserSqlProvider.class, method="countByExample")
    long countByExample(GroupUserExample example);

    @DeleteProvider(type=GroupUserSqlProvider.class, method="deleteByExample")
    int deleteByExample(GroupUserExample example);

    @Delete({
        "delete from auth_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into auth_user (id, account_id, ",
        "group_id, update_time, ",
        "update_account_id, is_del)",
        "values (#{id,jdbcType=INTEGER}, #{accountId,jdbcType=INTEGER}, ",
        "#{groupId,jdbcType=INTEGER}, #{updateTime,jdbcType=BIGINT}, ",
        "#{updateAccountId,jdbcType=INTEGER}, #{isDel,jdbcType=INTEGER})"
    })
    int insert(GroupUser record);

    @InsertProvider(type=GroupUserSqlProvider.class, method="insertSelective")
    int insertSelective(GroupUser record);

    @SelectProvider(type=GroupUserSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_account_id", property="updateAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    List<GroupUser> selectByExample(GroupUserExample example);

    @Select({
        "select",
        "id, account_id, group_id, update_time, update_account_id, is_del",
        "from auth_user",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="group_id", property="groupId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="update_account_id", property="updateAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    GroupUser selectByPrimaryKey(Integer id);

    @UpdateProvider(type=GroupUserSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    @UpdateProvider(type=GroupUserSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") GroupUser record, @Param("example") GroupUserExample example);

    @UpdateProvider(type=GroupUserSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(GroupUser record);

    @Update({
        "update auth_user",
        "set account_id = #{accountId,jdbcType=INTEGER},",
          "group_id = #{groupId,jdbcType=INTEGER},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "update_account_id = #{updateAccountId,jdbcType=INTEGER},",
          "is_del = #{isDel,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(GroupUser record);
}