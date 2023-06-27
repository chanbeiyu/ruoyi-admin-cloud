package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.AlkaidMessage;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 用户消息视图对象 alkaid_message
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidMessage.class)
public class AlkaidMessageVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @ExcelProperty(value = "消息ID")
    private Long messageId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 触发消息用户id
     */
    @ExcelProperty(value = "触发消息用户id")
    private Long triggerUserId;

    /**
     * 触发消息内容id，关注-用户|收藏/点赞-图集|评论/@-评论id
     */
    @ExcelProperty(value = "触发消息内容id，关注-用户|收藏/点赞-图集|评论/@-评论id")
    private Long triggerContentId;

    /**
     * 消息标题
     */
    @ExcelProperty(value = "消息标题")
    private String messageTitle;

    /**
     * 消息类型
     */
    @ExcelProperty(value = "消息类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_message_type")
    private Integer messageType;

    /**
     * 消息内容
     */
    @ExcelProperty(value = "消息内容")
    private String messageContent;

    /**
     * 消息状态
     */
    @ExcelProperty(value = "消息状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_data_2status")
    private Integer status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
