/**
 * FileName: CfgCollectionController
 * Author:   jason
 * Date:     2021/4/4 15:17
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.zhangxujie.konfig.api.CommonResult;
import org.zhangxujie.konfig.dto.GetCfgCollectionsReq;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.service.CfgCollectionService;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/cfg_coll")
@Api(tags = "CfgCollectionController", description = "访问配置集合")
@CrossOrigin
public class CfgCollectionController {

    public static final String USER_INVOKE_URL = "http://KONFIG-AUTHENTICATION";

//    @Resource
//    private RestTemplate restTemplate;

    @Resource
    private CfgCollectionService cfgCollectionService;


    @PostMapping("/list/{username}")
    public CommonResult getCfgCollections(@PathVariable String username, @RequestBody GetCfgCollectionsReq req) {

        List<CfgCollection> resp =  cfgCollectionService.query(req.getNameLike(), req.getSort(), req.getPageNum(), req.getNums());

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

}
