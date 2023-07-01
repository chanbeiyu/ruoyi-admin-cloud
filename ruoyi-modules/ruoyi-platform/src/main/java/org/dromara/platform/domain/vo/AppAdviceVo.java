package org.dromara.platform.domain.vo;

import org.dromara.platform.domain.AppAdvice;
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
 * 意见反馈信息视图对象 app_advice
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AppAdvice.class)
public class AppAdviceVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 留言id
     */
    @ExcelProperty(value = "留言id")
    private Long adviceId;

    /**
     * AppId
     */
    @ExcelProperty(value = "AppId")
    private Long appId;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    /**
     * 联系方式
     */
    @ExcelProperty(value = "联系方式")
    private String contact;

    /**
     * 反馈内容
     */
    @ExcelProperty(value = "反馈内容")
    private String content;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
