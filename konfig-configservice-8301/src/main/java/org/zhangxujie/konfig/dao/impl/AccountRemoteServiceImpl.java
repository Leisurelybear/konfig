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

import javax.annotation.Resource;
import java.util.List;

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

        if (info == null){
            log.info("rpc get user is null!");
        }else {
            log.info("rpc get userId is [" + info.getAccountId() + "]");
        }

        return info;
    }

    @Override
    public List<Integer> getGroupIdListByAccountId(Integer accountId) {
        log.info("accountId is [" + accountId + "]");

        List<Integer> groupIds = restTemplate.getForObject(INVOKE_URL + "/rpc/group/list_by_aid/" + accountId, List.class);

        if (groupIds == null || groupIds.size() == 0){
            log.info("rpc get group list is null!");
        }
        return groupIds;

    }
}
