package org.dromara.alkaid.controller;

import org.dromara.alkaid.domain.bo.RobotPropmtBo;
import org.dromara.alkaid.domain.vo.RobotPropmtVo;
import org.dromara.alkaid.service.IRobotPropmtService;
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
 * Ai Propmt
 *
 * @author beiyu
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/robot/propmt")
public class RobotPropmtController extends BaseController {

    private final IRobotPropmtService robotPropmtService;

    /**
     * 查询Ai Propmt列表
     */
    @SaCheckPermission("alkaid:robot:propmt:list")
    @GetMapping("/list")
    public TableDataInfo<RobotPropmtVo> list(RobotPropmtBo bo, PageQuery pageQuery) {
        return robotPropmtService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出Ai Propmt列表
     */
    @SaCheckPermission("alkaid:robot:propmt:export")
    @Log(title = "Ai Propmt", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(RobotPropmtBo bo, HttpServletResponse response) {
        List<RobotPropmtVo> list = robotPropmtService.queryList(bo);
        ExcelUtil.exportExcel(list, "Ai Propmt", RobotPropmtVo.class, response);
    }

    /**
     * 获取Ai Propmt详细信息
     *
     * @param id 主键
     */
    @SaCheckPermission("alkaid:robot:propmt:query")
    @GetMapping("/{id}")
    public R<RobotPropmtVo> getInfo(@NotNull(message = "主键不能为空")
                                    @PathVariable Long id) {
        return R.ok(robotPropmtService.queryById(id));
    }

    /**
     * 新增Ai Propmt
     */
    @SaCheckPermission("alkaid:robot:propmt:add")
    @Log(title = "Ai Propmt", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody RobotPropmtBo bo) {
        return toAjax(robotPropmtService.insertByBo(bo));
    }

    /**
     * 修改Ai Propmt
     */
    @SaCheckPermission("alkaid:robot:propmt:edit")
    @Log(title = "Ai Propmt", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody RobotPropmtBo bo) {
        return toAjax(robotPropmtService.updateByBo(bo));
    }

    /**
     * 删除Ai Propmt
     *
     * @param ids 主键串
     */
    @SaCheckPermission("alkaid:robot:propmt:remove")
    @Log(title = "Ai Propmt", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] ids) {
        return toAjax(robotPropmtService.deleteWithValidByIds(List.of(ids), true));
    }
}
