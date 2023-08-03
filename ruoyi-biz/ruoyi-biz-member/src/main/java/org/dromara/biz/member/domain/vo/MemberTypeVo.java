package org.dromara.biz.member.domain.vo;

import org.dromara.biz.member.domain.MemberType;
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
 * 会员类型信息视图对象 member_type
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberType.class)
public class MemberTypeVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 级别id
     */
    @ExcelProperty(value = "级别id")
    private Long typeId;

    /**
     * 接入App标识
     */
    @ExcelProperty(value = "接入App标识")
    private Long appId;

    /**
     * 会员类型编码
     */
    @ExcelProperty(value = "会员类型编码")
    private String typeCode;

    /**
     * 会员类型名称
     */
    @ExcelProperty(value = "会员类型名称")
    private String typeName;

    /**
     * 级别编码
     */
    @ExcelProperty(value = "级别编码")
    private String pointsCode;

    /**
     * 级别名称
     */
    @ExcelProperty(value = "级别名称")
    private String pointsName;

    /**
     * 最大积分
     */
    @ExcelProperty(value = "最大积分")
    private Long maxPoints;

    /**
     * 默认类型
     */
    @ExcelProperty(value = "默认类型")
    private String isDefault;

    /**
     * 会员类型说明
     */
    @ExcelProperty(value = "会员类型说明")
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
