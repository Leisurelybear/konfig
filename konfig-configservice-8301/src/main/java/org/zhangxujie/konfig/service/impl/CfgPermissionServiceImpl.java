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
import java.util.List;

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
}
