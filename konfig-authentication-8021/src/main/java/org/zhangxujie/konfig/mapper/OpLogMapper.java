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
import org.zhangxujie.konfig.model.OpLog;
import org.zhangxujie.konfig.model.OpLogExample;

public interface OpLogMapper {
    @SelectProvider(type=OpLogSqlProvider.class, method="countByExample")
    long countByExample(OpLogExample example);

    @DeleteProvider(type=OpLogSqlProvider.class, method="deleteByExample")
    int deleteByExample(OpLogExample example);

    @Delete({
        "delete from op_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int deleteByPrimaryKey(Integer id);

    @Insert({
        "insert into op_log (id, log, ",
        "update_account_id, update_username, ",
        "update_time, op_type, ",
        "op_table, op_field, ",
        "data_before, data_after)",
        "values (#{id,jdbcType=INTEGER}, #{log,jdbcType=VARCHAR}, ",
        "#{updateAccountId,jdbcType=INTEGER}, #{updateUsername,jdbcType=VARCHAR}, ",
        "#{updateTime,jdbcType=BIGINT}, #{opType,jdbcType=VARCHAR}, ",
        "#{opTable,jdbcType=VARCHAR}, #{opField,jdbcType=VARCHAR}, ",
        "#{dataBefore,jdbcType=VARCHAR}, #{dataAfter,jdbcType=VARCHAR})"
    })
    int insert(OpLog record);

    @InsertProvider(type=OpLogSqlProvider.class, method="insertSelective")
    int insertSelective(OpLog record);

    @SelectProvider(type=OpLogSqlProvider.class, method="selectByExample")
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="log", property="log", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_account_id", property="updateAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="op_type", property="opType", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_table", property="opTable", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_field", property="opField", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_before", property="dataBefore", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_after", property="dataAfter", jdbcType=JdbcType.VARCHAR)
    })
    List<OpLog> selectByExample(OpLogExample example);

    @Select({
        "select",
        "id, log, update_account_id, update_username, update_time, op_type, op_table, ",
        "op_field, data_before, data_after",
        "from op_log",
        "where id = #{id,jdbcType=INTEGER}"
    })
    @Results({
        @Result(column="id", property="id", jdbcType=JdbcType.INTEGER, id=true),
        @Result(column="log", property="log", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_account_id", property="updateAccountId", jdbcType=JdbcType.INTEGER),
        @Result(column="update_username", property="updateUsername", jdbcType=JdbcType.VARCHAR),
        @Result(column="update_time", property="updateTime", jdbcType=JdbcType.BIGINT),
        @Result(column="op_type", property="opType", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_table", property="opTable", jdbcType=JdbcType.VARCHAR),
        @Result(column="op_field", property="opField", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_before", property="dataBefore", jdbcType=JdbcType.VARCHAR),
        @Result(column="data_after", property="dataAfter", jdbcType=JdbcType.VARCHAR)
    })
    OpLog selectByPrimaryKey(Integer id);

    @UpdateProvider(type=OpLogSqlProvider.class, method="updateByExampleSelective")
    int updateByExampleSelective(@Param("record") OpLog record, @Param("example") OpLogExample example);

    @UpdateProvider(type=OpLogSqlProvider.class, method="updateByExample")
    int updateByExample(@Param("record") OpLog record, @Param("example") OpLogExample example);

    @UpdateProvider(type=OpLogSqlProvider.class, method="updateByPrimaryKeySelective")
    int updateByPrimaryKeySelective(OpLog record);

    @Update({
        "update op_log",
        "set log = #{log,jdbcType=VARCHAR},",
          "update_account_id = #{updateAccountId,jdbcType=INTEGER},",
          "update_username = #{updateUsername,jdbcType=VARCHAR},",
          "update_time = #{updateTime,jdbcType=BIGINT},",
          "op_type = #{opType,jdbcType=VARCHAR},",
          "op_table = #{opTable,jdbcType=VARCHAR},",
          "op_field = #{opField,jdbcType=VARCHAR},",
          "data_before = #{dataBefore,jdbcType=VARCHAR},",
          "data_after = #{dataAfter,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=INTEGER}"
    })
    int updateByPrimaryKey(OpLog record);
}