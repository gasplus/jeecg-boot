package org.jeecg.modules.message.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

/**
 * @Author: xhj
 * @Date: 2020-02-03 17:34
 * @Version: 1.0
 * @Description:
 **/
@Data
public class SysMessageDto implements Serializable {
    /**推送内容*/
    private java.lang.String esContent;

    /**消息标题*/
    private java.lang.String esTitle;

    /**推送时间*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private java.util.Date esSendTime;
}
