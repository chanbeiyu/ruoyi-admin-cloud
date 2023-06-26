package org.dromara.alkaid.controller;

import org.dromara.alkaid.domain.bo.GameRankBo;
import org.dromara.alkaid.domain.vo.GameRankVo;
import org.dromara.alkaid.service.IGameRankService;
import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 游戏用户排行榜
 *
 * @author beiyu
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/game/rank")
public class GameRankController extends BaseController {

    private final IGameRankService gameRankService;

    /**
     * 查询游戏用户排行榜列表
     */
    @SaCheckPermission("alkaid:game:rank:list")
    @GetMapping("/list")
    public TableDataInfo<GameRankVo> list(GameRankBo bo, PageQuery pageQuery) {
        return gameRankService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出游戏用户排行榜列表
     */
    @SaCheckPermission("alkaid:game:rank:export")
    @Log(title = "游戏用户排行榜", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(GameRankBo bo, HttpServletResponse response) {
        List<GameRankVo> list = gameRankService.queryList(bo);
        ExcelUtil.exportExcel(list, "游戏用户排行榜", GameRankVo.class, response);
    }

    /**
     * 获取游戏用户排行榜详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("alkaid:game:rank:query")
    @GetMapping("/{id}")
    public R<GameRankVo> getInfo(@NotNull(message = "主键不能为空")
                                 @PathVariable Long id) {
        return R.ok(gameRankService.queryById(id));
    }

    /**
     * 新增游戏用户排行榜
     */
    @SaCheckPermission("alkaid:game:rank:add")
    @Log(title = "游戏用户排行榜", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody GameRankBo bo) {
        return toAjax(gameRankService.insertByBo(bo));
    }

    /**
     * 修改游戏用户排行榜
     */
    @SaCheckPermission("alkaid:game:rank:edit")
    @Log(title = "游戏用户排行榜", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody GameRankBo bo) {
        return toAjax(gameRankService.updateByBo(bo));
    }

    /**
     * 删除游戏用户排行榜
     *
     * @param ids 主键串
     */
    @SaCheckPermission("alkaid:game:rank:remove")
    @Log(title = "游戏用户排行榜", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(gameRankService.deleteWithValidByIds(List.of(ids), true));
    }
}
