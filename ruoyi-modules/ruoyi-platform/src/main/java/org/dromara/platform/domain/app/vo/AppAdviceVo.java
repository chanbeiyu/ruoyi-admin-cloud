package org.dromara.platform.domain.app.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import io.github.linpeilie.annotations.AutoMappers;
import lombok.Data;
import org.dromara.biz.app.domain.AppAdvice;
import org.dromara.biz.app.translation.AppTranslation;
import org.dromara.common.translation.annotation.Translation;

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
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = AppTranslation.key, mapper = "appId", other = AppTranslation.Other.APP)
    private String appName;

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
