package org.dromara.biz.member.domain.vo;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.biz.member.domain.MemberCoinsRecord;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.biz.member.translation.MemberTranslation;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 代币记录信息视图对象 member_coins_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberCoinsRecord.class)
public class MemberCoinsRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @ExcelProperty(value = "级别id")
    private Long recordId;

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
     * 代币类型0点数1时常2天数
     */
    @ExcelProperty(value = "代币类型0点数1时常2天数")
    private Long coinsType;

    /**
     * 动作类型
     */
    @ExcelProperty(value = "动作类型")
    private String actionCode;

    /**
     * 先前代币
     */
    @ExcelProperty(value = "先前代币")
    private Long beforCoins;

    /**
     * 先前过期时间
     */
    @ExcelProperty(value = "先前过期时间")
    private Date beforExpiredDate;

    /**
     * 代币变化
     */
    @ExcelProperty(value = "代币变化")
    private Long changeCoins;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String orderNo;

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
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

}
