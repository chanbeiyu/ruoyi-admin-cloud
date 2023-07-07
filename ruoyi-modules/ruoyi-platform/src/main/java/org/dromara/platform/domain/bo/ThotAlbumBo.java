package org.dromara.platform.domain.bo;

import org.dromara.platform.domain.ThotAlbum;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

import java.util.Date;
import java.util.List;

/**
 * 思集信息业务对象 thot_album
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ThotAlbum.class, reverseConvertGenerate = false)
public class ThotAlbumBo extends BaseEntity {

    /**
     * 思集ID
     */
    @NotNull(message = "思集ID不能为空", groups = { EditGroup.class })
    private Long albumId;

    /**
     * 接入App
     */
    @NotNull(message = "接入App不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long appId;
    private List<Long> appIds;

    /**
     * 思绪编号
     */
    @NotBlank(message = "思绪标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String albumTitle;

    /**
     * 思绪描述
     */
    private String description;

    /**
     * 状态
     */
    private Integer status;

    /**
     * 发布时间
     */
    private Date publishTime;

}
