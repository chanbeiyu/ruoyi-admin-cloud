package org.dromara.platform.domain.vo.social;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.basal.social.domain.SocialNotice;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.translation.PlatformTranslation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 信息通知视图对象 social_notice
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialNotice.class)
public class SocialNoticeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @ExcelProperty(value = "消息ID")
    private Long noticeId;

    /**
     * 应用名称
     */
    private String appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long memberId;

    /**
     * 触发通知用户id
     */
    @ExcelProperty(value = "触发通知用户id")
    private Long triggerMemberId;

    /**
     * 触发通知内容id
     */
    @ExcelProperty(value = "触发通知内容id")
    private String triggerId;

    /**
     * 通知内容
     */
    @ExcelProperty(value = "通知内容")
    private String triggerContent;

    /**
     * 通知类型
     */
    @ExcelProperty(value = "通知类型")
    private String typeCode;

    /**
     * 通知状态（0发送失败1已发送2已阅读3删除）
     */
    @ExcelProperty(value = "通知状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_user_sex")
    private Integer status;

    /**
     * 发送时间
     */
    @ExcelProperty(value = "发送时间")
    private Date sendTime;

    /**
     * 阅读时间
     */
    @ExcelProperty(value = "阅读时间")
    private Date readTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
