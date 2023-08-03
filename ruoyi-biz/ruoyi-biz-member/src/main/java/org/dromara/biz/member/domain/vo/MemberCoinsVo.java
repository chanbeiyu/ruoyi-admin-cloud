package org.dromara.biz.member.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.biz.member.domain.MemberCoins;
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
 * 代币信息视图对象 member_coins
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberCoins.class)
public class MemberCoinsVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
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
     * 代币信息0点数1时常2天数
     */
    @ExcelProperty(value = "代币信息0点数1时常2天数")
    private Long coinsType;

    /**
     * 剩余点数/时常/天数
     */
    @ExcelProperty(value = "剩余点数/时常/天数")
    private Long lastCoins;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date expiredDate;

    /**
     * 级别说明
     */
    @ExcelProperty(value = "级别说明")
    private String description;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
