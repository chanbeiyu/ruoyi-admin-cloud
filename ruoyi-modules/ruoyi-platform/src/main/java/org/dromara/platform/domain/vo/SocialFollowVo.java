package org.dromara.platform.domain.vo;

import java.util.Date;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.constant.SocialTransConstant;
import org.dromara.platform.domain.SocialFollow;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;


/**
 * 关注信息视图对象 social_follow
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SocialFollow.class)
public class SocialFollowVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long followId;

    /**
     * 接入App标识
     */
    private String appId;

    @ExcelProperty(value = "App名称")
    @Translation(type = SocialTransConstant.SOCIAL_ID_TO_NAME, other = SocialTransConstant.Other.APP)
    private String appName;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long memberId;

    /**
     * 关注用户id
     */
    @ExcelProperty(value = "关注用户id")
    private Long toMemberId;

    /**
     * 关注状态0关注1互相关注2取消关注
     */
    @ExcelProperty(value = "关注状态0关注1互相关注2取消关注", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private Integer status;

    /**
     * 取消关注时间
     */
    @ExcelProperty(value = "取消关注时间")
    private Date unfollowTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
