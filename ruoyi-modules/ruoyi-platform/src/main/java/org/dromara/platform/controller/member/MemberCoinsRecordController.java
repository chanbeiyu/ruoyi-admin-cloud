package org.dromara.platform.controller.member;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.basal.platform.domain.member.bo.MemberCoinsRecordBo;
import org.dromara.basal.platform.domain.member.vo.MemberCoinsRecordVo;
import org.dromara.basal.platform.service.member.IMemberCoinsRecordService;
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
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 代币记录信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/coins/record")
public class MemberCoinsRecordController extends BaseController {

    private final IMemberCoinsRecordService memberCoinsRecordService;

    /**
     * 查询代币记录信息列表
     */
    @SaCheckPermission("member:coins:record:list")
    @GetMapping("/list")
    public TableDataInfo<MemberCoinsRecordVo> list(MemberCoinsRecordBo bo, PageQuery pageQuery) {
        return memberCoinsRecordService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出代币记录信息列表
     */
    @SaCheckPermission("member:coins:record:export")
    @Log(title = "代币记录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberCoinsRecordBo bo, HttpServletResponse response) {
        List<MemberCoinsRecordVo> list = memberCoinsRecordService.queryList(bo);
        ExcelUtil.exportExcel(list, "代币记录信息", MemberCoinsRecordVo.class, response);
    }

    /**
     * 获取代币记录信息详细信息
     *
     * @param recordId 主键
     */
    @SaCheckPermission("member:coins:record:query")
    @GetMapping("/{recordId}")
    public R<MemberCoinsRecordVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long recordId) {
        return R.ok(memberCoinsRecordService.queryById(recordId));
    }

    /**
     * 新增代币记录信息
     */
    @SaCheckPermission("member:coins:record:add")
    @Log(title = "代币记录信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberCoinsRecordBo bo) {
        return toAjax(memberCoinsRecordService.insertByBo(bo));
    }

    /**
     * 修改代币记录信息
     */
    @SaCheckPermission("member:coins:record:edit")
    @Log(title = "代币记录信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberCoinsRecordBo bo) {
        return toAjax(memberCoinsRecordService.updateByBo(bo));
    }

    /**
     * 删除代币记录信息
     *
     * @param recordIds 主键串
     */
    @SaCheckPermission("member:coins:record:remove")
    @Log(title = "代币记录信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{recordIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] recordIds) {
        return toAjax(memberCoinsRecordService.deleteWithValidByIds(List.of(recordIds), true));
    }
}
