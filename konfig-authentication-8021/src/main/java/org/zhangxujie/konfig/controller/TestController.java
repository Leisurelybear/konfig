/**
 * FileName: LoginController
 * Author:   jason
 * Date:     2021/3/1 18:06
 * Description:
 */
package org.zhangxujie.konfig.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhangxujie.konfig.api.CommonResult;

@RestController
@RequestMapping("/default")
public class TestController {

    String str = "cfg:U-1:C-1";

    @GetMapping("test")
    @PreAuthorize("hasAuthority('cfg:U-1:C-1')")
    public CommonResult hello(){

        return new CommonResult(200, "success", "{}");
    }

    @PostMapping("/upload")
    public CommonResult upload(@RequestParam("file") MultipartFile multipartFile){
        String filePath="";
        if(!multipartFile.isEmpty()){
            System.out.println(multipartFile.getOriginalFilename());
        }
        return CommonResult.success("OK");
    }
}
