package org.dromara.biz.social.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.biz.social.domain.SocialLike;
import org.dromara.biz.social.translation.SocialTranslation;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;


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

    @ExcelProperty(value = "应用名称")
    @Translation(type = SocialTranslation.key, other = SocialTranslation.Other.APP)
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
     * 所属主题
     */
    @ExcelProperty(value = "所属主题")
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
