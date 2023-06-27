package org.dromara.alkaid.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.alkaid.domain.AlkaidCampaign;
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
 * 活动信息视图对象 alkaid_campaign
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidCampaign.class)
public class AlkaidCampaignVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @ExcelProperty(value = "消息ID")
    private Long campaignId;

    /**
     * 活动标题
     */
    @ExcelProperty(value = "活动标题")
    private String campaignTitle;

    /**
     * 活动类型
     */
    @ExcelProperty(value = "活动类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_campaign_type")
    private Integer campaignType;

    /**
     * 活动说明
     */
    @ExcelProperty(value = "活动说明")
    private String campaignDescribe;

    /**
     * 活动图标
     */
    @ExcelProperty(value = "活动图标")
    private String campaignIcoImg;

    /**
     * 活动封面
     */
    @ExcelProperty(value = "活动封面")
    private String campaignCoverImg;

    /**
     * 活动地址
     */
    @ExcelProperty(value = "活动地址")
    private String campaignUrl;

    /**
     * 活动状态
     */
    @ExcelProperty(value = "活动状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_data_2status")
    private Integer status;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
