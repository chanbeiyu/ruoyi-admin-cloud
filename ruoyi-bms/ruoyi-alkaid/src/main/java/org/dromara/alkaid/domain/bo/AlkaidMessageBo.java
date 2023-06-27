package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidMessage;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 用户消息业务对象 alkaid_message
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidMessage.class, reverseConvertGenerate = false)
public class AlkaidMessageBo extends BaseEntity {

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空", groups = { EditGroup.class })
    private Long messageId;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 触发消息用户id
     */
    @NotNull(message = "触发消息用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long triggerUserId;

    /**
     * 触发消息内容id，关注-用户|收藏/点赞-图集|评论/@-评论id
     */
    @NotNull(message = "触发消息内容id，关注-用户|收藏/点赞-图集|评论/@-评论id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long triggerContentId;

    /**
     * 消息标题
     */
    @NotBlank(message = "消息标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String messageTitle;

    /**
     * 消息类型
     */
    @NotNull(message = "消息类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer messageType;

    /**
     * 消息内容
     */
    @NotBlank(message = "消息内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String messageContent;

    /**
     * 消息状态
     */
    @NotNull(message = "消息状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
