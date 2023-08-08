package org.dromara.biz.member.domain.vo;

import org.dromara.biz.member.domain.MemberPoints;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.biz.member.translation.MemberTranslation;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;


/**
 * 会员积分视图对象 member_points
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberPoints.class)
public class MemberPointsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @ExcelProperty(value = "积分id")
    private Long id;

    /**
     * 应用名称
     */
    @ExcelProperty(value = "应用名称")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = MemberTranslation.key, mapper = "appId", other = MemberTranslation.Other.APP)
    private String appName;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = MemberTranslation.key, mapper = "memberId", other = MemberTranslation.Other.MEMBER_INFO)
    private String memberName;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "成员类型")
    private Long memberTypeId;

    /**
     * 会员类别名称
     */
    @ExcelProperty(value = "成员名称")
    @Translation(type = MemberTranslation.key, mapper = "memberTypeId", other = MemberTranslation.Other.MEMBER_TYPE)
    private String memberTypeName;

    /**
     * 总积分
     */
    @ExcelProperty(value = "总积分")
    private Long totalPoints;

    /**
     * 最终会员等级
     */
    @ExcelProperty(value = "最终会员等级")
    private Long lastLevel;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Integer status;

    /**
     * 获取积分说明
     */
    @ExcelProperty(value = "获取积分说明")
    private String description;

}
