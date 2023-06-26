package org.dromara.alkaid.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.tenant.core.TenantEntity;

import java.io.Serial;
import java.util.Date;

/**
 * 游戏用户排行榜对象 game_rank
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("game_rank")
public class GameRank extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 应用编号
     */
    private String appCode;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 关卡
     */
    private String leave;

    /**
     * 计分日期
     */
    private Date date;

    /**
     * 得分
     */
    private Long score;

    /**
     * 得分类型
     */
    private Integer scoreType;

    /**
     * 备注
     */
    private String remark;


}
