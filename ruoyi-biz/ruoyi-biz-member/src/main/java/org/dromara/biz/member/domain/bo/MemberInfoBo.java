package org.dromara.biz.member.domain.bo;

import org.dromara.biz.member.domain.MemberInfo;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 成员信息业务对象 member_info
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = MemberInfo.class, reverseConvertGenerate = false)
public class MemberInfoBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = { EditGroup.class })
    private Long memberId;

    /**
     * 关联用户标识
     */
    @NotBlank(message = "关联用户标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String unionId;

    /**
     * 接入App标识
     */
    @NotNull(message = "接入App标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;

    /**
     * 成员类型
     */
    @NotNull(message = "成员类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long typeId;

    /**
     * 昵称
     */
    @NotBlank(message = "昵称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String nickName;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = { AddGroup.class, EditGroup.class })
    private String avatarImg;

    /**
     * 顶部背景图
     */
    @NotBlank(message = "顶部背景图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerImg;

    /**
     * 个性签名
     */
    @NotBlank(message = "个性签名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String whatsup;

    /**
     * 状态:0正常1锁定2注销
     */
    @NotNull(message = "状态:0正常1锁定2注销不能为空", groups = { AddGroup.class, EditGroup.class })
    private Integer status;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
