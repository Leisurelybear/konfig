/**
 * FileName: AccountRemoteServiceImpl
 * Author:   jason
 * Date:     2021/5/20 11:54
 * Description:
 */
package org.zhangxujie.konfig.dao.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.Account;
import org.zhangxujie.konfig.model.Group;

import javax.annotation.Resource;
import java.util.*;

@Repository
@Slf4j
public class AccountRemoteServiceImpl implements AccountRemoteService {


    @Value("${rpc.url.auth}")
    private String INVOKE_URL;

    @Resource
    private RestTemplate restTemplate;

    @Override
    public boolean validateToken(String token) {
        log.info("token is [" + token + "]");

        Boolean isValid = restTemplate.getForObject(INVOKE_URL + "/rpc/verify/" + token, Boolean.class);

        log.info("token is valid [" + isValid + "]");

        return isValid;
    }

    @Override
    public InfoRemote infoFromToken(String token) {
        log.info("token is [" + token + "]");

        InfoRemote info = restTemplate.getForObject(INVOKE_URL + "/rpc/info/" + token, InfoRemote.class);

        if (info == null) {
            log.info("rpc get user is null!");
        } else {
            log.info("rpc get userId is [" + info.getAccountId() + "]");
        }

        return info;
    }

    @Override
    public List<Integer> getGroupIdListByAccountId(Integer accountId) {
        log.info("accountId is [" + accountId + "]");

        List<Integer> groupIds = restTemplate.getForObject(INVOKE_URL + "/rpc/group/list_by_aid/" + accountId, List.class);

        if (groupIds == null || groupIds.size() == 0) {
            log.info("rpc get group list is null!");
        }
        return groupIds;

    }

    @Override
    public List<Account> getUsersByAid(List<Integer> accountIds) {

        log.info("accountIds is [" + Arrays.toString(accountIds.toArray()) + "]");
        if (accountIds == null || accountIds.size() == 0){
            return new ArrayList<>();
        }

        String aids = "";
        for (Integer accountId : accountIds) {
            aids += accountId + "_";
        }
        aids = aids.substring(0, aids.length() - 1);

        List accounts = restTemplate.getForObject(INVOKE_URL + "/rpc/user/list_by_aids/" + aids, List.class);

        accounts.forEach(c -> {
            ((LinkedHashMap)c).put("password", "");
        });

        System.out.println(accounts);


        return accounts;
    }

    @Override
    public List<Group> getGroupsByAid(List<Integer> groupIds) {
        log.info("groupIds is [" + Arrays.toString(groupIds.toArray()) + "]");

        if (groupIds == null || groupIds.size() == 0){
            return new ArrayList<>();
        }

        String gids = "";
        for (Integer groupId : groupIds) {
            gids += groupId + "_";
        }
        gids = gids.substring(0, gids.length() - 1);

        List groups = restTemplate.getForObject(INVOKE_URL + "/rpc/group/list_by_gids/" + gids, List.class);

        System.out.println(groups);

        return groups;
    }
}
