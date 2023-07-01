package org.dromara.platform.domain.vo;

import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;
import org.dromara.platform.domain.AppInfo;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 应用信息视图对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AppInfo.class)
public class AppInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * AppId
     */
    @ExcelProperty(value = "AppId")
    private Long appId;

    /**
     * 编码
     */
    @ExcelProperty(value = "编码")
    private String appCode;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String appName;

    /**
     * AccessKeyId
     */
    @Sensitive(strategy = SensitiveStrategy.SECRET_KEY)
    @ExcelProperty(value = "AccessKeyId")
    private String accessKeyId;

    /**
     * SecretAccessKey
     */
    @Sensitive(strategy = SensitiveStrategy.SECRET_KEY)
    @ExcelProperty(value = "SecretAccessKey")
    private String secretAccessKey;

    /**
     * 签名加盐值
     */
    @ExcelProperty(value = "签名加盐值")
    private String salt;

    /**
     * 允许的域,支持模糊匹配
     */
    @ExcelProperty(value = "允许的域,支持模糊匹配")
    private String domains;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

}
