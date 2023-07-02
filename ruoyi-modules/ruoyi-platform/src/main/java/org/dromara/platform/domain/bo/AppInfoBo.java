package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.AppInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 应用信息业务对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppInfo.class, reverseConvertGenerate = false)
public class AppInfoBo extends BaseEntity {

    /**
     * AppId
     */
    @NotNull(message = "AppId不能为空", groups = { EditGroup.class })
    private Long appId;

    /**
     * 编码
     */
    @NotBlank(message = "编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appCode;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appName;

    /**
     * 是否为内部项目
     */
    @NotNull(message = "请选择是否为内部项目", groups = { AddGroup.class, EditGroup.class })
    private Integer isInternal;

    /**
     * AccessKeyId
     */
    private String accessKeyId;

    /**
     * SecretAccessKey
     */
    private String secretAccessKey;

    /**
     * 签名加盐值
     */
    private String salt;

    /**
     * 允许的域,支持模糊匹配
     */
    private String domains;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remark;

}
