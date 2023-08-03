package org.dromara.biz.member.domain.bo;

import org.dromara.biz.member.domain.MemberType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 会员类型信息业务对象 member_type
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberType.class, reverseConvertGenerate = false)
public class MemberTypeBo extends BaseEntity {

    /**
     * 级别id
     */
    @NotNull(message = "级别id不能为空", groups = { EditGroup.class })
    private Long typeId;

    /**
     * 接入App标识
     */
    @NotNull(message = "接入App标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;

    /**
     * 会员类型编码
     */
    @NotBlank(message = "会员类型编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeCode;

    /**
     * 会员类型名称
     */
    @NotBlank(message = "会员类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String typeName;

    /**
     * 级别编码
     */
    @NotBlank(message = "级别编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pointsCode;

    /**
     * 级别名称
     */
    @NotBlank(message = "级别名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pointsName;

    /**
     * 最大积分
     */
    @NotNull(message = "最大积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long maxPoints;

    /**
     * 默认类型
     */
    @NotBlank(message = "默认类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isDefault;

    /**
     * 会员类型说明
     */
    @NotBlank(message = "会员类型说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
