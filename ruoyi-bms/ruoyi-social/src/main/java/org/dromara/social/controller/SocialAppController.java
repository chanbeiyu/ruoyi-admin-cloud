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
import org.dromara.social.domain.vo.SocialAppVo;
import org.dromara.social.domain.bo.SocialAppBo;
import org.dromara.social.service.ISocialAppService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 应用信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/social/app")
public class SocialAppController extends BaseController {

    private final ISocialAppService socialAppService;

    /**
     * 查询应用信息列表
     */
    @SaCheckPermission("social:app:list")
    @GetMapping("/list")
    public TableDataInfo<SocialAppVo> list(SocialAppBo bo, PageQuery pageQuery) {
        return socialAppService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出应用信息列表
     */
    @SaCheckPermission("social:app:export")
    @Log(title = "应用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SocialAppBo bo, HttpServletResponse response) {
        List<SocialAppVo> list = socialAppService.queryList(bo);
        ExcelUtil.exportExcel(list, "应用信息", SocialAppVo.class, response);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param appId 主键
     */
    @SaCheckPermission("social:app:query")
    @GetMapping("/{appId}")
    public R<SocialAppVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long appId) {
        return R.ok(socialAppService.queryById(appId));
    }

    /**
     * 新增应用信息
     */
    @SaCheckPermission("social:app:add")
    @Log(title = "应用信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SocialAppBo bo) {
        return toAjax(socialAppService.insertByBo(bo));
    }

    /**
     * 修改应用信息
     */
    @SaCheckPermission("social:app:edit")
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SocialAppBo bo) {
        return toAjax(socialAppService.updateByBo(bo));
    }

    /**
     * 删除应用信息
     *
     * @param appIds 主键串
     */
    @SaCheckPermission("social:app:remove")
    @Log(title = "应用信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{appIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] appIds) {
        return toAjax(socialAppService.deleteWithValidByIds(List.of(appIds), true));
    }
}
