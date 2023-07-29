package org.dromara.app.api.domain;

import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Data
public class RemoteAppInfo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * AppId
     */
    private Long appId;

    /**
     * 编码
     */
    private String appCode;

    /**
     * 名称
     */
    private String appName;

    private String isInternal;

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
     * 状态
     */
    private String status;

    /**
     * 扩展信息
     */
    private List<Object> extend;

    /**
     * 描述
     */
    private String description;

}
