package org.jeecg.modules.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.common.exception.JeecgBootException;
import org.jeecg.common.system.base.service.impl.JeecgServiceImpl;
import org.jeecg.modules.message.dto.SysMessageDto;
import org.jeecg.modules.message.entity.SysMessage;
import org.jeecg.modules.message.mapper.SysMessageMapper;
import org.jeecg.modules.message.service.ISysMessageService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description: 消息
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
@Service
public class SysMessageServiceImpl extends JeecgServiceImpl<SysMessageMapper, SysMessage> implements ISysMessageService {
    @Resource
    private SysMessageMapper sysMessageMapper;
    @Override
    public int getUnReadSmsCount(String userId) {
        QueryWrapper<SysMessage>  queryWrapper=new QueryWrapper<>();
        queryWrapper.lambda().eq(SysMessage::getEsReceiver,userId);
        int count = super.count(queryWrapper);
        return count;
    }

    @Override
    public IPage<SysMessageDto> getUserPageList(Page<SysMessageDto> page, String userId) {
        return sysMessageMapper.getUserPageList(page,userId);
    }

    @Override
    public void readSms(String id, String userName) {
        SysMessage sysMessage = super.getById(id);
        if(sysMessage==null){
            throw new JeecgBootException("消息不存在");
        }
        if(!sysMessage.getEsReceiver().equals(userName)){
            throw new JeecgBootException("非法操作，不能阅读其他人消息");
        }
        if(sysMessage.getIsRead()!=null&&sysMessage.getIsRead()==1){
            throw new JeecgBootException("已经标记过阅读，不能重复标记");
        }
        UpdateWrapper<SysMessage> updateWrapper=new UpdateWrapper<>();
        updateWrapper.lambda().set(SysMessage::getIsRead,1).eq(SysMessage::getId,id);
        super.update(updateWrapper);
    }
}
