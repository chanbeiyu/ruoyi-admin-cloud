package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidImg;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 图片信息业务对象 alkaid_img
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidImg.class, reverseConvertGenerate = false)
public class AlkaidImgBo extends BaseEntity {

    /**
     * 图片id
     */
    @NotNull(message = "图片id不能为空", groups = { EditGroup.class })
    private Long imgId;

    /**
     * 创建用户id
     */
    @NotNull(message = "创建用户id不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 标签,英文逗号分隔
     */
    @NotBlank(message = "标签,英文逗号分隔不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tags;

    /**
     * 原图地址
     */
    @NotBlank(message = "原图地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imgUrl;

    /**
     * 小图地址
     */
    @NotBlank(message = "小图地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imgSUrl;

    /**
     * 大图地址
     */
    @NotBlank(message = "大图地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imgLUrl;

    /**
     * 收藏数
     */
    @NotNull(message = "收藏数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long favorNum;

    /**
     * 点赞数
     */
    @NotNull(message = "点赞数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long likeNum;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
