package org.dromara.alkaid.controller;

import org.dromara.alkaid.domain.bo.SpeechRecordBo;
import org.dromara.alkaid.domain.vo.SpeechRecordVo;
import org.dromara.alkaid.service.ISpeechRecordService;
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
 * 语音记录管理
 *
 * @author beiyu
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/speech/record")
public class SpeechRecordController extends BaseController {

    private final ISpeechRecordService speechRecordService;

    /**
     * 查询语音记录管理列表
     */
    @SaCheckPermission("alkaid:speech:record:list")
    @GetMapping("/list")
    public TableDataInfo<SpeechRecordVo> list(SpeechRecordBo bo, PageQuery pageQuery) {
        return speechRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出语音记录管理列表
     */
    @SaCheckPermission("alkaid:speech:record:export")
    @Log(title = "语音记录管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SpeechRecordBo bo, HttpServletResponse response) {
        List<SpeechRecordVo> list = speechRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "语音记录管理", SpeechRecordVo.class, response);
    }

    /**
     * 获取语音记录管理详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("alkaid:speech:record:query")
    @GetMapping("/{id}")
    public R<SpeechRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long id) {
        return R.ok(speechRecordService.queryById(id));
    }

    /**
     * 新增语音记录管理
     */
    @SaCheckPermission("alkaid:speech:record:add")
    @Log(title = "语音记录管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SpeechRecordBo bo) {
        return toAjax(speechRecordService.insertByBo(bo));
    }

    /**
     * 修改语音记录管理
     */
    @SaCheckPermission("alkaid:speech:record:edit")
    @Log(title = "语音记录管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SpeechRecordBo bo) {
        return toAjax(speechRecordService.updateByBo(bo));
    }

    /**
     * 删除语音记录管理
     *
     * @param ids 主键串
     */
    @SaCheckPermission("alkaid:speech:record:remove")
    @Log(title = "语音记录管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(speechRecordService.deleteWithValidByIds(List.of(ids), true));
    }
}
