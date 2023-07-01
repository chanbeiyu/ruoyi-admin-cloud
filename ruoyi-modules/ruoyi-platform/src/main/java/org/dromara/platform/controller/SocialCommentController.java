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
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.platform.domain.vo.SocialCommentVo;
import org.dromara.platform.domain.bo.SocialCommentBo;
import org.dromara.platform.service.ISocialCommentService;

/**
 * 评论信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/comment")
public class SocialCommentController extends BaseController {

    private final ISocialCommentService socialCommentService;

    /**
     * 查询评论信息列表
     */
    @SaCheckPermission("social:comment:list")
    @GetMapping("/list")
    public R<List<SocialCommentVo>> list(SocialCommentBo bo) {
        List<SocialCommentVo> list = socialCommentService.queryList(bo);
        return R.ok(list);
    }

    /**
     * 导出评论信息列表
     */
    @SaCheckPermission("social:comment:export")
    @Log(title = "评论信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialCommentBo bo, HttpServletResponse response) {
        List<SocialCommentVo> list = socialCommentService.queryList(bo);
        ExcelUtil.exportExcel(list, "评论信息", SocialCommentVo.class, response);
    }

    /**
     * 获取评论信息详细信息
     *
     * @param commentId 主键
     */
    @SaCheckPermission("social:comment:query")
    @GetMapping("/{commentId}")
    public R<SocialCommentVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long commentId) {
        return R.ok(socialCommentService.queryById(commentId));
    }

    /**
     * 新增评论信息
     */
    @SaCheckPermission("social:comment:add")
    @Log(title = "评论信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialCommentBo bo) {
        return toAjax(socialCommentService.insertByBo(bo));
    }

    /**
     * 修改评论信息
     */
    @SaCheckPermission("social:comment:edit")
    @Log(title = "评论信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialCommentBo bo) {
        return toAjax(socialCommentService.updateByBo(bo));
    }

    /**
     * 删除评论信息
     *
     * @param commentIds 主键串
     */
    @SaCheckPermission("social:comment:remove")
    @Log(title = "评论信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] commentIds) {
        return toAjax(socialCommentService.deleteWithValidByIds(List.of(commentIds), true));
    }
}
