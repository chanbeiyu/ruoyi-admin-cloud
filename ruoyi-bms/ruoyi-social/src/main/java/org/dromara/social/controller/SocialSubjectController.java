package org.dromara.social.controller;

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
import org.dromara.social.domain.vo.SocialSubjectVo;
import org.dromara.social.domain.bo.SocialSubjectBo;
import org.dromara.social.service.ISocialSubjectService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 内容主题
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/subject")
public class SocialSubjectController extends BaseController {

    private final ISocialSubjectService socialSubjectService;

    /**
     * 查询内容主题列表
     */
    @SaCheckPermission("social:subject:list")
    @GetMapping("/list")
    public TableDataInfo<SocialSubjectVo> list(SocialSubjectBo bo, PageQuery pageQuery) {
        return socialSubjectService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出内容主题列表
     */
    @SaCheckPermission("social:subject:export")
    @Log(title = "内容主题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialSubjectBo bo, HttpServletResponse response) {
        List<SocialSubjectVo> list = socialSubjectService.queryList(bo);
        ExcelUtil.exportExcel(list, "内容主题", SocialSubjectVo.class, response);
    }

    /**
     * 获取内容主题详细信息
     *
     * @param subjectId 主键
     */
    @SaCheckPermission("social:subject:query")
    @GetMapping("/{subjectId}")
    public R<SocialSubjectVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long subjectId) {
        return R.ok(socialSubjectService.queryById(subjectId));
    }

    /**
     * 新增内容主题
     */
    @SaCheckPermission("social:subject:add")
    @Log(title = "内容主题", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialSubjectBo bo) {
        return toAjax(socialSubjectService.insertByBo(bo));
    }

    /**
     * 修改内容主题
     */
    @SaCheckPermission("social:subject:edit")
    @Log(title = "内容主题", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialSubjectBo bo) {
        return toAjax(socialSubjectService.updateByBo(bo));
    }

    /**
     * 删除内容主题
     *
     * @param subjectIds 主键串
     */
    @SaCheckPermission("social:subject:remove")
    @Log(title = "内容主题", businessType = BusinessType.DELETE)
    @DeleteMapping("/{subjectIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] subjectIds) {
        return toAjax(socialSubjectService.deleteWithValidByIds(List.of(subjectIds), true));
    }
}
