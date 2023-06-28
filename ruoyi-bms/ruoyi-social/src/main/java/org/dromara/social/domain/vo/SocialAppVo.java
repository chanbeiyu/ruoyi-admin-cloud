package org.dromara.social.domain.vo;

import org.dromara.social.domain.SocialApp;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 应用信息视图对象 social_app
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialApp.class)
public class SocialAppVo implements Serializable {

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
    @ExcelProperty(value = "AccessKeyId")
    private String accessKeyId;

    /**
     * SecretAccessKey
     */
    @ExcelProperty(value = "SecretAccessKey")
    private String secretAccessKey;

    /**
     * 签名算法
     */
    @ExcelProperty(value = "签名算法", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String algorithm;

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
    private String describe;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
