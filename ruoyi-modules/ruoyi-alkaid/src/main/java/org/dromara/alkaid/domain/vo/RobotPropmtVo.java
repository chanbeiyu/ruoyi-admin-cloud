package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.RobotPropmt;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;


/**
 * Ai Propmt视图对象 robot_propmt
 *
 * @author beiyu
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = RobotPropmt.class)
public class RobotPropmtVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 主题
     */
    @ExcelProperty(value = "主题")
    private String subject;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 内容
     */
    @ExcelProperty(value = "内容")
    private String content;

    /**
     * 平台
     */
    @ExcelProperty(value = "平台", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_ai_platform")
    private Integer platform;

    /**
     * 来源
     */
    @ExcelProperty(value = "来源", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_propmt_source")
    private Integer source;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
