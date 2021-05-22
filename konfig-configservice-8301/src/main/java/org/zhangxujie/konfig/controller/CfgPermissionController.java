/**
 * FileName: CfgPermissionController
 * Author:   jason
 * Date:     2021/5/21 21:15
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/cfg_permission")
@Api(tags = "CfgPermissionController", description = "配置权限")
@CrossOrigin
public class CfgPermissionController {

    //TODO：申请配置权限、通过、拒绝（前端需要添加页面展示权限管理）
}
