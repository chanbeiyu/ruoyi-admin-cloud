package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidTag;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 标签信息业务对象 alkaid_tag
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidTag.class, reverseConvertGenerate = false)
public class AlkaidTagBo extends BaseEntity {

    /**
     * 标签id
     */
    @NotNull(message = "标签id不能为空", groups = { EditGroup.class })
    private Long tagId;

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
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
