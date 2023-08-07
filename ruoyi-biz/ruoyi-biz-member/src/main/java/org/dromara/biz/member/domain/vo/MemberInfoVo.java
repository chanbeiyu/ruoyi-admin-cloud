package org.dromara.biz.member.domain.vo;

import org.dromara.biz.member.domain.MemberInfo;
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
 * 成员信息视图对象 member_info
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = MemberInfo.class)
public class MemberInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long memberId;

    /**
     * 关联用户标识
     */
    @ExcelProperty(value = "关联用户标识")
    private String unionId;

    /**
     * 应用名称
     */
    @ExcelProperty(value = "应用名称")
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = MemberTranslation.key, mapper = "appId", other = MemberTranslation.Other.APP)
    private String appName;

    /**
     * 成员类型
     */
    @ExcelProperty(value = "成员类型")
    private Long typeId;

    /**
     * 昵称
     */
    @ExcelProperty(value = "昵称")
    private String nickName;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String avatarImg;

    /**
     * 顶部背景图
     */
    @ExcelProperty(value = "顶部背景图")
    private String bannerImg;

    /**
     * 个性签名
     */
    @ExcelProperty(value = "个性签名")
    private String whatsup;

    /**
     * 状态:0正常1锁定2注销
     */
    @ExcelProperty(value = "状态")
    private String status;

}
