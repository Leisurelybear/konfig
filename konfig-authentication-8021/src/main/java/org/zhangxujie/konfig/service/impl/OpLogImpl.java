/**
 * FileName: OpLogImpl
 * Author:   jason
 * Date:     2021/6/2 14:41
 * Description:
 */
package org.zhangxujie.konfig.service.impl;


import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.mapper.OpLogMapper;
import org.zhangxujie.konfig.model.OpLog;
import org.zhangxujie.konfig.model.OpLogExample;
import org.zhangxujie.konfig.util.TimeUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class OpLogImpl implements LogUtil {

    @Resource
    private OpLogMapper opLogMapper;

    @Override
    public boolean insert(String opType, String log, String oldData, String newData, String opUsername, Integer opUserid) {

        OpLog opLog = new OpLog();
        opLog.setDataBefore(oldData);
        opLog.setDataAfter(newData);
        opLog.setLog(log);
        opLog.setOpType(opType);
        opLog.setUpdateAccountId(opUserid);
        opLog.setUpdateUsername(opUsername);
        opLog.setUpdateTime(TimeUtil.getNowTimestamp());

        opLogMapper.insert(opLog);

        return true;
    }

    @Override
    public List<OpLog> list(String opType, Integer pageNumber, Integer pageSize) {

        OpLogExample example = new OpLogExample();
        example.setOrderByClause("update_time desc");
        OpLogExample.Criteria criteria = example.createCriteria();

        if (opType != null && !opType.equals("")) {
            criteria.andOpTypeEqualTo(opType);
        }

        return opLogMapper.selectByExample(example);
    }

    @Override
    public Integer count(String opType) {

        OpLogExample example = new OpLogExample();
        OpLogExample.Criteria criteria = example.createCriteria();

        if (opType != null && !opType.equals("")) {
            criteria.andOpTypeEqualTo(opType);
        }

        return (int) opLogMapper.countByExample(example);
    }
}
