package org.dromara.basal.platform.domain.member.vo;

import org.dromara.basal.platform.domain.member.MemberPointsRecord;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.basal.platform.translation.PlatformTranslation;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 会员积分记录视图对象 member_points_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberPointsRecord.class)
public class MemberPointsRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @ExcelProperty(value = "积分id")
    private Long recordId;

    /**
     * 应用名称
     */
    @ExcelProperty(value = "应用名称")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = PlatformTranslation.key, mapper = "appId", other = PlatformTranslation.Other.APP)
    private String appName;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    @ExcelProperty(value = "成员名称")
    @Translation(type = PlatformTranslation.key, mapper = "memberId", other = PlatformTranslation.Other.MEMBER_INFO)
    private String memberName;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "成员类型")
    private Long memberTypeId;

    @ExcelProperty(value = "成员类型")
    @Translation(type = PlatformTranslation.key, mapper = "memberTypeId", other = PlatformTranslation.Other.MEMBER_TYPE)
    private String memberTypeName;

    /**
     * 操作类型
     */
    @ExcelProperty(value = "操作类型")
    private String actionCode;

    /**
     * 上次积分
     */
    @ExcelProperty(value = "上次积分")
    private Long beforPoints;

    /**
     * 获取积分
     */
    @ExcelProperty(value = "获取积分")
    private Long points;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expiredDate;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Long status;

    /**
     * 获取积分说明
     */
    @ExcelProperty(value = "获取积分说明")
    private String description;

}
