package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;

/**
 * 标签信息对象 alkaid_tag
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_tag")
public class AlkaidTag implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 标签id
     */
    @TableId(value = "tag_id")
    private Long tagId;

    /**
     * 标签名称
     */
    private String tagName;

    /**
     * 标签类型
     */
    private String tagType;

    /**
     * 备注
     */
    private String remark;


}
