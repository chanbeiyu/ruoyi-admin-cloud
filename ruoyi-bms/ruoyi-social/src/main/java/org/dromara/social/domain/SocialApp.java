package org.dromara.social.domain;

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
@TableName("social_app")
public class SocialApp extends TenantEntity {

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
     * AccessKeyId
     */
    private String accessKeyId;

    /**
     * SecretAccessKey
     */
    private String secretAccessKey;

    /**
     * 签名算法
     */
    private String algorithm;

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
    private String describe;

    /**
     * 备注
     */
    private String remark;


}
