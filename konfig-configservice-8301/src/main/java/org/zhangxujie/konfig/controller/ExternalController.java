/**
 * FileName: ExternalController
 * Author:   jason
 * Date:     2021/6/3 16:14
 * Description:
 */
package org.zhangxujie.konfig.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.dto.external.Konfig;
import org.zhangxujie.konfig.dto.external.KonfigCollection;
import org.zhangxujie.konfig.model.CfgCollection;
import org.zhangxujie.konfig.model.CfgConfig;
import org.zhangxujie.konfig.service.CfgCollectionService;
import org.zhangxujie.konfig.service.CfgConfigService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/sdk")
@Api(tags = "提供SDK的查询配置接口", description = "查询配置")
@CrossOrigin
public class ExternalController {

    @Resource
    private CfgCollectionService collectionService;

    @Resource
    private CfgConfigService configService;

    @GetMapping("/config/{collectionId}")
    public ResponseEntity config(@PathVariable("collectionId") Integer collectionId) {

        CfgCollection cfgCollection = collectionService.queryById(collectionId);

        List<CfgConfig> configList = configService.getByCollectionId(collectionId);

        KonfigCollection konfigCollection = new KonfigCollection();
        if (cfgCollection != null){
            konfigCollection.setId(cfgCollection.getId());
            konfigCollection.setCollectionName(cfgCollection.getcName());
        }

        List<Konfig> konfigList = new ArrayList<>();
        if (configList != null && configList.size() > 0){
            configList.forEach(c -> {
                Konfig konfig = new Konfig();
                konfig.setId(c.getId());
                konfig.setName(c.getCfgName());
                konfig.setKey(c.getCfgKey());
                konfig.setValue(c.getCfgValue());
                konfigList.add(konfig);
            });
        }

        konfigCollection.setConfigList(konfigList);
        return new ResponseEntity<>(JSONObject.toJSONString(konfigCollection), HttpStatus.OK);
    }


}
