package org.dromara.biz.member.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 会员积分记录对象 member_points_record
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("member_points_record")
public class MemberPointsRecord extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 积分id
     */
    @TableId(value = "record_id")
    private Long recordId;

    /**
     * 接入App标识
     */
    private Long appId;

    /**
     * 成员id
     */
    private Long memberId;

    /**
     * 会员类别
     */
    private Long memberTypeId;

    /**
     * 操作类型
     */
    private String actionCode;

    /**
     * 上次积分
     */
    private Long beforPoints;

    /**
     * 获取积分
     */
    private Long points;

    /**
     * 状态
     */
    private Long status;

    /**
     * 获取积分说明
     */
    private String description;

    /**
     * 备注
     */
    private String remark;


}
