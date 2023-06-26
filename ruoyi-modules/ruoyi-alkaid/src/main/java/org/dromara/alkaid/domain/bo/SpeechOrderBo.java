package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.SpeechOrder;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 语音订单业务对象 speech_order
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SpeechOrder.class, reverseConvertGenerate = false)
public class SpeechOrderBo extends BaseEntity {

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
     * 应用编码
     */
    @NotBlank(message = "应用编码不能为空", groups = {AddGroup.class, EditGroup.class})
    private String appCode;

    /**
     * 客户端类型
     */
    @NotNull(message = "客户端类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer client;

    /**
     * 平台
     */
    @NotNull(message = "平台不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer platform;

    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String orderId;

    /**
     * 订单金额
     */
    @NotNull(message = "订单金额不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long amount;

    /**
     * 增加次数
     */
    @NotNull(message = "增加次数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long charCount;

    /**
     * 增加时间
     */
    @NotNull(message = "增加时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long timeCount;

    /**
     * 增加次数
     */
    @NotNull(message = "增加次数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long timesCount;

    /**
     * 订单状态
     */
    @NotNull(message = "订单状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer status;

    /**
     * 支付主题
     */
    @NotBlank(message = "支付主题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String paymentSubject;

    /**
     * 支付平台
     */
    @NotNull(message = "支付平台不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long paymentPlatform;

    /**
     * 支付订单
     */
    @NotBlank(message = "支付订单不能为空", groups = {AddGroup.class, EditGroup.class})
    private String paymentOrder;

    /**
     * 支付时间
     */
    @NotNull(message = "支付时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date paymentTime;

    /**
     * 支付备注
     */
    @NotBlank(message = "支付备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String paymentRemark;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;


}
