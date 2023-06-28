package org.dromara.social.domain.bo;

import org.dromara.social.domain.SocialTag;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 标签信息业务对象 social_tag
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialTag.class, reverseConvertGenerate = false)
public class SocialTagBo extends BaseEntity {

    /**
     * 标签id
     */
    @NotNull(message = "标签id不能为空", groups = { EditGroup.class })
    private Long tagId;

    /**
     * 标签编码
     */
    @NotBlank(message = "标签编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tagCode;

    /**
     * 标签名称
     */
    @NotBlank(message = "标签名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tagName;

    /**
     * 标签类型
     */
    @NotBlank(message = "标签类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tagType;

    /**
     * 接入App标识
     */
    @NotBlank(message = "接入App标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 对应主题
     */
    @NotNull(message = "对应主题不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long subjectId;

}
