/**
 * FileName: GroupController
 * Author:   jason
 * Date:     2021/5/25 13:47
 * Description:
 */
package org.zhangxujie.konfig.controller;

import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.model.Group;
import org.zhangxujie.konfig.service.GroupService;
import org.zhangxujie.konfig.util.TokenUtil;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/group")
@CrossOrigin
public class GroupController {

    @Resource
    private GroupService groupService;

    @PostMapping("/list_by_name")
    public CommonResult listByName(@RequestBody String name, @RequestParam("token") String token){
        if (!TokenUtil.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }

        List<Group> groupList = groupService.listByName(name);

        return CommonResult.success(groupList);
    }

}
