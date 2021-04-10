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
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.AccountExample;

public interface AccountMapper {
    @SelectProvider(type = AccountSqlProvider.class, method = "countByExample")
    long countByExample(AccountExample example);

    @DeleteProvider(type = AccountSqlProvider.class, method = "deleteByExample")
    int deleteByExample(AccountExample example);

    @Delete({
            "delete from auth_account",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into auth_account (id, username, ",
            "password, salt, ",
            "email, is_del)",
            "values (#{id,jdbcType=INTEGER}, #{username,jdbcType=VARCHAR}, ",
            "#{password,jdbcType=VARCHAR}, #{salt,jdbcType=VARCHAR}, ",
            "#{email,jdbcType=VARCHAR}, #{isDel,jdbcType=INTEGER})"
    })
    int insert(Account record);

    @InsertProvider(type = AccountSqlProvider.class, method = "insertSelective")
    int insertSelective(Account record);

    @SelectProvider(type = AccountSqlProvider.class, method = "selectByExample")
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_del", property = "isDel", jdbcType = JdbcType.INTEGER)
    })
    List<Account> selectByExample(AccountExample example);

    @Select({
            "select",
            "id, username, password, salt, email, is_del",
            "from auth_account",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_del", property = "isDel", jdbcType = JdbcType.INTEGER)
    })
    Account selectByPrimaryKey(Integer id);

    @UpdateProvider(type = AccountSqlProvider.class, method = "updateByExampleSelective")
    int updateByExampleSelective(@Param("record") Account record, @Param("example") AccountExample example);

    @UpdateProvider(type = AccountSqlProvider.class, method = "updateByExample")
    int updateByExample(@Param("record") Account record, @Param("example") AccountExample example);

    @UpdateProvider(type = AccountSqlProvider.class, method = "updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(Account record);

    @Update({
            "update auth_account",
            "set username = #{username,jdbcType=VARCHAR},",
            "password = #{password,jdbcType=VARCHAR},",
            "salt = #{salt,jdbcType=VARCHAR},",
            "email = #{email,jdbcType=VARCHAR},",
            "is_del = #{isDel,jdbcType=INTEGER}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(Account record);


    @Select({
            "select",
            "id, username, password, salt, email, is_del",
            "from auth_account",
            "where id = #{username,jdbcType=VARCHAR}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_del", property = "isDel", jdbcType = JdbcType.INTEGER)
    })
    Account selectOneByUsername(String username);



    @Select({
            "select",
            "id, username, password, salt, email, is_del",
            "from auth_account",
            "where username like concat('%',#{usernameLike,javaType=STRING},'%')",
            " and email like concat('%',#{emailLike,javaType=STRING},'%') order by id",
            " limit #{pageNumber, javaType=INTEGER}, #{pageSize, javaType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_del", property = "isDel", jdbcType = JdbcType.INTEGER)
    })
    List<Account> queryAll(@Param("usernameLike") String usernameLike,@Param("emailLike") String emailLike, @Param("pageNumber")Integer pageNumber, @Param("pageSize")Integer pageSize);

    @Select({
            "select",
            "id, username, password, salt, email, is_del",
            "from auth_account",
            "where username like concat('%',#{usernameLike,javaType=STRING},'%')",
            " and email like concat('%',#{emailLike,javaType=STRING},'%') order by id desc",
            " limit #{pageNumber, javaType=INTEGER}, #{pageSize, javaType=INTEGER}"
    })
    @Results({
            @Result(column = "id", property = "id", jdbcType = JdbcType.INTEGER, id = true),
            @Result(column = "username", property = "username", jdbcType = JdbcType.VARCHAR),
            @Result(column = "password", property = "password", jdbcType = JdbcType.VARCHAR),
            @Result(column = "salt", property = "salt", jdbcType = JdbcType.VARCHAR),
            @Result(column = "email", property = "email", jdbcType = JdbcType.VARCHAR),
            @Result(column = "is_del", property = "isDel", jdbcType = JdbcType.INTEGER)
    })
    List<Account> queryAllDesc(@Param("usernameLike") String usernameLike,@Param("emailLike") String emailLike, @Param("pageNumber")Integer pageNumber, @Param("pageSize")Integer pageSize);


}