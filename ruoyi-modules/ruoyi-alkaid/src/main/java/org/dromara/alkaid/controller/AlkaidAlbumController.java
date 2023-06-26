package org.dromara.alkaid.controller;

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
import org.dromara.alkaid.domain.vo.AlkaidAlbumVo;
import org.dromara.alkaid.domain.bo.AlkaidAlbumBo;
import org.dromara.alkaid.service.IAlkaidAlbumService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 图集信息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/album")
public class AlkaidAlbumController extends BaseController {

    private final IAlkaidAlbumService alkaidAlbumService;

    /**
     * 查询图集信息列表
     */
    @SaCheckPermission("alkaid:album:list")
    @GetMapping("/list")
    public TableDataInfo<AlkaidAlbumVo> list(AlkaidAlbumBo bo, PageQuery pageQuery) {
        return alkaidAlbumService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图集信息列表
     */
    @SaCheckPermission("alkaid:album:export")
    @Log(title = "图集信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidAlbumBo bo, HttpServletResponse response) {
        List<AlkaidAlbumVo> list = alkaidAlbumService.queryList(bo);
        ExcelUtil.exportExcel(list, "图集信息", AlkaidAlbumVo.class, response);
    }

    /**
     * 获取图集信息详细信息
     *
     * @param albumId 主键
     */
    @SaCheckPermission("alkaid:album:query")
    @GetMapping("/{albumId}")
    public R<AlkaidAlbumVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long albumId) {
        return R.ok(alkaidAlbumService.queryById(albumId));
    }

    /**
     * 新增图集信息
     */
    @SaCheckPermission("alkaid:album:add")
    @Log(title = "图集信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidAlbumBo bo) {
        return toAjax(alkaidAlbumService.insertByBo(bo));
    }

    /**
     * 修改图集信息
     */
    @SaCheckPermission("alkaid:album:edit")
    @Log(title = "图集信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidAlbumBo bo) {
        return toAjax(alkaidAlbumService.updateByBo(bo));
    }

    /**
     * 删除图集信息
     *
     * @param albumIds 主键串
     */
    @SaCheckPermission("alkaid:album:remove")
    @Log(title = "图集信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{albumIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] albumIds) {
        return toAjax(alkaidAlbumService.deleteWithValidByIds(List.of(albumIds), true));
    }
}
