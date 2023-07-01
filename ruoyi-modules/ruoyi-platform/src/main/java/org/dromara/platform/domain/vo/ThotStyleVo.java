package org.dromara.platform.domain.vo;

import org.dromara.platform.domain.ThotStyle;
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
 * 样式信息视图对象 thot_style
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotStyle.class)
public class ThotStyleVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 样式id
     */
    @ExcelProperty(value = "样式id")
    private Long styleId;

    /**
     * 接入App
     */
    @ExcelProperty(value = "接入App")
    private Long appId;

    /**
     * 样式编码
     */
    @ExcelProperty(value = "样式编码")
    private String styleCode;

    /**
     * 样式名称
     */
    @ExcelProperty(value = "样式名称")
    private String styleName;

    /**
     * 样式
     */
    @ExcelProperty(value = "样式")
    private String styleContent;

    /**
     * 样式描述
     */
    @ExcelProperty(value = "样式描述")
    private String description;


}
