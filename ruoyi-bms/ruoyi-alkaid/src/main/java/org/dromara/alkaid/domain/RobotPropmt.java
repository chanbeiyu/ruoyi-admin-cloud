package org.dromara.alkaid.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;

/**
 * Ai Propmt对象 robot_propmt
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("robot_propmt")
public class RobotPropmt extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 主题
     */
    private String subject;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * 平台
     */
    private Integer platform;

    /**
     * 来源
     */
    private Integer source;

    /**
     * 备注
     */
    private String remark;


}
