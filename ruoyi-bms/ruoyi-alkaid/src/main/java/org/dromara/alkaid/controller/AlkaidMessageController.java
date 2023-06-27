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
import org.dromara.alkaid.domain.vo.AlkaidMessageVo;
import org.dromara.alkaid.domain.bo.AlkaidMessageBo;
import org.dromara.alkaid.service.IAlkaidMessageService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 用户消息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/message")
public class AlkaidMessageController extends BaseController {

    private final IAlkaidMessageService alkaidMessageService;

    /**
     * 查询用户消息列表
     */
    @SaCheckPermission("alkaid:message:list")
    @GetMapping("/list")
    public TableDataInfo<AlkaidMessageVo> list(AlkaidMessageBo bo, PageQuery pageQuery) {
        return alkaidMessageService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出用户消息列表
     */
    @SaCheckPermission("alkaid:message:export")
    @Log(title = "用户消息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidMessageBo bo, HttpServletResponse response) {
        List<AlkaidMessageVo> list = alkaidMessageService.queryList(bo);
        ExcelUtil.exportExcel(list, "用户消息", AlkaidMessageVo.class, response);
    }

    /**
     * 获取用户消息详细信息
     *
     * @param messageId 主键
     */
    @SaCheckPermission("alkaid:message:query")
    @GetMapping("/{messageId}")
    public R<AlkaidMessageVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long messageId) {
        return R.ok(alkaidMessageService.queryById(messageId));
    }

    /**
     * 新增用户消息
     */
    @SaCheckPermission("alkaid:message:add")
    @Log(title = "用户消息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidMessageBo bo) {
        return toAjax(alkaidMessageService.insertByBo(bo));
    }

    /**
     * 修改用户消息
     */
    @SaCheckPermission("alkaid:message:edit")
    @Log(title = "用户消息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidMessageBo bo) {
        return toAjax(alkaidMessageService.updateByBo(bo));
    }

    /**
     * 删除用户消息
     *
     * @param messageIds 主键串
     */
    @SaCheckPermission("alkaid:message:remove")
    @Log(title = "用户消息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] messageIds) {
        return toAjax(alkaidMessageService.deleteWithValidByIds(List.of(messageIds), true));
    }
}
