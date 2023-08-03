package org.dromara.biz.member.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.biz.member.domain.MemberPoints;
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
     * 接入App标识
     */
    @ExcelProperty(value = "接入App标识")
    private Long appId;

    /**
     * 成员id
     */
    @ExcelProperty(value = "成员id")
    private Long memberId;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "会员类别")
    private Long memberTypeId;

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

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
