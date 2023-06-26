package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidComment;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 图集评论信息业务对象 alkaid_comment
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidComment.class, reverseConvertGenerate = false)
public class AlkaidCommentBo extends BaseEntity {

    /**
     * 评论id
     */
    @NotNull(message = "评论id不能为空", groups = { EditGroup.class })
    private Long commentId;

    /**
     * 父评论(回复)id
     */
    @NotNull(message = "父评论(回复)id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long commentPid;

    /**
     * 评论用户id
     */
    @NotNull(message = "评论用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long commentUserId;

    /**
     * 评论内容
     */
    @NotBlank(message = "评论内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String commentContent;

    /**
     * 集id
     */
    @NotNull(message = "集id不能为空", groups = { EditGroup.class })
    private Long albumId;

    /**
     * 点赞数
     */
    @NotNull(message = "点赞数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long likeNum;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 删除时间
     */
    @NotNull(message = "删除时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date deleteTime;


}
