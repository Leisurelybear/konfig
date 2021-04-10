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
import org.zhangxujie.konfig.model.AuthRole;
import org.zhangxujie.konfig.model.AuthRoleExample;

public interface AuthRoleMapper {
    @SelectProvider(type=AuthRoleSqlProvider.class, method="countByExample")
    long countByExample(AuthRoleExample example);

    @DeleteProvider(type=AuthRoleSqlProvider.class, method="deleteByExample")
    int deleteByExample(AuthRoleExample example);

    @Delete({
        "delete from auth_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into auth_role (id, role_name, ",
        "role_info)",
        "values (#{id,jdbcType=INTEGER}, #{roleName,jdbcType=VARCHAR}, ",
        "#{roleInfo,jdbcType=VARCHAR})"
    })
    int insert(AuthRole record);

    @InsertProvider(type=AuthRoleSqlProvider.class, method="insertSelective")
    int insertSelective(AuthRole record);

    @SelectProvider(type=AuthRoleSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_info", property="roleInfo", jdbcType=JdbcType.VARCHAR)
    })
    List<AuthRole> selectByExample(AuthRoleExample example);

    @Select({
        "select",
        "id, role_name, role_info",
        "from auth_role",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="role_name", property="roleName", jdbcType=JdbcType.VARCHAR),
        @Result(column="role_info", property="roleInfo", jdbcType=JdbcType.VARCHAR)
    })
    AuthRole selectByPrimaryKey(Integer id);

    @UpdateProvider(type=AuthRoleSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") AuthRole record, @Param("example") AuthRoleExample example);

    @UpdateProvider(type=AuthRoleSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") AuthRole record, @Param("example") AuthRoleExample example);

    @UpdateProvider(type=AuthRoleSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(AuthRole record);

    @Update({
        "update auth_role",
        "set role_name = #{roleName,jdbcType=VARCHAR},",
          "role_info = #{roleInfo,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(AuthRole record);
}