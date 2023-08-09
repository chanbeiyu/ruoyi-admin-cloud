package org.dromara.basal.platform.domain.member.vo;

import java.util.Date;

import org.dromara.basal.platform.domain.member.MemberCoins;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.basal.platform.translation.PlatformTranslation;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.translation.annotation.Translation;

import java.io.Serial;
import java.io.Serializable;


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
    private Integer status;

}
