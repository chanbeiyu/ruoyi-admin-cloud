package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.SpeechBilling;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 语音计费业务对象 speech_billing
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SpeechBilling.class, reverseConvertGenerate = false)
public class SpeechBillingBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;

    /**
     * 应用编号
     */
    @NotBlank(message = "应用编号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String appCode;

    /**
     * 剩余字符
     */
    @NotNull(message = "剩余字符不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long totalChar;

    /**
     * 剩余时常
     */
    @NotNull(message = "剩余时常不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long totalTime;

    /**
     * 剩余次数
     */
    @NotNull(message = "剩余次数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long totalTimes;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;


}
