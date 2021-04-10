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
import org.zhangxujie.konfig.model.UserInfo;
import org.zhangxujie.konfig.model.UserInfoExample;

public interface UserInfoMapper {
    @SelectProvider(type=UserInfoSqlProvider.class, method="countByExample")
    long countByExample(UserInfoExample example);

    @DeleteProvider(type=UserInfoSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserInfoExample example);

    @Delete({
        "delete from user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_info (id, account_id, ",
        "nickname, phone, ",
        "img_url, last_accessed_time, ",
        "extra, update_time, ",
        "is_del)",
        "values (#{id,jdbcType=INTEGER}, #{accountId,jdbcType=INTEGER}, ",
        "#{nickname,jdbcType=VARCHAR}, #{phone,jdbcType=VARCHAR}, ",
        "#{imgUrl,jdbcType=VARCHAR}, #{lastAccessedTime,jdbcType=BIGINT}, ",
        "#{extra,jdbcType=VARCHAR}, #{updateTime,jdbcType=BIGINT}, ",
        "#{isDel,jdbcType=INTEGER})"
    })
    int insert(UserInfo record);

    @InsertProvider(type=UserInfoSqlProvider.class, method="insertSelective")
    int insertSelective(UserInfo record);

    @SelectProvider(type=UserInfoSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_accessed_time", property="lastAccessedTime", jdbcType=JdbcType.BIGINT),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    List<UserInfo> selectByExample(UserInfoExample example);

    @Select({
        "select",
        "id, account_id, nickname, phone, img_url, last_accessed_time, extra, update_time, ",
        "is_del",
        "from user_info",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
        @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
        @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
        @Result(column="last_accessed_time", property="lastAccessedTime", jdbcType=JdbcType.BIGINT),
        @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    UserInfo selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserInfo record, @Param("example") UserInfoExample example);

    @UpdateProvider(type=UserInfoSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserInfo record);

    @Update({
        "update user_info",
        "set account_id = #{accountId,jdbcType=INTEGER},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "phone = #{phone,jdbcType=VARCHAR},",
          "img_url = #{imgUrl,jdbcType=VARCHAR},",
          "last_accessed_time = #{lastAccessedTime,jdbcType=BIGINT},",
          "extra = #{extra,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "is_del = #{isDel,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserInfo record);



    @Select({
            "select",
            "id, account_id, nickname, phone, img_url, last_accessed_time, extra, update_time, ",
            "is_del",
            "from user_info",
            "where id = #{account_id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
            @Result(column="nickname", property="nickname", jdbcType=JdbcType.VARCHAR),
            @Result(column="phone", property="phone", jdbcType=JdbcType.VARCHAR),
            @Result(column="img_url", property="imgUrl", jdbcType=JdbcType.VARCHAR),
            @Result(column="last_accessed_time", property="lastAccessedTime", jdbcType=JdbcType.BIGINT),
            @Result(column="extra", property="extra", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    UserInfo selectByAccountId(Integer accountId);
}