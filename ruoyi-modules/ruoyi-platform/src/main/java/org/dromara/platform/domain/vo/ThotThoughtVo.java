package org.dromara.platform.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.platform.constant.SocialTransConstant;
import org.dromara.platform.domain.ThotThought;
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
 * 思绪信息视图对象 thot_thought
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ThotThought.class)
public class ThotThoughtVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 思绪ID
     */
    @ExcelProperty(value = "思绪ID")
    private Long thoughtId;

    /**
     * 接入App
     */
    private Long appId;

    @ExcelProperty(value = "应用名称")
    @Translation(type = SocialTransConstant.SOCIAL_ID_TO_NAME, mapper = "appId", other = SocialTransConstant.Other.APP)
    private String appName;

    /**
     * 思绪编号
     */
    @ExcelProperty(value = "思绪编号")
    private String code;

    /**
     * 主图
     */
    @ExcelProperty(value = "主图")
    private String mainImg;

    /**
     * banner图
     */
    @ExcelProperty(value = "banner图")
    private String bannerImg;

    /**
     * 思绪标题
     */
    @ExcelProperty(value = "思绪标题")
    private String title;

    /**
     * 标题样式
     */
    @ExcelProperty(value = "标题样式")
    private Long titleStyle;

    /**
     * 思绪内容
     */
    @ExcelProperty(value = "思绪内容")
    private String content;

    /**
     * 内容样式
     */
    @ExcelProperty(value = "内容样式")
    private Long cententStyle;

    /**
     * 活动状态
     */
    @ExcelProperty(value = "活动状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "en_data_status1")
    private Integer status;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;




    @ExcelProperty(value = "思集ID")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long albumId;

    @ExcelProperty(value = "思集标题")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String albumTitle;

    @ExcelProperty(value = "是否为封面")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Integer albumIsCover;

    @ExcelProperty(value = "加入时间")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date albumCreateTime;

}
