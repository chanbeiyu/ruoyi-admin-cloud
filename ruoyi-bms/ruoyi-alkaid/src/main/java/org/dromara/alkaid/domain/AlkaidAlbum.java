package org.dromara.alkaid.domain;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 图集信息对象 alkaid_album
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@TableName("alkaid_album")
public class AlkaidAlbum implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 集id
     */
    @TableId(value = "album_id")
    private Long albumId;

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
     * 分类
     */
    private Long category;

    /**
     * 标签,英文逗号分隔
     */
    private String tags;

    /**
     * 默认封面
     */
    private String coverImg;

    /**
     * 收藏数
     */
    private Long favorNum;

    /**
     * 点赞数
     */
    private Long likeNum;

    /**
     * 是否置顶Y是N否
     */
    private String isTop;

    /**
     * 状态:0草稿1正常2删除
     */
    private Long status;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 发布时间
     */
    private Date publishTime;

    /**
     * 删除时间
     */
    private Date deleteTime;

    /**
     * 备注
     */
    private String remark;


}
