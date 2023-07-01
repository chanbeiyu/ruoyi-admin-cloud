package org.dromara.platform.domain.vo;

import org.dromara.platform.domain.ThotAlbum;
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
 * 思集信息视图对象 thot_album
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotAlbum.class)
public class ThotAlbumVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思集ID
     */
    @ExcelProperty(value = "思集ID")
    private Long albumId;

    /**
     * 接入App
     */
    @ExcelProperty(value = "接入App")
    private Long appId;

    /**
     * 思绪编号
     */
    @ExcelProperty(value = "思绪编号")
    private String albumTitle;

    /**
     * 思绪描述
     */
    @ExcelProperty(value = "思绪描述")
    private String description;

    /**
     * 活动状态（0创建1发布2结束3删除）
     */
    @ExcelProperty(value = "活动状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=创建1发布2结束3删除")
    private Integer status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
