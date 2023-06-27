package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.AlkaidCampaign;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 活动信息业务对象 alkaid_campaign
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = AlkaidCampaign.class, reverseConvertGenerate = false)
public class AlkaidCampaignBo extends BaseEntity {

    /**
     * 消息ID
     */
    @NotNull(message = "消息ID不能为空", groups = { EditGroup.class })
    private Long campaignId;

    /**
     * 活动标题
     */
    @NotBlank(message = "活动标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignTitle;

    /**
     * 活动类型
     */
    @NotNull(message = "活动类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer campaignType;

    /**
     * 活动说明
     */
    @NotBlank(message = "活动说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignDescribe;

    /**
     * 活动图标
     */
    @NotBlank(message = "活动图标不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignIcoImg;

    /**
     * 活动封面
     */
    @NotBlank(message = "活动封面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignCoverImg;

    /**
     * 活动地址
     */
    @NotBlank(message = "活动地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String campaignUrl;

    /**
     * 活动状态
     */
    @NotNull(message = "活动状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date beginTime;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

    /**
     * 发布时间
     */
    @NotNull(message = "发布时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date publishTime;

    /**
     * 备注
     */
    private String remark;


}
