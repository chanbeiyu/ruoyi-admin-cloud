package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 图集收藏信息对象 alkaid_favor
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_favor")
public class AlkaidFavor implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 组件id
     */
    @TableId(value = "favor_id")
    private Long favorId;

    /**
     * 图集id
     */
    private Long albumId;

    /**
     * 创建用户id
     */
    private Long favorUserId;

    /**
     * 图集用户id
     */
    private Long albumUserId;

    /**
     * 描述
     */
    private String describe;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 备注
     */
    private String remark;


}
