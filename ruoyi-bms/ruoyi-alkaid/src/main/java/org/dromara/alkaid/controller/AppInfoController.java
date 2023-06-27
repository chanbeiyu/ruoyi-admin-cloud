package org.dromara.alkaid.controller;

import org.dromara.alkaid.domain.bo.AppInfoBo;
import org.dromara.alkaid.domain.vo.AppInfoVo;
import org.dromara.alkaid.service.IAppInfoService;
import cn.dev33.satoken.annotation.SaCheckPermission;
import jakarta.annotation.Resource;
import jakarta.annotation.Resources;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.domain.R;
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
 * 应用信息
 *
 * @author beiyu
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/app/info")
public class AppInfoController extends BaseController {

    private final IAppInfoService appInfoService;

    /**
     * 查询应用信息列表
     */
    @SaCheckPermission("alkaid:app:info:list")
    @GetMapping("/list")
    public TableDataInfo<AppInfoVo> list(AppInfoBo bo, PageQuery pageQuery) {
        return appInfoService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出应用信息列表
     */
    @SaCheckPermission("alkaid:app:info:export")
    @Log(title = "应用信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AppInfoBo bo, HttpServletResponse response) {
        List<AppInfoVo> list = appInfoService.queryList(bo);
        ExcelUtil.exportExcel(list, "应用信息", AppInfoVo.class, response);
    }

    /**
     * 获取应用信息详细信息
     *
     * @param code 主键
     */
    @SaCheckPermission("alkaid:app:info:query")
    @GetMapping("/{code}")
    public R<AppInfoVo> getInfo(@NotNull(message = "主键不能为空")
                                @PathVariable String code) {
        return R.ok(appInfoService.queryById(code));
    }

    /**
     * 添加/修改应用信息
     */
    @SaCheckPermission("alkaid:app:info:edit")
    @Log(title = "应用信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AppInfoBo bo) {
        return toAjax(appInfoService.insertOrUpdate(bo));
    }

    /**
     * 删除应用信息
     *
     * @param codes 主键串
     */
    @SaCheckPermission("alkaid:app:info:remove")
    @Log(title = "应用信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{codes}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] codes) {
        return toAjax(appInfoService.deleteWithValidByIds(List.of(codes), true));
    }
}
