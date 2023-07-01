package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 应用版本信息对象 app_version
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_version")
public class AppVersion extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 版本id
     */
    @TableId(value = "version_id")
    private Long versionId;

    /**
     * AppId
     */
    private Long appId;

    /**
     * 版本名称
     */
    @Version
    private String version;

    /**
     * 内部版本号
     */
    private String buildVersion;

    /**
     * 强制升级
     */
    private Integer forced;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 备注
     */
    private String remark;


}
