package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 活动信息对象 alkaid_campaign
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_campaign")
public class AlkaidCampaign implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 消息ID
     */
    @TableId(value = "campaign_id")
    private Long campaignId;

    /**
     * 活动标题
     */
    private String campaignTitle;

    /**
     * 活动类型
     */
    private Integer campaignType;

    /**
     * 活动说明
     */
    private String campaignDescribe;

    /**
     * 活动图标
     */
    private String campaignIcoImg;

    /**
     * 活动封面
     */
    private String campaignCoverImg;

    /**
     * 活动地址
     */
    private String campaignUrl;

    /**
     * 活动状态
     */
    private Integer status;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 备注
     */
    private String remark;


}
