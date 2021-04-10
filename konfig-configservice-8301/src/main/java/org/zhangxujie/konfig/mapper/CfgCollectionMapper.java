package org.zhangxujie.konfig.mapper;

import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.model.CfgCollectionExample;

public interface CfgCollectionMapper {
    @SelectProvider(type=CfgCollectionSqlProvider.class, method="countByExample")
    long countByExample(CfgCollectionExample example);

    @DeleteProvider(type=CfgCollectionSqlProvider.class, method="deleteByExample")
    int deleteByExample(CfgCollectionExample example);

    @Delete({
            "delete from cfg_config_collection",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
            "insert into cfg_config_collection (id, collection_id, ",
            "c_name, is_del, is_draft, ",
            "update_username, create_username, ",
            "update_time, create_time)",
            "values (#{id,jdbcType=INTEGER}, #{collectionId,jdbcType=INTEGER}, ",
            "#{cName,jdbcType=VARCHAR}, #{isDel,jdbcType=INTEGER}, #{isDraft,jdbcType=INTEGER}, ",
            "#{updateUsername,jdbcType=VARCHAR}, #{createUsername,jdbcType=VARCHAR}, ",
            "#{updateTime,jdbcType=BIGINT}, #{createTime,jdbcType=BIGINT})"
    })
    @Options(useGeneratedKeys=true, keyProperty="id")
    int insert(CfgCollection record);

    @InsertProvider(type=CfgCollectionSqlProvider.class, method="insertSelective")
    int insertSelective(CfgCollection record);

    @SelectProvider(type=CfgCollectionSqlProvider.class, method="selectByExample")
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="c_name", property="cName", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<CfgCollection> selectByExample(CfgCollectionExample example);

    @Select({
            "select",
            "id, collection_id, c_name, is_del, is_draft, update_username, create_username, ",
            "update_time, create_time",
            "from cfg_config_collection",
            "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="collection_id", property="collectionId", jdbcType=JdbcType.INTEGER),
            @Result(column="c_name", property="cName", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    CfgCollection selectByPrimaryKey(Integer id);

    @UpdateProvider(type=CfgCollectionSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") CfgCollection record, @Param("example") CfgCollectionExample example);

    @UpdateProvider(type=CfgCollectionSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") CfgCollection record, @Param("example") CfgCollectionExample example);

    @UpdateProvider(type=CfgCollectionSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(CfgCollection record);

    @Update({
            "update cfg_config_collection",
            "set collection_id = #{collectionId,jdbcType=INTEGER},",
            "c_name = #{cName,jdbcType=VARCHAR},",
            "is_del = #{isDel,jdbcType=INTEGER},",
            "is_draft = #{isDraft,jdbcType=INTEGER},",
            "update_username = #{updateUsername,jdbcType=VARCHAR},",
            "create_username = #{createUsername,jdbcType=VARCHAR},",
            "update_time = #{updateTime,jdbcType=BIGINT},",
            "create_time = #{createTime,jdbcType=BIGINT}",
            "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(CfgCollection record);

    @Select({
            "select",
            "id, collection_id, c_name, is_del, is_draft, update_username, create_username, ",
            "update_time, create_time",
            "from cfg_config_collection",
            "where is_del=0 and c_name like concat('%',#{nameLike,javaType=STRING},'%')",
            "order by id limit #{pageNum,javaType=INTEGER}, #{nums,javaType=INTEGER}"
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="c_name", property="cName", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<CfgCollection> queryAll(@Param("nameLike") String nameLike,@Param("pageNum") Integer pageNum, @Param("nums")Integer nums);


    @Select({
            "select",
            "id, collection_id, c_name, is_del, is_draft, update_username, create_username, ",
            "update_time, create_time",
            "from cfg_config_collection",
            "where is_del=0 and c_name like concat('%',#{nameLike,javaType=STRING},'%')",
            "order by id desc limit #{pageNum,javaType=INTEGER}, #{nums,javaType=INTEGER}",
    })
    @Results({
            @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
            @Result(column="c_name", property="cName", jdbcType=JdbcType.VARCHAR),
            @Result(column="is_del", property="isDel", jdbcType=JdbcType.INTEGER),
            @Result(column="is_draft", property="isDraft", jdbcType=JdbcType.INTEGER),
            @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="create_username", property="createUsername", jdbcType=JdbcType.VARCHAR),
            @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
            @Result(column="create_time", property="createTime", jdbcType=JdbcType.BIGINT)
    })
    List<CfgCollection> queryAllDesc(@Param("nameLike") String nameLike,@Param("pageNum") Integer pageNum, @Param("nums")Integer nums);
}