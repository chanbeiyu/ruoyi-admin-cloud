package org.dromara.biz.member.domain.vo;

import org.dromara.biz.member.domain.MemberLevel;
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
 * 会员级别信息视图对象 member_level
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberLevel.class)
public class MemberLevelVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @ExcelProperty(value = "级别id")
    private Long levelId;

    /**
     * 应用名称
     */
    @ExcelProperty(value = "应用名称")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = MemberTranslation.key, mapper = "appId", other = MemberTranslation.Other.APP)
    private String appName;

    /**
     * 会员类别
     */
    @ExcelProperty(value = "会员类别")
    private Long memberTypeId;

    /**
     * 会员类别名称
     */
    @ExcelProperty(value = "会员类别")
    @Translation(type = MemberTranslation.key, mapper = "memberTypeId", other = MemberTranslation.Other.MEMBER_TYPE)
    private String memberTypeName;

    /**
     * 级别编码
     */
    @ExcelProperty(value = "级别编码")
    private String levelCode;

    /**
     * 级别名称
     */
    @ExcelProperty(value = "级别名称")
    private String levelName;

    /**
     * 最小积分
     */
    @ExcelProperty(value = "最小积分")
    private Long leastPoints;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 最大积分
     */
    @ExcelProperty(value = "最大积分")
    private Long biggestPoints;

    /**
     * 级别说明
     */
    @ExcelProperty(value = "级别说明")
    private String description;

}
