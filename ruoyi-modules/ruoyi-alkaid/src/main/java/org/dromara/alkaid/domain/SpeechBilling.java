package org.dromara.alkaid.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;

/**
 * 语音计费对象 speech_billing
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("speech_billing")
public class SpeechBilling extends TenantEntity {

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
     * 应用编号
     */
    private String appCode;

    /**
     * 剩余字符
     */
    private Long totalChar;

    /**
     * 剩余时常
     */
    private Long totalTime;

    /**
     * 剩余次数
     */
    private Long totalTimes;

    /**
     * 备注
     */
    private String remark;


}
