package org.dromara.platform.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 应用扩展信息对象 app_extend
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("app_extend")
public class AppExtend extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 组件id
     */
    @TableId(value = "extend_id")
    private Long extendId;

    /**
     * AppId
     */
    private Long appId;

    /**
     * 联系微信
     */
    private String contactWechat;

    /**
     * 联系方式
     */
    private String contactPhone;

    /**
     * 联系邮箱
     */
    private String contactEmail;

    /**
     * 服务协议
     */
    private String serviceAgreement;

    /**
     * 隐私条款
     */
    private String privacyPolicy;

    /**
     * 行为规范
     */
    private String behaviourNorm;

    /**
     * 个人信息收集清单
     */
    private String personalInfoChecklist;

    /**
     * 备注
     */
    private String remark;


}
