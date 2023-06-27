package org.dromara.alkaid.controller;

import org.dromara.alkaid.domain.bo.SpeechOrderBo;
import org.dromara.alkaid.domain.vo.SpeechOrderVo;
import org.dromara.alkaid.service.ISpeechOrderService;
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
 * 语音订单
 *
 * @author beiyu
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/speech/order")
public class SpeechOrderController extends BaseController {

    private final ISpeechOrderService speechOrderService;

    /**
     * 查询语音订单列表
     */
    @SaCheckPermission("alkaid:speech:order:list")
    @GetMapping("/list")
    public TableDataInfo<SpeechOrderVo> list(SpeechOrderBo bo, PageQuery pageQuery) {
        return speechOrderService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出语音订单列表
     */
    @SaCheckPermission("alkaid:speech:order:export")
    @Log(title = "语音订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(SpeechOrderBo bo, HttpServletResponse response) {
        List<SpeechOrderVo> list = speechOrderService.queryList(bo);
        ExcelUtil.exportExcel(list, "语音订单", SpeechOrderVo.class, response);
    }

    /**
     * 获取语音订单详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("alkaid:speech:order:query")
    @GetMapping("/{id}")
    public R<SpeechOrderVo> getInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return R.ok(speechOrderService.queryById(id));
    }

    /**
     * 新增语音订单
     */
    @SaCheckPermission("alkaid:speech:order:add")
    @Log(title = "语音订单", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody SpeechOrderBo bo) {
        return toAjax(speechOrderService.insertByBo(bo));
    }

    /**
     * 修改语音订单
     */
    @SaCheckPermission("alkaid:speech:order:edit")
    @Log(title = "语音订单", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody SpeechOrderBo bo) {
        return toAjax(speechOrderService.updateByBo(bo));
    }

    /**
     * 删除语音订单
     *
     * @param ids 主键串
     */
    @SaCheckPermission("alkaid:speech:order:remove")
    @Log(title = "语音订单", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(speechOrderService.deleteWithValidByIds(List.of(ids), true));
    }
}
