package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.AlkaidTag;
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
 * 标签信息视图对象 alkaid_tag
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidTag.class)
public class AlkaidTagVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @ExcelProperty(value = "标签id")
    private Long tagId;

    /**
     * 标签名称
     */
    @ExcelProperty(value = "标签名称")
    private String tagName;

    /**
     * 标签类型
     */
    @ExcelProperty(value = "标签类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_tag_type")
    private String tagType;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
