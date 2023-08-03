package org.dromara.biz.member.domain.vo;

import org.dromara.biz.member.domain.MemberAction;
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
 * 会员积分视图对象 member_action
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberAction.class)
public class MemberActionVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @ExcelProperty(value = "积分id")
    private Long actionId;

    /**
     * 接入App标识
     */
    @ExcelProperty(value = "接入App标识")
    private Long appId;

    /**
     * 会员id
     */
    @ExcelProperty(value = "会员id")
    private Long memberId;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "会员类别")
    private Long memberTypeId;

    /**
     * 动作编号
     */
    @ExcelProperty(value = "动作编号")
    private String actionCode;

    /**
     * 动作名称
     */
    @ExcelProperty(value = "动作名称")
    private String actionName;

    /**
     * 积分点
     */
    @ExcelProperty(value = "积分点")
    private Long points;

    /**
     * 代币点
     */
    @ExcelProperty(value = "代币点")
    private Long conis;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private Long status;

    /**
     * 说明
     */
    @ExcelProperty(value = "说明")
    private String description;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
