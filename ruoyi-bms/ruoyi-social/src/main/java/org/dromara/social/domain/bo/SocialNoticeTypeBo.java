package org.dromara.social.domain.bo;

import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.social.constant.SocialTransConstant;
import org.dromara.social.domain.SocialNoticeType;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 信息通知类型业务对象 social_notice_type
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SocialNoticeType.class, reverseConvertGenerate = false)
public class SocialNoticeTypeBo extends BaseEntity {

    /**
     * 通知类型id
     */
    @NotNull(message = "通知类型id不能为空", groups = { EditGroup.class })
    private Long noticeTypeId;

    /**
     * 接入App标识
     */
    @NotBlank(message = "接入App标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 通知类型编码
     */
    @NotBlank(message = "通知类型编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String noticeTypeCode;

    /**
     * 通知类型名称
     */
    @NotBlank(message = "通知类型名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String noticeTypeName;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
