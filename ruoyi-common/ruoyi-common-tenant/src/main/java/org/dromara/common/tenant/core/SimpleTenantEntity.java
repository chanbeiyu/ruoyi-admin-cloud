package org.dromara.common.tenant.core;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 租户基类
 *
 * @author Michelle.Chung
 */
@Data
public class SimpleTenantEntity implements Serializable {

    /**
     * 租户编号
     */
    private String tenantId;

}
