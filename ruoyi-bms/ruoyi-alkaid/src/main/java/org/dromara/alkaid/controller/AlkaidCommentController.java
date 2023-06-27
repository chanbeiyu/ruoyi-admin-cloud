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
import org.dromara.alkaid.domain.vo.AlkaidCommentVo;
import org.dromara.alkaid.domain.bo.AlkaidCommentBo;
import org.dromara.alkaid.service.IAlkaidCommentService;

/**
 * 图集评论信息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class AlkaidCommentController extends BaseController {

    private final IAlkaidCommentService alkaidCommentService;

    /**
     * 查询图集评论信息列表
     */
    @SaCheckPermission("alkaid:comment:list")
    @GetMapping("/list")
    public R<List<AlkaidCommentVo>> list(AlkaidCommentBo bo) {
        List<AlkaidCommentVo> list = alkaidCommentService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出图集评论信息列表
     */
    @SaCheckPermission("alkaid:comment:export")
    @Log(title = "图集评论信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidCommentBo bo, HttpServletResponse response) {
        List<AlkaidCommentVo> list = alkaidCommentService.queryList(bo);
        ExcelUtil.exportExcel(list, "图集评论信息", AlkaidCommentVo.class, response);
    }

    /**
     * 获取图集评论信息详细信息
     *
     * @param commentId 主键
     */
    @SaCheckPermission("alkaid:comment:query")
    @GetMapping("/{commentId}")
    public R<AlkaidCommentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long commentId) {
        return R.ok(alkaidCommentService.queryById(commentId));
    }

    /**
     * 新增图集评论信息
     */
    @SaCheckPermission("alkaid:comment:add")
    @Log(title = "图集评论信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidCommentBo bo) {
        return toAjax(alkaidCommentService.insertByBo(bo));
    }

    /**
     * 修改图集评论信息
     */
    @SaCheckPermission("alkaid:comment:edit")
    @Log(title = "图集评论信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidCommentBo bo) {
        return toAjax(alkaidCommentService.updateByBo(bo));
    }

    /**
     * 删除图集评论信息
     *
     * @param commentIds 主键串
     */
    @SaCheckPermission("alkaid:comment:remove")
    @Log(title = "图集评论信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] commentIds) {
        return toAjax(alkaidCommentService.deleteWithValidByIds(List.of(commentIds), true));
    }
}
