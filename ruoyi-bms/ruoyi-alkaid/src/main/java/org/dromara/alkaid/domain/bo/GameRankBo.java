package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.GameRank;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 游戏用户排行榜业务对象 game_rank
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = GameRank.class, reverseConvertGenerate = false)
public class GameRankBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 应用编号
     */
    @NotBlank(message = "应用编号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String appCode;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;

    /**
     * 关卡
     */
    @NotBlank(message = "关卡不能为空", groups = {AddGroup.class, EditGroup.class})
    private String leave;

    /**
     * 计分日期
     */
    @NotNull(message = "计分日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date date;

    /**
     * 得分
     */
    @NotNull(message = "得分不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long score;

    /**
     * 得分类型
     */
    @NotNull(message = "得分类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer scoreType;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;


}
