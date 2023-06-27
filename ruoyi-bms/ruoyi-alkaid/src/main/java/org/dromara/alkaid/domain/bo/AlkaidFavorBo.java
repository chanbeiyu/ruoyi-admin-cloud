package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidFavor;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 图集收藏信息业务对象 alkaid_favor
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidFavor.class, reverseConvertGenerate = false)
public class AlkaidFavorBo extends BaseEntity {

    /**
     * 组件id
     */
    @NotNull(message = "组件id不能为空", groups = { EditGroup.class })
    private Long favorId;

    /**
     * 图集id
     */
    @NotNull(message = "图集id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long albumId;

    /**
     * 创建用户id
     */
    @NotNull(message = "创建用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long favorUserId;

    /**
     * 图集用户id
     */
    @NotNull(message = "图集用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long albumUserId;

    /**
     * 描述
     */
    @NotBlank(message = "描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String describe;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
