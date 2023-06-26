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
import org.dromara.alkaid.domain.vo.AlkaidFavorVo;
import org.dromara.alkaid.domain.bo.AlkaidFavorBo;
import org.dromara.alkaid.service.IAlkaidFavorService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 图集收藏信息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/alkaid/favor")
public class AlkaidFavorController extends BaseController {

    private final IAlkaidFavorService alkaidFavorService;

    /**
     * 查询图集收藏信息列表
     */
    @SaCheckPermission("alkaid:favor:list")
    @GetMapping("/list")
    public TableDataInfo<AlkaidFavorVo> list(AlkaidFavorBo bo, PageQuery pageQuery) {
        return alkaidFavorService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图集收藏信息列表
     */
    @SaCheckPermission("alkaid:favor:export")
    @Log(title = "图集收藏信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidFavorBo bo, HttpServletResponse response) {
        List<AlkaidFavorVo> list = alkaidFavorService.queryList(bo);
        ExcelUtil.exportExcel(list, "图集收藏信息", AlkaidFavorVo.class, response);
    }

    /**
     * 获取图集收藏信息详细信息
     *
     * @param favorId 主键
     */
    @SaCheckPermission("alkaid:favor:query")
    @GetMapping("/{favorId}")
    public R<AlkaidFavorVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long favorId) {
        return R.ok(alkaidFavorService.queryById(favorId));
    }

    /**
     * 新增图集收藏信息
     */
    @SaCheckPermission("alkaid:favor:add")
    @Log(title = "图集收藏信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidFavorBo bo) {
        return toAjax(alkaidFavorService.insertByBo(bo));
    }

    /**
     * 修改图集收藏信息
     */
    @SaCheckPermission("alkaid:favor:edit")
    @Log(title = "图集收藏信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidFavorBo bo) {
        return toAjax(alkaidFavorService.updateByBo(bo));
    }

    /**
     * 删除图集收藏信息
     *
     * @param favorIds 主键串
     */
    @SaCheckPermission("alkaid:favor:remove")
    @Log(title = "图集收藏信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{favorIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] favorIds) {
        return toAjax(alkaidFavorService.deleteWithValidByIds(List.of(favorIds), true));
    }
}
