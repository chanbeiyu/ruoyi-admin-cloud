package org.dromara.alkaid.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;

/**
 * 应用信息对象 app_info
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_info")
public class AppInfo extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * App 编码
     */
    @TableId(value = "code")
    private String code;

    /**
     * App 名称
     */
    private String name;

    /**
     * App 类型
     */
    private Integer type;

    /**
     * 口号
     */
    private String slogan;

    /**
     * Logo
     */
    private String logo;

    /**
     * App 介绍
     */
    private String describes;

    /**
     * 网站地址
     */
    private String websiteUrl;

    /**
     * iOS下载地址
     */
    private String iosUrl;

    /**
     * Android下载地址
     */
    private String androidUrl;

    /**
     * 备注
     */
    private String remark;


}
