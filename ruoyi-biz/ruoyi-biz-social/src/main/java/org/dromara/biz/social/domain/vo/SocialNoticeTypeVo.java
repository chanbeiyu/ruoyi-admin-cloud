package org.dromara.biz.social.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.biz.social.domain.SocialNoticeType;
import org.dromara.biz.social.translation.SocialTranslation;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 信息通知类型视图对象 social_notice_type
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialNoticeType.class)
public class SocialNoticeTypeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 通知类型id
     */
    @ExcelProperty(value = "通知类型id")
    private Long noticeTypeId;

    /**
     * 接入App标识
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = SocialTranslation.key, mapper = "appId", other = SocialTranslation.Other.APP)
    private String appName;

    /**
     * 通知类型编码
     */
    @ExcelProperty(value = "通知类型编码")
    private String noticeTypeCode;

    /**
     * 通知类型名称
     */
    @ExcelProperty(value = "通知类型名称")
    private String noticeTypeName;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String status;

    /**
     * 主题描述
     */
    @ExcelProperty(value = "主题描述")
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
