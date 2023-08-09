package org.dromara.basal.platform.domain.social.vo;

import org.dromara.basal.platform.translation.PlatformTranslation;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.basal.platform.domain.social.SocialTag;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 标签信息视图对象 social_tag
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialTag.class)
public class SocialTagVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @ExcelProperty(value = "标签id")
    private Long tagId;

    /**
     * 标签编码
     */
    @ExcelProperty(value = "标签编码")
    private String tagCode;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String tagName;

    /**
     * 标签类型
     */
    @ExcelProperty(value = "标签类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private String tagType;

    /**
     * 应用名称
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 所属主题
     */
    @ExcelProperty(value = "所属主题", converter = ExcelDictConvert.class)
    private Long subjectId;

    @ExcelProperty(value = "所属主题")
    @Translation(type = PlatformTranslation.key, mapper = "subjectId", other = PlatformTranslation.Other.SOCIAL_SUBJECT)
    private String subjectName;

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
