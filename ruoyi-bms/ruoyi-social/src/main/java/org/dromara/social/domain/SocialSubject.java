package org.dromara.social.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 内容主题对象 social_subject
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("social_subject")
public class SocialSubject extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "subject_id")
    private Long subjectId;

    /**
     * 接入App标识
     */
    private String appId;

    /**
     * 主题编码
     */
    private String socialCode;

    /**
     * 主题名称
     */
    private String socialName;

    /**
     * 主题描述
     */
    private String describe;

    /**
     * 备注
     */
    private String remark;


}
