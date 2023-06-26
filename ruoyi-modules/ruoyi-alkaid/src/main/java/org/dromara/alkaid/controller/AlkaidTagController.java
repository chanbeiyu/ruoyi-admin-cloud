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
import org.dromara.alkaid.domain.vo.AlkaidTagVo;
import org.dromara.alkaid.domain.bo.AlkaidTagBo;
import org.dromara.alkaid.service.IAlkaidTagService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 标签信息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/tag")
public class AlkaidTagController extends BaseController {

    private final IAlkaidTagService alkaidTagService;

    /**
     * 查询标签信息列表
     */
    @SaCheckPermission("alkaid:tag:list")
    @GetMapping("/list")
    public TableDataInfo<AlkaidTagVo> list(AlkaidTagBo bo, PageQuery pageQuery) {
        return alkaidTagService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出标签信息列表
     */
    @SaCheckPermission("alkaid:tag:export")
    @Log(title = "标签信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidTagBo bo, HttpServletResponse response) {
        List<AlkaidTagVo> list = alkaidTagService.queryList(bo);
        ExcelUtil.exportExcel(list, "标签信息", AlkaidTagVo.class, response);
    }

    /**
     * 获取标签信息详细信息
     *
     * @param tagId 主键
     */
    @SaCheckPermission("alkaid:tag:query")
    @GetMapping("/{tagId}")
    public R<AlkaidTagVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long tagId) {
        return R.ok(alkaidTagService.queryById(tagId));
    }

    /**
     * 新增标签信息
     */
    @SaCheckPermission("alkaid:tag:add")
    @Log(title = "标签信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidTagBo bo) {
        return toAjax(alkaidTagService.insertByBo(bo));
    }

    /**
     * 修改标签信息
     */
    @SaCheckPermission("alkaid:tag:edit")
    @Log(title = "标签信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidTagBo bo) {
        return toAjax(alkaidTagService.updateByBo(bo));
    }

    /**
     * 删除标签信息
     *
     * @param tagIds 主键串
     */
    @SaCheckPermission("alkaid:tag:remove")
    @Log(title = "标签信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] tagIds) {
        return toAjax(alkaidTagService.deleteWithValidByIds(List.of(tagIds), true));
    }
}
