/**
 * FileName: CfgCollectionController
 * Author:   jason
 * Date:     2021/4/4 15:17
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.bouncycastle.cert.ocsp.Req;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.dto.*;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.util.TokenUtil;

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


    @PostMapping("/list")
    public CommonResult getCfgCollections(@RequestParam("token") String token, @RequestBody GetCfgCollectionsReq req) {

        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("token失效，请重新登录");
        }

        List<CfgCollection> resp =  cfgCollectionService.query(req.getNameLike(), req.getSort(), req.getPageNum(), req.getNums());

        return CommonResult.success(resp);

    }


    @PostMapping("/add/{collectionName}")
    public CommonResult add(@RequestParam("token") String token, @PathVariable String collectionName) {


        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("token失效，请重新登录");
        }

        AddCollectionReq req = new AddCollectionReq(collectionName);

        String username = TokenUtil.getUsernameFromToken(token);

        AddCollectionResp resp =  cfgCollectionService.add(req, username);

        if (resp.getId() == -1){
            return CommonResult.failed("配置集名重复，请更改后提交");
        }

        return CommonResult.success(resp);

    }


    @DeleteMapping("/del/{collectionId}")
    public CommonResult delete(@RequestParam("token") String token, @PathVariable Integer collectionId) {

        if (!TokenUtil.validateToken(token)){
            return CommonResult.failed("token失效，请重新登录");
        }

        DeleteCollectionReq req = new DeleteCollectionReq(collectionId);

        String username = TokenUtil.getUsernameFromToken(token);

        DeleteCollectionResp resp =  cfgCollectionService.delete(req, username);

        if (resp.getStatus() == false){
            return CommonResult.failed("该配置为线上配置，不可删除，只能够上线替换！");
        }

        return CommonResult.success(resp);

    }
}
