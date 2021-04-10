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
import org.zhangxujie.konfig.model.UserFavorite;
import org.zhangxujie.konfig.model.UserFavoriteExample;

public interface UserFavoriteMapper {
    @SelectProvider(type=UserFavoriteSqlProvider.class, method="countByExample")
    long countByExample(UserFavoriteExample example);

    @DeleteProvider(type=UserFavoriteSqlProvider.class, method="deleteByExample")
    int deleteByExample(UserFavoriteExample example);

    @Delete({
        "delete from user_favorites",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into user_favorites (id, account_id, ",
        "cfg_type, cfg_id, ",
        "comment, is_del)",
        "values (#{id,jdbcType=INTEGER}, #{accountId,jdbcType=INTEGER}, ",
        "#{cfgType,jdbcType=INTEGER}, #{cfgId,jdbcType=INTEGER}, ",
        "#{comment,jdbcType=VARCHAR}, #{isDel,jdbcType=INTEGER})"
    })
    int insert(UserFavorite record);

    @InsertProvider(type=UserFavoriteSqlProvider.class, method="insertSelective")
    int insertSelective(UserFavorite record);

    @SelectProvider(type=UserFavoriteSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="cfg_type", property="cfgType", jdbcType=JdbcType.INTEGER),
        @Result(column="cfg_id", property="cfgId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    List<UserFavorite> selectByExample(UserFavoriteExample example);

    @Select({
        "select",
        "id, account_id, cfg_type, cfg_id, comment, is_del",
        "from user_favorites",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="account_id", property="accountId", jdbcType=JdbcType.INTEGER),
        @Result(column="cfg_type", property="cfgType", jdbcType=JdbcType.INTEGER),
        @Result(column="cfg_id", property="cfgId", jdbcType=JdbcType.INTEGER),
        @Result(column="comment", property="comment", jdbcType=JdbcType.VARCHAR),
        @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER)
    })
    UserFavorite selectByPrimaryKey(Integer id);

    @UpdateProvider(type=UserFavoriteSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") UserFavorite record, @Param("example") UserFavoriteExample example);

    @UpdateProvider(type=UserFavoriteSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") UserFavorite record, @Param("example") UserFavoriteExample example);

    @UpdateProvider(type=UserFavoriteSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(UserFavorite record);

    @Update({
        "update user_favorites",
        "set account_id = #{accountId,jdbcType=INTEGER},",
          "cfg_type = #{cfgType,jdbcType=INTEGER},",
          "cfg_id = #{cfgId,jdbcType=INTEGER},",
          "comment = #{comment,jdbcType=VARCHAR},",
          "is_del = #{isDel,jdbcType=INTEGER}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(UserFavorite record);
}