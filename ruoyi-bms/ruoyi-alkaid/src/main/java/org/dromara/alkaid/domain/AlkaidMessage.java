package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户消息对象 alkaid_message
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_message")
public class AlkaidMessage implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "message_id")
    private Long messageId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 触发消息用户id
     */
    private Long triggerUserId;

    /**
     * 触发消息内容id，关注-用户|收藏/点赞-图集|评论/@-评论id
     */
    private Long triggerContentId;

    /**
     * 消息标题
     */
    private String messageTitle;

    /**
     * 消息类型
     */
    private Integer messageType;

    /**
     * 消息内容
     */
    private String messageContent;

    /**
     * 消息状态
     */
    private Integer status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 备注
     */
    private String remark;


}
