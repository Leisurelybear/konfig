/**
 * FileName: ConfigPermissionServiceImpl
 * Author:   jason
 * Date:     2021/5/20 16:51
 * Description:
 */
package org.zhangxujie.konfig.service.impl;

import org.springframework.stereotype.Service;
import org.zhangxujie.konfig.dto.ListPermissionResp;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.mapper.CfgPermissionMapper;
import org.zhangxujie.konfig.model.CfgPermission;
import org.zhangxujie.konfig.model.CfgPermissionExample;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.service.CfgPermissionService;
import org.zhangxujie.konfig.util.TimeUtil;

import javax.annotation.Resource;
import java.util.*;

@Service
public class CfgPermissionServiceImpl implements CfgPermissionService {

    @Resource
    private CfgPermissionMapper cfgPermissionMapper;

    @Resource
    private AccountRemoteService accountRemoteService;


    @Override
    public boolean hasPermission(String token, Integer collectionId) {

        InfoRemote user = accountRemoteService.infoFromToken(token);
        if (user == null) {
            return false;
        }

        //1、先判断单用户权限
        CfgPermissionExample example = new CfgPermissionExample();
        example.createCriteria()
                .andIsDelEqualTo(0)
                .andAccountIdEqualTo(user.getAccountId())
                .andCollectionIdEqualTo(collectionId);
        long count = cfgPermissionMapper.countByExample(example);

        if (count > 0) {
            return true;
        }

        //2、如果单用户没权限，再判断用户组权限
        List<Integer> groupIds = accountRemoteService.getGroupIdListByAccountId(user.getAccountId());

        CfgPermissionExample example1 = new CfgPermissionExample();
        example1.createCriteria()
                .andIsDelEqualTo(0)
                .andGroupIdIn(groupIds)
                .andCollectionIdEqualTo(collectionId);
        count = cfgPermissionMapper.countByExample(example1);
        if (count > 0) {
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
    public Integer initCreateUserPermission(Integer accountId, Integer collectionId, String createUsername, Integer createAccountId) {

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
    public ListPermissionResp list(List<Integer> accountIds, List<Integer> collectionIds, List<Integer> groupsIds, Integer pageNumber, Integer pageSize) {

        CfgPermissionExample example = new CfgPermissionExample();
        pageNumber = pageNumber <= 0 ? 1 : pageNumber;
        String limitParam = "limit " + (pageNumber - 1) * pageSize + " , " + pageSize;
        if (pageSize <= 0) { //如果pageSize小于0，则查询全部
            limitParam = "";
        }
        example.setOrderByClause("create_time desc " + limitParam);
        CfgPermissionExample.Criteria criteria = example.createCriteria().andIsDelEqualTo(0);

        //查询用户 or 用户组
        if (accountIds != null && accountIds.size() != 0) {
            criteria.andAccountIdIn(accountIds);
        } else if (groupsIds != null && groupsIds.size() != 0) {
            criteria.andGroupIdIn(groupsIds);
        }

        //查询 collectionID
        if (collectionIds != null && collectionIds.size() != 0) {
            criteria.andCollectionIdIn(collectionIds);
        }

        List<CfgPermission> cfgPermissionList = cfgPermissionMapper.selectByExample(example);
        long count = cfgPermissionMapper.countByExample(example);

        ListPermissionResp resp = new ListPermissionResp();
        resp.setPermissionList(cfgPermissionList);
        resp.setPageNumber(pageNumber);
        resp.setPageSize(pageSize);
        resp.setCount((int) count);


        return resp;
    }

    @Override
    public boolean createUserPermission(String createUsername, Integer createAccountId, Integer collectionId, List<Integer> accountIds) {

        //先去检查是否已经有有权限的，如果有则跳过
        CfgPermissionExample example = new CfgPermissionExample();
        example.createCriteria().andIsDelEqualTo(0)
                .andAccountIdIn(accountIds);
        List<CfgPermission> permissionList = cfgPermissionMapper.selectByExample(example);
        Set<Integer> set = new HashSet<>();
        permissionList.forEach(c -> {
            set.add(c.getAccountId());
        });

        for (Integer accountId : accountIds) {
            if (set.contains(accountId)){
                continue;
            }
            CfgPermission cfgPermission = new CfgPermission();
            cfgPermission.setType(0); //用户权限
            cfgPermission.setGroupId(0);
            cfgPermission.setAccountId(accountId);
            cfgPermission.setCollectionId(collectionId);
            cfgPermission.setCreateTime(TimeUtil.getNowTimestamp());
            cfgPermission.setCreateAccountId(createAccountId);
            cfgPermission.setCreateUsername(createUsername);
            cfgPermission.setIsDel(0);

            cfgPermissionMapper.insert(cfgPermission);
        }

        return true;
    }

    @Override
    public boolean createGroupPermission(String createUsername, Integer createAccountId, Integer collectionId, List<Integer> groupIds) {

        //先去检查是否已经有有权限的，如果有则跳过
        CfgPermissionExample example = new CfgPermissionExample();
        example.createCriteria().andIsDelEqualTo(0)
                .andGroupIdIn(groupIds);
        List<CfgPermission> permissionList = cfgPermissionMapper.selectByExample(example);
        Set<Integer> set = new HashSet<>();
        permissionList.forEach(c -> {
            set.add(c.getGroupId());
        });

        for (Integer groupId : groupIds) {
            if (set.contains(groupId)){
                continue;
            }
            CfgPermission cfgPermission = new CfgPermission();
            cfgPermission.setType(0); //用户权限
            cfgPermission.setGroupId(groupId);
            cfgPermission.setAccountId(0);
            cfgPermission.setCollectionId(collectionId);
            cfgPermission.setCreateTime(TimeUtil.getNowTimestamp());
            cfgPermission.setCreateAccountId(createAccountId);
            cfgPermission.setCreateUsername(createUsername);
            cfgPermission.setIsDel(0);

            cfgPermissionMapper.insert(cfgPermission);
        }

        return true;
    }

    @Override
    public boolean remove(Integer cfgPermissionId, Integer accountId) {

        CfgPermission cfgPermission = cfgPermissionMapper.selectByPrimaryKey(cfgPermissionId);

        if (cfgPermission.getAccountId().equals(accountId)){
            //不能删除自己的这个配置权限，否则会有问题
            return false;
        }

        cfgPermission.setIsDel(1);
        cfgPermissionMapper.updateByPrimaryKeySelective(cfgPermission);
        return true;
    }
}
