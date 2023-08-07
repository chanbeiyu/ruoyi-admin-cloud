package org.dromara.biz.member.controller;

import java.util.List;

import lombok.RequiredArgsConstructor;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.dromara.biz.member.domain.bo.MemberTypeBo;
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
import org.dromara.biz.member.domain.vo.MemberLevelVo;
import org.dromara.biz.member.domain.bo.MemberLevelBo;
import org.dromara.biz.member.service.IMemberLevelService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 会员级别信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/member/level")
public class MemberLevelController extends BaseController {

    private final IMemberLevelService memberLevelService;

    /**
     * 查询会员级别信息列表
     */
    @SaCheckPermission("member:level:list")
    @GetMapping("/list")
    public TableDataInfo<MemberLevelVo> list(MemberLevelBo bo, PageQuery pageQuery) {
        return memberLevelService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出会员级别信息列表
     */
    @SaCheckPermission("member:level:export")
    @Log(title = "会员级别信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(MemberLevelBo bo, HttpServletResponse response) {
        List<MemberLevelVo> list = memberLevelService.queryList(bo);
        ExcelUtil.exportExcel(list, "会员级别信息", MemberLevelVo.class, response);
    }

    /**
     * 获取会员级别信息详细信息
     *
     * @param levelId 主键
     */
    @SaCheckPermission("member:level:query")
    @GetMapping("/{levelId}")
    public R<MemberLevelVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long levelId) {
        return R.ok(memberLevelService.queryById(levelId));
    }

    /**
     * 新增会员级别信息
     */
    @SaCheckPermission("member:level:add")
    @Log(title = "会员级别信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody MemberLevelBo bo) {
        return toAjax(memberLevelService.insertByBo(bo));
    }

    /**
     * 修改会员级别信息
     */
    @SaCheckPermission("member:level:edit")
    @Log(title = "会员级别信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody MemberLevelBo bo) {
        return toAjax(memberLevelService.updateByBo(bo));
    }

    /**
     * 状态修改
     */
    @SaCheckPermission("member:level:edit")
    @Log(title = "状态变更", businessType = BusinessType.UPDATE)
    @PutMapping("/status")
    public R<Void> changeStatus(@RequestBody MemberLevelBo bo) {
        return toAjax(memberLevelService.updateStatus(bo.getLevelId(), bo.getStatus()));
    }

    /**
     * 删除会员级别信息
     *
     * @param levelIds 主键串
     */
    @SaCheckPermission("member:level:remove")
    @Log(title = "会员级别信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{levelIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] levelIds) {
        return toAjax(memberLevelService.deleteWithValidByIds(List.of(levelIds), true));
    }
}
