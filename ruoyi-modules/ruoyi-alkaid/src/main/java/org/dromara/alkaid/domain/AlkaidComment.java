package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 图集评论信息对象 alkaid_comment
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_comment")
public class AlkaidComment implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "comment_id")
    private Long commentId;

    /**
     * 父评论(回复)id
     */
    private Long commentPid;

    /**
     * 评论用户id
     */
    private Long commentUserId;

    /**
     * 评论内容
     */
    private String commentContent;

    /**
     * 集id
     */
    private Long albumId;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * 状态
     */
    private Long status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 删除时间
     */
    private Date deleteTime;



}
