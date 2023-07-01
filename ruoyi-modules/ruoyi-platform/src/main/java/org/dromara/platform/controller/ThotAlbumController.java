package org.dromara.platform.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import org.dromara.common.idempotent.annotation.RepeatSubmit;
import org.dromara.common.log.annotation.Log;
import org.dromara.common.web.core.BaseController;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.platform.domain.vo.ThotAlbumVo;
import org.dromara.platform.domain.bo.ThotAlbumBo;
import org.dromara.platform.service.IThotAlbumService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 思集信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/album")
public class ThotAlbumController extends BaseController {

    private final IThotAlbumService thotAlbumService;

    /**
     * 查询思集信息列表
     */
    @SaCheckPermission("thoughts:album:list")
    @GetMapping("/list")
    public TableDataInfo<ThotAlbumVo> list(ThotAlbumBo bo, PageQuery pageQuery) {
        return thotAlbumService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出思集信息列表
     */
    @SaCheckPermission("thoughts:album:export")
    @Log(title = "思集信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotAlbumBo bo, HttpServletResponse response) {
        List<ThotAlbumVo> list = thotAlbumService.queryList(bo);
        ExcelUtil.exportExcel(list, "思集信息", ThotAlbumVo.class, response);
    }

    /**
     * 获取思集信息详细信息
     *
     * @param albumId 主键
     */
    @SaCheckPermission("thoughts:album:query")
    @GetMapping("/{albumId}")
    public R<ThotAlbumVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long albumId) {
        return R.ok(thotAlbumService.queryById(albumId));
    }

    /**
     * 新增思集信息
     */
    @SaCheckPermission("thoughts:album:add")
    @Log(title = "思集信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotAlbumBo bo) {
        return toAjax(thotAlbumService.insertByBo(bo));
    }

    /**
     * 修改思集信息
     */
    @SaCheckPermission("thoughts:album:edit")
    @Log(title = "思集信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotAlbumBo bo) {
        return toAjax(thotAlbumService.updateByBo(bo));
    }

    /**
     * 删除思集信息
     *
     * @param albumIds 主键串
     */
    @SaCheckPermission("thoughts:album:remove")
    @Log(title = "思集信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{albumIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] albumIds) {
        return toAjax(thotAlbumService.deleteWithValidByIds(List.of(albumIds), true));
    }
}
