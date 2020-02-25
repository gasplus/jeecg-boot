package org.jeecg.modules.message.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.jeecg.common.system.base.service.JeecgService;
import org.jeecg.modules.message.dto.SysMessageDto;
import org.jeecg.modules.message.entity.SysMessage;

/**
 * @Description: 消息
 * @Author: jeecg-boot
 * @Date:  2019-04-09
 * @Version: V1.0
 */
public interface ISysMessageService extends JeecgService<SysMessage> {
    /**
     * 获取未读消息总数
     * @param userId
     * @return
     */
    int  getUnReadSmsCount(String userId);

    /**
     * 获取用户个人消息
     * @param page
     * @param userId
     * @return
     */
    IPage<SysMessageDto> getUserPageList(Page<SysMessageDto> page,String userId);

    /**
     * 标记消息已读
     * @param id
     * @param userName
     */
    void readSms(String id ,String userName);


}
