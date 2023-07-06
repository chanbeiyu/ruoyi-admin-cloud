package org.dromara.platform.domain.vo;

import org.dromara.platform.domain.ThotAlbumThought;
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
 * 思集信息视图对象 thot_album_thought
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotAlbumThought.class)
public class ThotAlbumThoughtVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思集ID
     */
    @ExcelProperty(value = "思集ID")
    private Long albumId;

    /**
     * 思绪ID
     */
    @ExcelProperty(value = "思绪ID")
    private Long thoughtId;

    /**
     * 是否为封面
     */
    @ExcelProperty(value = "是否为封面")
    private Integer isCover;

    /**
     * 加入时间
     */
    @ExcelProperty(value = "加入时间")
    private Date createTime;


}
