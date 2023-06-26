package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 图片信息对象 alkaid_img
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_img")
public class AlkaidImg implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @TableId(value = "img_id")
    private Long imgId;

    /**
     * 创建用户id
     */
    private Long userId;

    /**
     * 标题
     */
    private String title;

    /**
     * 描述
     */
    private String describe;

    /**
     * 标签,英文逗号分隔
     */
    private String tags;

    /**
     * 原图地址
     */
    private String imgUrl;

    /**
     * 小图地址
     */
    private String imgSUrl;

    /**
     * 大图地址
     */
    private String imgLUrl;

    /**
     * 收藏数
     */
    private Long favorNum;

    /**
     * 点赞数
     */
    private Long likeNum;

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
