package org.dromara.social.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.social.constant.SocialTransConstant;
import org.dromara.social.domain.SocialLike;
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
 * 点赞信息视图对象 social_like
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialLike.class)
public class SocialLikeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long likeId;

    /**
     * 接入App标识
     */
    private String appId;

    @ExcelProperty(value = "App名称")
    @Translation(type = SocialTransConstant.SOCIAL_ID_TO_NAME, other = SocialTransConstant.Other.APP)
    private String appName;

    /**
     * 点赞用户id
     */
    @ExcelProperty(value = "点赞用户id")
    private Long memberId;

    /**
     * 被点赞用户id
     */
    @ExcelProperty(value = "被点赞用户id")
    private Long toMemberId;

    /**
     * 对应主题
     */
    @ExcelProperty(value = "对应主题")
    private Long subjectId;

    /**
     * 目标id
     */
    @ExcelProperty(value = "目标id")
    private String targetId;

    /**
     * 目标内容
     */
    @ExcelProperty(value = "目标内容")
    private String targetContent;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
