package org.dromara.alkaid.controller;

import org.dromara.alkaid.domain.bo.SpeechBillingBo;
import org.dromara.alkaid.domain.vo.SpeechBillingVo;
import org.dromara.alkaid.service.ISpeechBillingService;
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
 * 语音计费
 *
 * @author beiyu
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/speech/billing")
public class SpeechBillingController extends BaseController {

    private final ISpeechBillingService speechBillingService;

    /**
     * 查询语音计费列表
     */
    @SaCheckPermission("alkaid:speech:billing:list")
    @GetMapping("/list")
    public TableDataInfo<SpeechBillingVo> list(SpeechBillingBo bo, PageQuery pageQuery) {
        return speechBillingService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出语音计费列表
     */
    @SaCheckPermission("alkaid:speech:billing:export")
    @Log(title = "语音计费", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SpeechBillingBo bo, HttpServletResponse response) {
        List<SpeechBillingVo> list = speechBillingService.queryList(bo);
        ExcelUtil.exportExcel(list, "语音计费", SpeechBillingVo.class, response);
    }

    /**
     * 获取语音计费详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("alkaid:speech:billing:query")
    @GetMapping("/{id}")
    public R<SpeechBillingVo> getInfo(@NotNull(message = "主键不能为空")
                                      @PathVariable Long id) {
        return R.ok(speechBillingService.queryById(id));
    }

    /**
     * 新增语音计费
     */
    @SaCheckPermission("alkaid:speech:billing:add")
    @Log(title = "语音计费", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SpeechBillingBo bo) {
        return toAjax(speechBillingService.insertByBo(bo));
    }

    /**
     * 修改语音计费
     */
    @SaCheckPermission("alkaid:speech:billing:edit")
    @Log(title = "语音计费", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SpeechBillingBo bo) {
        return toAjax(speechBillingService.updateByBo(bo));
    }

    /**
     * 删除语音计费
     *
     * @param ids 主键串
     */
    @SaCheckPermission("alkaid:speech:billing:remove")
    @Log(title = "语音计费", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(speechBillingService.deleteWithValidByIds(List.of(ids), true));
    }
}
