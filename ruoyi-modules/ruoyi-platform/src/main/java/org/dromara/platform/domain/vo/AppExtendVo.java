package org.dromara.platform.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.constant.SocialTransConstant;
import org.dromara.platform.domain.AppExtend;
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
 * 应用扩展信息视图对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AppExtend.class)
public class AppExtendVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 组件id
     */
    @ExcelProperty(value = "组件id")
    private Long extendId;

    /**
     * AppId
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = SocialTransConstant.SOCIAL_ID_TO_NAME, mapper = "appId", other = SocialTransConstant.Other.APP)
    private String appName;

    /**
     * 联系微信
     */
    @ExcelProperty(value = "联系微信")
    private String contactWechat;

    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式")
    private String contactPhone;

    /**
     * 联系邮箱
     */
    @ExcelProperty(value = "联系邮箱")
    private String contactEmail;

    /**
     * 服务协议
     */
    @ExcelProperty(value = "服务协议")
    private String serviceAgreement;

    /**
     * 隐私条款
     */
    @ExcelProperty(value = "隐私条款")
    private String privacyPolicy;

    /**
     * 行为规范
     */
    @ExcelProperty(value = "行为规范")
    private String behaviourNorm;

    /**
     * 个人信息收集清单
     */
    @ExcelProperty(value = "个人信息收集清单")
    private String personalInfoChecklist;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
