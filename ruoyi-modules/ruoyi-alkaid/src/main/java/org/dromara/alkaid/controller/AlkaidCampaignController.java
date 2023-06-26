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
import org.dromara.alkaid.domain.vo.AlkaidCampaignVo;
import org.dromara.alkaid.domain.bo.AlkaidCampaignBo;
import org.dromara.alkaid.service.IAlkaidCampaignService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 活动信息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/campaign")
public class AlkaidCampaignController extends BaseController {

    private final IAlkaidCampaignService alkaidCampaignService;

    /**
     * 查询活动信息列表
     */
    @SaCheckPermission("alkaid:campaign:list")
    @GetMapping("/list")
    public TableDataInfo<AlkaidCampaignVo> list(AlkaidCampaignBo bo, PageQuery pageQuery) {
        return alkaidCampaignService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出活动信息列表
     */
    @SaCheckPermission("alkaid:campaign:export")
    @Log(title = "活动信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidCampaignBo bo, HttpServletResponse response) {
        List<AlkaidCampaignVo> list = alkaidCampaignService.queryList(bo);
        ExcelUtil.exportExcel(list, "活动信息", AlkaidCampaignVo.class, response);
    }

    /**
     * 获取活动信息详细信息
     *
     * @param campaignId 主键
     */
    @SaCheckPermission("alkaid:campaign:query")
    @GetMapping("/{campaignId}")
    public R<AlkaidCampaignVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long campaignId) {
        return R.ok(alkaidCampaignService.queryById(campaignId));
    }

    /**
     * 新增活动信息
     */
    @SaCheckPermission("alkaid:campaign:add")
    @Log(title = "活动信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidCampaignBo bo) {
        return toAjax(alkaidCampaignService.insertByBo(bo));
    }

    /**
     * 修改活动信息
     */
    @SaCheckPermission("alkaid:campaign:edit")
    @Log(title = "活动信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidCampaignBo bo) {
        return toAjax(alkaidCampaignService.updateByBo(bo));
    }

    /**
     * 删除活动信息
     *
     * @param campaignIds 主键串
     */
    @SaCheckPermission("alkaid:campaign:remove")
    @Log(title = "活动信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{campaignIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] campaignIds) {
        return toAjax(alkaidCampaignService.deleteWithValidByIds(List.of(campaignIds), true));
    }
}
