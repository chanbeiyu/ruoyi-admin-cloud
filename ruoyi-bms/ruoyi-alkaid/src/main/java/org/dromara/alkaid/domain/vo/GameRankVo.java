package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.GameRank;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 游戏用户排行榜视图对象 game_rank
 *
 * @author beiyu
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = GameRank.class)
public class GameRankVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 应用编号
     */
    @ExcelProperty(value = "应用编号")
    private String appCode;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    /**
     * 关卡
     */
    @ExcelProperty(value = "关卡")
    private String leave;

    /**
     * 计分日期
     */
    @ExcelProperty(value = "计分日期")
    private Date date;

    /**
     * 得分
     */
    @ExcelProperty(value = "得分")
    private Long score;

    /**
     * 得分类型
     */
    @ExcelProperty(value = "得分类型")
    private Integer scoreType;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
