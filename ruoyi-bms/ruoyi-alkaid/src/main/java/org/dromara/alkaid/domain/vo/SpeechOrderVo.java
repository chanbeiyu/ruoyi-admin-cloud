package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.SpeechOrder;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 语音订单视图对象 speech_order
 *
 * @author beiyu
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SpeechOrder.class)
public class SpeechOrderVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 应用编码
     */
    @ExcelProperty(value = "应用编码")
    private String appCode;

    /**
     * 客户端类型
     */
    @ExcelProperty(value = "客户端类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_app_type")
    private Integer client;

    /**
     * 平台
     */
    @ExcelProperty(value = "平台", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_payment_platform")
    private Integer platform;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String orderId;

    /**
     * 订单金额
     */
    @ExcelProperty(value = "订单金额")
    private Long amount;

    /**
     * 增加次数
     */
    @ExcelProperty(value = "增加次数")
    private Long charCount;

    /**
     * 增加时间
     */
    @ExcelProperty(value = "增加时间")
    private Long timeCount;

    /**
     * 增加次数
     */
    @ExcelProperty(value = "增加次数")
    private Long timesCount;

    /**
     * 订单状态
     */
    @ExcelProperty(value = "订单状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_order_status")
    private Integer status;

    /**
     * 支付主题
     */
    @ExcelProperty(value = "支付主题")
    private String paymentSubject;

    /**
     * 支付平台
     */
    @ExcelProperty(value = "支付平台", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_payment_platform")
    private Long paymentPlatform;

    /**
     * 支付订单
     */
    @ExcelProperty(value = "支付订单")
    private String paymentOrder;

    /**
     * 支付时间
     */
    @ExcelProperty(value = "支付时间")
    private Date paymentTime;

    /**
     * 支付备注
     */
    @ExcelProperty(value = "支付备注")
    private String paymentRemark;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
