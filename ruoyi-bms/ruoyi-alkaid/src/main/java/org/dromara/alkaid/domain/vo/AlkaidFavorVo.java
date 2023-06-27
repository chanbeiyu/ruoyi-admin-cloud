package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.AlkaidFavor;
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
 * 图集收藏信息视图对象 alkaid_favor
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidFavor.class)
public class AlkaidFavorVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 组件id
     */
    @ExcelProperty(value = "组件id")
    private Long favorId;

    /**
     * 图集id
     */
    @ExcelProperty(value = "图集id")
    private Long albumId;

    /**
     * 创建用户id
     */
    @ExcelProperty(value = "创建用户id")
    private Long favorUserId;

    /**
     * 图集用户id
     */
    @ExcelProperty(value = "图集用户id")
    private Long albumUserId;

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
