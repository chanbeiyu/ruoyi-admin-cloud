package org.dromara.biz.member.domain.bo;

import org.dromara.biz.member.domain.MemberPoints;
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
 * 会员积分业务对象 member_points
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberPoints.class, reverseConvertGenerate = false)
public class MemberPointsBo extends BaseEntity {

    /**
     * 积分id
     */
    @NotNull(message = "积分id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 接入App标识
     */
    @NotNull(message = "接入App标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;

    /**
     * 成员id
     */
    @NotNull(message = "成员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberId;

    /**
     * 会员类别
     */
    @NotNull(message = "会员类别不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long memberTypeId;

    /**
     * 总积分
     */
    @NotNull(message = "总积分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long totalPoints;

    /**
     * 最终会员等级
     */
    @NotNull(message = "最终会员等级不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long lastLevel;

    /**
     * 过期时间
     */
    @NotNull(message = "过期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date expiredDate;

    /**
     * 状态
     */
    @NotNull(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long status;

    /**
     * 获取积分说明
     */
    @NotBlank(message = "获取积分说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String description;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
