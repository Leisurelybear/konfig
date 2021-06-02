package org.zhangxujie.konfig.common;

import org.zhangxujie.konfig.model.OpLog;

import java.util.List;

public interface LogUtil {

    /**
     * @Description: 插入日志
     * @Param opType: 操作类型，增删改查
     * @Param log: 日志内容
     * @Param oldData: 如果有数据，则存储旧数据
     * @Param newData: 如果有数据，则存储新数据
     * @Param opUsername: 操作者
     * @Param opUserid: 操作者ID
     * @return: boolean
     **/
    boolean insert(String opType, String log, String oldData, String newData, String opUsername, Integer opUserid);

    //分页查询
    List<OpLog> list(String opType, Integer pageNumber, Integer pageSize);

    //计数
    Integer count(String opType);


}
