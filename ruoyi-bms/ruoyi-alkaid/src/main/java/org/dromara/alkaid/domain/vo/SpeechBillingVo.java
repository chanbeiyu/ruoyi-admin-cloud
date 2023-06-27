package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.SpeechBilling;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 语音计费视图对象 speech_billing
 *
 * @author beiyu
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SpeechBilling.class)
public class SpeechBillingVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 应用编号
     */
    @ExcelProperty(value = "应用编号")
    private String appCode;

    /**
     * 剩余字符
     */
    @ExcelProperty(value = "剩余字符")
    private Long totalChar;

    /**
     * 剩余时常
     */
    @ExcelProperty(value = "剩余时常")
    private Long totalTime;

    /**
     * 剩余次数
     */
    @ExcelProperty(value = "剩余次数")
    private Long totalTimes;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
