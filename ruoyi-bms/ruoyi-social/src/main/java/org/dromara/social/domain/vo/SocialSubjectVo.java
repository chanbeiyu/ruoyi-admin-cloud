package org.dromara.social.domain.vo;

import org.dromara.social.domain.SocialSubject;
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
 * 内容主题视图对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialSubject.class)
public class SocialSubjectVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long subjectId;

    /**
     * 接入App标识
     */
    @ExcelProperty(value = "接入App标识")
    private String appId;

    /**
     * 主题编码
     */
    @ExcelProperty(value = "主题编码")
    private String socialCode;

    /**
     * 主题名称
     */
    @ExcelProperty(value = "主题名称")
    private String socialName;

    /**
     * 主题描述
     */
    @ExcelProperty(value = "主题描述")
    private String describe;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
