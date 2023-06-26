package org.dromara.alkaid.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 语音订单对象 speech_order
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("speech_order")
public class SpeechOrder extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 应用编码
     */
    private String appCode;

    /**
     * 客户端类型
     */
    private Integer client;

    /**
     * 平台
     */
    private Integer platform;

    /**
     * 订单号
     */
    private String orderId;

    /**
     * 订单金额
     */
    private Long amount;

    /**
     * 增加次数
     */
    private Long charCount;

    /**
     * 增加时间
     */
    private Long timeCount;

    /**
     * 增加次数
     */
    private Long timesCount;

    /**
     * 订单状态
     */
    private Integer status;

    /**
     * 支付主题
     */
    private String paymentSubject;

    /**
     * 支付平台
     */
    private Long paymentPlatform;

    /**
     * 支付订单
     */
    private String paymentOrder;

    /**
     * 支付时间
     */
    private Date paymentTime;

    /**
     * 支付备注
     */
    private String paymentRemark;

    /**
     * 备注
     */
    private String remark;


}
