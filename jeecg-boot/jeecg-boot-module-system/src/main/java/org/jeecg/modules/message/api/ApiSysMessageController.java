package org.jeecg.modules.message.api;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.MemberLoginUser;
import org.jeecg.common.constant.ApiConstant;
import org.jeecg.common.system.vo.LoginUser;
import org.jeecg.modules.message.dto.SysMessageDto;
import org.jeecg.modules.message.service.ISysMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: xhj
 * @Date: 2020-02-03 17:19
 * @Version: 1.0
 * @Description:
 **/
@RestController
@RequestMapping("/api/" + ApiConstant.VERSION + "/message/sysMessage")
@Slf4j
public class ApiSysMessageController {
    @Autowired
    private ISysMessageService sysMessageService;

    @ApiOperation(value = "获取用户未读消息总数", notes = "获取用户未读消息总数")
    @GetMapping("/smsCount")
    public Result<?> getSmsCount(@ApiIgnore @MemberLoginUser LoginUser loginUser){
        int unReadSmsCount = sysMessageService.getUnReadSmsCount(loginUser.getId());
        return  Result.ok(unReadSmsCount);
    }

    @ApiOperation(value = "获取个人消息列表", notes = "获取个人消息列表")
    @GetMapping("/listSms")
    public Result<?> getListSms(@RequestParam(name = "pageNo", defaultValue = "1") Integer pageNo,
                                @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize,
                                HttpServletRequest req,@ApiIgnore @MemberLoginUser LoginUser loginUser){
        Page<SysMessageDto> page= new Page<SysMessageDto>(pageNo, pageSize);
        IPage<SysMessageDto> pageList=sysMessageService.getUserPageList(page,loginUser.getId());
        return  Result.ok(pageList);
    }



    @ApiOperation(value = "标记消息已读", notes = "标记消息已读")
    @PutMapping("/readSms")
    public Result<?> readSms(@RequestParam("id") String id,@ApiIgnore @MemberLoginUser LoginUser loginUser){
        sysMessageService.readSms(id,loginUser.getUsername());
        return Result.ok("阅读成功");
    }
}
