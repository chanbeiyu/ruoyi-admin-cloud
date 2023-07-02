package org.dromara.platform.domain;

import jakarta.validation.constraints.NotBlank;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 应用信息对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_info")
public class AppInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * AppId
     */
    @TableId(value = "app_id")
    private Long appId;

    /**
     * 编码
     */
    private String appCode;

    /**
     * 名称
     */
    private String appName;

    /**
     * 是否为内部项目
     */
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
