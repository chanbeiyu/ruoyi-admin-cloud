package org.dromara.social.domain.bo;

import org.dromara.social.domain.SocialSubject;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 内容主题业务对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialSubject.class, reverseConvertGenerate = false)
public class SocialSubjectBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long subjectId;

    /**
     * 接入App标识
     */
    @NotBlank(message = "接入App标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 主题编码
     */
    @NotBlank(message = "主题编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String subjectCode;

    /**
     * 主题名称
     */
    @NotBlank(message = "主题名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String subjectName;

    /**
     * 主题描述
     */
    private String description;

}
