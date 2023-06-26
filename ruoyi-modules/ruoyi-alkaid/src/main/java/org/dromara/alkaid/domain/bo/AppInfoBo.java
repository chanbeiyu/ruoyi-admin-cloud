package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AppInfo;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.tenant.core.TenantEntity;

/**
 * 应用信息业务对象 app_info
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AppInfo.class, reverseConvertGenerate = false)
public class AppInfoBo extends TenantEntity {

    /**
     * 应用编码
     */
    @NotBlank(message = "应用编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String code;

    /**
     * 应用名称
     */
    @NotBlank(message = "应用名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 应用类型
     */
    @NotNull(message = "应用类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer type;

    /**
     * 口号
     */
    @NotBlank(message = "口号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String slogan;

    /**
     * Logo
     */
    @NotBlank(message = "Logo不能为空", groups = {AddGroup.class, EditGroup.class})
    private String logo;

    /**
     * 应用介绍
     */
    @NotBlank(message = "应用介绍不能为空", groups = {AddGroup.class, EditGroup.class})
    private String describes;

    /**
     * 网站地址
     */
    @NotBlank(message = "网站地址不能为空", groups = {AddGroup.class, EditGroup.class})
    private String websiteUrl;

    /**
     * iOS下载地址
     */
    @NotBlank(message = "iOS下载地址不能为空", groups = {AddGroup.class, EditGroup.class})
    private String iosUrl;

    /**
     * Android下载地址
     */
    @NotBlank(message = "Android下载地址不能为空", groups = {AddGroup.class, EditGroup.class})
    private String androidUrl;

    /**
     * 备注
     */
    private String remark;


}
