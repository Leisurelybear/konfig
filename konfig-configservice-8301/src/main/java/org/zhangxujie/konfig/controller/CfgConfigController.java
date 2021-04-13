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
import org.zhangxujie.konfig.api.CommonResult;
import org.zhangxujie.konfig.dto.GetCfgConfigReq;
import org.zhangxujie.konfig.dto.GetCfgConfigResp;
import org.zhangxujie.konfig.dto.UpdateConfigReq;
import org.zhangxujie.konfig.model.CfgConfig;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgConfigService;

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

    //查询对应collection中的配置信息
    @PostMapping("/list")
    public CommonResult<GetCfgConfigResp> getConfigs(@RequestBody GetCfgConfigReq req, @RequestParam("token") String token) {

        List<CfgConfig> confs = cfgConfigService.query(req.getCollectionIds(), req.getNameLike(), req.getKeyLike(), req.getSort());

        GetCfgConfigResp resp = new GetCfgConfigResp();
        resp.setConfs(confs);
        resp.setNum(confs.size());

        return CommonResult.success(resp);

//        CommonResult<GetPermResp> perms = restTemplate.getForObject(USER_INVOKE_URL + "/perm/get/{username}", CommonResult.class, username);
//        if (perms == null){
//            return CommonResult.failed();
//        }
//        System.out.println(perms);
//        return CommonResult.success(perms);


//        String authToken = RequestHolder.getHeaderAuthorization();
//        if (!AdminUtil.checkUser(authToken, username)){
//            return CommonResult.unauthorized(null);
//        }

    }

    //TODO:再req中获取到config collection的名称，以及实现一个通过token查询用户名的通用功能
//    public CommonResult<GetCfgConfigResp> update(@RequestBody UpdateConfigReq req, @RequestParam("token") String token) {
//
//        //每次更新都会置为草稿版本，如果是线上版本，则生成新的草稿版本，；如果是草稿版本，则不变
//        int collectionId = cfgCollectionService.setToDraft(req.getCollectionId());
//
//        cfgConfigService.update();
//
//
//
//    }
}