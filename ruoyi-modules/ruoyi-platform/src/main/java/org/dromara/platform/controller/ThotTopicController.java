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
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.log.enums.BusinessType;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.platform.domain.vo.ThotTopicVo;
import org.dromara.platform.domain.bo.ThotTopicBo;
import org.dromara.platform.service.IThotTopicService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 话题信息
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/topic")
public class ThotTopicController extends BaseController {

    private final IThotTopicService thotTopicService;

    /**
     * 查询话题信息列表
     */
    @SaCheckPermission("thoughts:topic:list")
    @GetMapping("/list")
    public TableDataInfo<ThotTopicVo> list(ThotTopicBo bo, PageQuery pageQuery) {
        return thotTopicService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出话题信息列表
     */
    @SaCheckPermission("thoughts:topic:export")
    @Log(title = "话题信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(ThotTopicBo bo, HttpServletResponse response) {
        List<ThotTopicVo> list = thotTopicService.queryList(bo);
        ExcelUtil.exportExcel(list, "话题信息", ThotTopicVo.class, response);
    }

    /**
     * 获取话题信息详细信息
     *
     * @param topicId 主键
     */
    @SaCheckPermission("thoughts:topic:query")
    @GetMapping("/{topicId}")
    public R<ThotTopicVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long topicId) {
        return R.ok(thotTopicService.queryById(topicId));
    }

    /**
     * 新增话题信息
     */
    @SaCheckPermission("thoughts:topic:add")
    @Log(title = "话题信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody ThotTopicBo bo) {
        return toAjax(thotTopicService.insertByBo(bo));
    }

    /**
     * 修改话题信息
     */
    @SaCheckPermission("thoughts:topic:edit")
    @Log(title = "话题信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody ThotTopicBo bo) {
        return toAjax(thotTopicService.updateByBo(bo));
    }

    /**
     * 删除话题信息
     *
     * @param topicIds 主键串
     */
    @SaCheckPermission("thoughts:topic:remove")
    @Log(title = "话题信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{topicIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] topicIds) {
        return toAjax(thotTopicService.deleteWithValidByIds(List.of(topicIds), true));
    }
}
