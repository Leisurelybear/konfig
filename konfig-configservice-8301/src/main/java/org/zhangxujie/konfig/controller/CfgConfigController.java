/**
 * FileName: CfgConfigController
 * Author:   jason
 * Date:     2021/4/9 0:49
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.common.Const;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.dto.*;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.model.CfgConfig;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgConfigService;
import org.zhangxujie.konfig.service.CfgPermissionService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/config")
@Api(tags = "CfgConfigController", description = "访问配置")
@CrossOrigin
public class CfgConfigController {

    @Resource
    private CfgConfigService cfgConfigService;

    @Resource
    private CfgCollectionService cfgCollectionService;

    @Resource
    private CfgPermissionService cfgPermissionService;

    @Resource
    private AccountRemoteService accountRemoteService;

    @Resource
    private LogUtil opLog;

    //查询对应collection中的配置信息
    @PostMapping("/list")
    public CommonResult<GetCfgConfigResp> getConfigs(@RequestBody GetCfgConfigReq req, @RequestParam("token") String token) {

        log.info("Token: " + token);
        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("Token失效，请重新登录！");
        }

        if (!cfgPermissionService.hasPermission(token, req.getCollectionIds().get(0))){
            return CommonResult.failed("您没有该记录的操作权限");
        }

        List<CfgConfig> confs = cfgConfigService.query(req.getCollectionIds(), req.getNameLike(), req.getKeyLike(), req.getSort());

        GetCfgConfigResp resp = new GetCfgConfigResp();
        resp.setConfs(confs);
        resp.setNum(confs.size());

        CfgCollection cfgCollection = cfgCollectionService.queryById(req.getCollectionIds().get(0));
        resp.setCollectionName(cfgCollection.getcName());
        resp.setCollectionId(cfgCollection.getId());
        resp.setIsDraft(cfgCollection.getIsDraft());

        return CommonResult.success(resp);

    }

    //
    @PostMapping("/update")
    public CommonResult<UpdateConfigResp> update(@RequestBody UpdateConfigReq req, @RequestParam("token") String token) {
        log.info("Token: " + token);
        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("Token失效，请重新登录！");
        }

        if (!cfgPermissionService.hasPermission(token, req.getCollectionId())){
            return CommonResult.failed("您没有该记录的操作权限");
        }

        if (cfgCollectionService.isOnline(req.getCollectionId())){
            return CommonResult.failed("当前为线上版本，不能修改！");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);


        //每次更新都会置为草稿版本，如果是线上版本，则生成新的草稿版本，；如果是草稿版本，则不变
        int collectionId = cfgCollectionService.setToDraft(req.getCollectionId(), TokenUtil.getUsernameFromToken(token));
        boolean done = cfgConfigService.update(req.getCollectionId(), collectionId, req.getId(), req.getCfgName(), req.getCfgKey(), req.getCfgValue(), req.getUsername());
        opLog.insert(Const.LOG_OPTYPE_CONFIG, "更新配置", "", req.toString(), info.getUsername(), info.getAccountId());

        return CommonResult.success(new UpdateConfigResp(collectionId));
    }

    @PostMapping("/create")
    public CommonResult<CfgConfig> add(@RequestBody AddConfigReq req, @RequestParam("token") String token) {
        log.info("Token: " + token);
        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("Token失效，请重新登录！");
        }

        if (!cfgPermissionService.hasPermission(token, req.getCollectionId())){
            return CommonResult.failed("您没有该记录的操作权限");
        }

        if (cfgCollectionService.isOnline(req.getCollectionId())){
            return CommonResult.failed("当前为线上版本，不能修改！");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);

        //每次更新都会置为草稿版本，如果是线上版本，则生成新的草稿版本，；如果是草稿版本，则不变
        int collectionId = cfgCollectionService.setToDraft(req.getCollectionId(), TokenUtil.getUsernameFromToken(token));
        CfgConfig cfgConfig = cfgConfigService.add(req.getCollectionId(), collectionId, req.getConfigName(), req.getConfigKey(), req.getConfigValue(), TokenUtil.getUsernameFromToken(token));

        opLog.insert(Const.LOG_OPTYPE_CONFIG, "添加配置", "", req.toString(), info.getUsername(), info.getAccountId());

        return CommonResult.success(cfgConfig);
    }

    @DeleteMapping("/delete")
    public CommonResult<CfgConfig> delete(@RequestBody DeleteConfigReq req, @RequestParam("token") String token) {
        log.info("Token: " + token);
        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("Token失效，请重新登录！");
        }

        if (!cfgPermissionService.hasPermission(token, req.getCollectionId())){
            return CommonResult.failed("您没有该记录的操作权限");
        }

        InfoRemote info = accountRemoteService.infoFromToken(token);

        //查看是否为线上，如果线上，则不能删除
        boolean isOnline = cfgCollectionService.isOnline(req.getCollectionId());
        if (isOnline){
            return CommonResult.failed("当前为线上版本，不能删除！");
        }

        int status = cfgConfigService.delete(req.getConfigId(), TokenUtil.getUsernameFromToken(token));
        if (status <= 0){
            return CommonResult.failed("删除失败");
        }

        opLog.insert(Const.LOG_OPTYPE_CONFIG, "删除配置", "", req.toString(), info.getUsername(), info.getAccountId());

        return CommonResult.success(null);
    }
}