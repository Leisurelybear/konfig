/**
 * FileName: OpLogController
 * Author:   jason
 * Date:     2021/6/2 21:09
 * Description:
 */
package org.zhangxujie.konfig.controller;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.zhangxujie.konfig.common.CommonResult;
import org.zhangxujie.konfig.common.LogUtil;
import org.zhangxujie.konfig.dao.AccountRemoteService;
import org.zhangxujie.konfig.dto.OpLogListReq;
import org.zhangxujie.konfig.dto.OpLogListResp;
import org.zhangxujie.konfig.dto.account.InfoRemote;
import org.zhangxujie.konfig.model.OpLog;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping("/log")
@Api(tags = "oplog", description = "操作日志")
@CrossOrigin
public class OpLogController {

    @Resource
    private LogUtil opLogService;

    @Resource
    private AccountRemoteService accountRemoteService;

    @PostMapping("/list")
    public CommonResult list(@RequestBody OpLogListReq req, @RequestParam("token") String token) {
        if (!accountRemoteService.validateToken(token)) {
            return CommonResult.failed("token失效，请重新登录");
        }
        InfoRemote info = accountRemoteService.infoFromToken(token);

        List<OpLog> opLogList = opLogService.list(req.getType(), req.getPageNumber(), req.getPageSize());

        Integer count = opLogService.count(req.getType());

        OpLogListResp resp = new OpLogListResp();
        resp.setOpLogList(opLogList);
        resp.setType(req.getType());
        resp.setPageNumber(req.getPageNumber());
        resp.setPageSize(req.getPageSize());
        resp.setCount(count);

        return CommonResult.success(resp);
    }
}
