package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidAlbum;
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
 * 图集信息业务对象 alkaid_album
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidAlbum.class, reverseConvertGenerate = false)
public class AlkaidAlbumBo extends BaseEntity {

    /**
     * 集id
     */
    @NotNull(message = "集id不能为空", groups = { EditGroup.class })
    private Long albumId;

    /**
     * 创建用户id
     */
    @NotNull(message = "创建用户id不能为空", groups = { AddGroup.class })
    private Long userId;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String title;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String describe;

    /**
     * 分类
     */
    @NotNull(message = "分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long category;

    /**
     * 标签,英文逗号分隔
     */
    @NotBlank(message = "标签,英文逗号分隔不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tags;

    /**
     * 默认封面
     */
    @NotBlank(message = "默认封面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String coverImg;

    /**
     * 收藏数
     */
    @NotNull(message = "收藏数不能为空", groups = { AddGroup.class })
    private Long favorNum;

    /**
     * 点赞数
     */
    @NotNull(message = "点赞数不能为空", groups = { AddGroup.class })
    private Long likeNum;

    /**
     * 是否置顶Y是N否
     */
    @NotBlank(message = "是否置顶Y是N否不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isTop;

    /**
     * 状态:0草稿1正常2删除
     */
    private Long status;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 删除时间
     */
    @NotNull(message = "删除时间不能为空", groups = { AddGroup.class })
    private Date deleteTime;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
