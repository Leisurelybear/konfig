/**
 * FileName: ConfigPermissionServiceImpl
 * Author:   jason
 * Date:     2021/5/20 16:51
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.mapper.CfgPermissionMapper;
import org.zhangxujie.konfig.model.CfgPermission;
import org.zhangxujie.konfig.model.CfgPermissionExample;
import org.zhangxujie.konfig.dao.AccountRemoteDAO;
import org.zhangxujie.konfig.service.CfgPermissionService;
import org.zhangxujie.konfig.util.TimeUtil;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CfgPermissionServiceImpl implements CfgPermissionService {

    @Resource
    private CfgPermissionMapper cfgPermissionMapper;

    @Resource
    private AccountRemoteDAO accountRemoteDAO;


    @Override
    public boolean hasPermission(String token, Integer collectionId) {

        InfoRemote user = accountRemoteDAO.infoFromToken(token);
        if (user == null){
            return false;
        }

        //1、先判断单用户权限
        CfgPermissionExample example = new CfgPermissionExample();
        example.createCriteria()
                .andIsDelEqualTo(0)
                .andAccountIdEqualTo(user.getAccountId())
                .andCollectionIdEqualTo(collectionId);
        long count = cfgPermissionMapper.countByExample(example);

        if (count > 0){
            return true;
        }

        //2、如果单用户没权限，再判断用户组权限
        List<Integer> groupIds = accountRemoteDAO.getGroupIdListByAccountId(user.getAccountId());

        CfgPermissionExample example1 = new CfgPermissionExample();
        example1.createCriteria()
                .andIsDelEqualTo(0)
                .andGroupIdIn(groupIds)
                .andCollectionIdEqualTo(collectionId);
        count = cfgPermissionMapper.countByExample(example1);
        if (count > 0){
            return true;
        }

        return false;
    }

    @Override
    public List<Integer> getPermissionCollectionIdList(String token) {
        return null;
    }

    @Override
    public List<Integer> getPermissionUserIdList(Integer collectionId) {
        return null;
    }

    @Override
    public List<Integer> getPermissionCollectionIdListByGroupId(Integer groupId) {
        return null;
    }

    @Override
    public Integer addUserPermission(Integer accountId, Integer collectionId, String createUsername, Integer createAccountId) {

        CfgPermission cfgPermission = new CfgPermission();
        cfgPermission.setAccountId(accountId);
        cfgPermission.setCollectionId(collectionId);
        cfgPermission.setCreateTime(TimeUtil.getNowTimestamp());
        cfgPermission.setCreateUsername(createUsername);
        cfgPermission.setCreateAccountId(createAccountId);
        cfgPermission.setType(0);
        cfgPermission.setIsDel(0);

        int res = cfgPermissionMapper.insert(cfgPermission);

        return res;
    }

    @Override
    public Integer addGroupPermission(Integer groupId, Integer collectionId, String createUsername, String createAccountId) {
        return null;
    }
}
