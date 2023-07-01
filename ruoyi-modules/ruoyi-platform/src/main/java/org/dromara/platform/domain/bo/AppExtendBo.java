package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.AppExtend;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 应用扩展信息业务对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppExtend.class, reverseConvertGenerate = false)
public class AppExtendBo extends BaseEntity {

    /**
     * 组件id
     */
    @NotNull(message = "组件id不能为空", groups = { EditGroup.class })
    private Long extendId;

    /**
     * AppId
     */
    @NotNull(message = "AppId不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;

    /**
     * 联系微信
     */
    @NotBlank(message = "联系微信不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contactWechat;

    /**
     * 联系方式
     */
    @NotBlank(message = "联系方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contactPhone;

    /**
     * 联系邮箱
     */
    @NotBlank(message = "联系邮箱不能为空", groups = { AddGroup.class, EditGroup.class })
    private String contactEmail;

    /**
     * 服务协议
     */
    @NotBlank(message = "服务协议不能为空", groups = { AddGroup.class, EditGroup.class })
    private String serviceAgreement;

    /**
     * 隐私条款
     */
    @NotBlank(message = "隐私条款不能为空", groups = { AddGroup.class, EditGroup.class })
    private String privacyPolicy;

    /**
     * 行为规范
     */
    @NotBlank(message = "行为规范不能为空", groups = { AddGroup.class, EditGroup.class })
    private String behaviourNorm;

    /**
     * 个人信息收集清单
     */
    @NotBlank(message = "个人信息收集清单不能为空", groups = { AddGroup.class, EditGroup.class })
    private String personalInfoChecklist;

    /**
     * 备注
     */
    private String remark;


}
