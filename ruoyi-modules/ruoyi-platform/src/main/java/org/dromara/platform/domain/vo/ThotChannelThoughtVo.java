package org.dromara.platform.domain.vo;

import org.dromara.platform.domain.ThotChannelThought;
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
 * 思绪专题关联信息视图对象 thot_channel_thought
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotChannelThought.class)
public class ThotChannelThoughtVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 频道id
     */
    @ExcelProperty(value = "频道id")
    private Long channelId;

    /**
     * 思绪ID
     */
    @ExcelProperty(value = "思绪ID")
    private Long thoughtId;


}
