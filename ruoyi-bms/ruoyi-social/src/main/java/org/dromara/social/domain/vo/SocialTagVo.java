package org.dromara.social.domain.vo;

import org.dromara.social.domain.SocialTag;
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
     * 接入App标识
     */
    @ExcelProperty(value = "接入App标识")
    private String appId;
    private String appName;

    /**
     * 对应主题
     */
    @ExcelProperty(value = "对应主题", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private Long subjectId;
    private String subjectName;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
