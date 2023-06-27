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
import org.dromara.alkaid.domain.vo.AlkaidImgVo;
import org.dromara.alkaid.domain.bo.AlkaidImgBo;
import org.dromara.alkaid.service.IAlkaidImgService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 图片信息
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/img")
public class AlkaidImgController extends BaseController {

    private final IAlkaidImgService alkaidImgService;

    /**
     * 查询图片信息列表
     */
    @SaCheckPermission("alkaid:img:list")
    @GetMapping("/list")
    public TableDataInfo<AlkaidImgVo> list(AlkaidImgBo bo, PageQuery pageQuery) {
        return alkaidImgService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出图片信息列表
     */
    @SaCheckPermission("alkaid:img:export")
    @Log(title = "图片信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(AlkaidImgBo bo, HttpServletResponse response) {
        List<AlkaidImgVo> list = alkaidImgService.queryList(bo);
        ExcelUtil.exportExcel(list, "图片信息", AlkaidImgVo.class, response);
    }

    /**
     * 获取图片信息详细信息
     *
     * @param imgId 主键
     */
    @SaCheckPermission("alkaid:img:query")
    @GetMapping("/{imgId}")
    public R<AlkaidImgVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long imgId) {
        return R.ok(alkaidImgService.queryById(imgId));
    }

    /**
     * 新增图片信息
     */
    @SaCheckPermission("alkaid:img:add")
    @Log(title = "图片信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody AlkaidImgBo bo) {
        return toAjax(alkaidImgService.insertByBo(bo));
    }

    /**
     * 修改图片信息
     */
    @SaCheckPermission("alkaid:img:edit")
    @Log(title = "图片信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody AlkaidImgBo bo) {
        return toAjax(alkaidImgService.updateByBo(bo));
    }

    /**
     * 删除图片信息
     *
     * @param imgIds 主键串
     */
    @SaCheckPermission("alkaid:img:remove")
    @Log(title = "图片信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{imgIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] imgIds) {
        return toAjax(alkaidImgService.deleteWithValidByIds(List.of(imgIds), true));
    }
}
