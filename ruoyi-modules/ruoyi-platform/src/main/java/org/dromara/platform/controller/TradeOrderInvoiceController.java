package org.dromara.biz.trade.controller;

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
import org.dromara.biz.trade.domain.vo.TradeOrderInvoiceVo;
import org.dromara.biz.trade.domain.bo.TradeOrderInvoiceBo;
import org.dromara.biz.trade.service.ITradeOrderInvoiceService;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 订单信息
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/trade/order/invoice")
public class TradeOrderInvoiceController extends BaseController {

    private final ITradeOrderInvoiceService tradeOrderInvoiceService;

    /**
     * 查询订单信息列表
     */
    @SaCheckPermission("trade:order:invoice:list")
    @GetMapping("/list")
    public TableDataInfo<TradeOrderInvoiceVo> list(TradeOrderInvoiceBo bo, PageQuery pageQuery) {
        return tradeOrderInvoiceService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出订单信息列表
     */
    @SaCheckPermission("trade:order:invoice:export")
    @Log(title = "订单信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TradeOrderInvoiceBo bo, HttpServletResponse response) {
        List<TradeOrderInvoiceVo> list = tradeOrderInvoiceService.queryList(bo);
        ExcelUtil.exportExcel(list, "订单信息", TradeOrderInvoiceVo.class, response);
    }

    /**
     * 获取订单信息详细信息
     *
     * @param invoiceId 主键
     */
    @SaCheckPermission("trade:order:invoice:query")
    @GetMapping("/{invoiceId}")
    public R<TradeOrderInvoiceVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable Long invoiceId) {
        return R.ok(tradeOrderInvoiceService.queryById(invoiceId));
    }

    /**
     * 新增订单信息
     */
    @SaCheckPermission("trade:order:invoice:add")
    @Log(title = "订单信息", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TradeOrderInvoiceBo bo) {
        return toAjax(tradeOrderInvoiceService.insertByBo(bo));
    }

    /**
     * 修改订单信息
     */
    @SaCheckPermission("trade:order:invoice:edit")
    @Log(title = "订单信息", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TradeOrderInvoiceBo bo) {
        return toAjax(tradeOrderInvoiceService.updateByBo(bo));
    }

    /**
     * 删除订单信息
     *
     * @param invoiceIds 主键串
     */
    @SaCheckPermission("trade:order:invoice:remove")
    @Log(title = "订单信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{invoiceIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable Long[] invoiceIds) {
        return toAjax(tradeOrderInvoiceService.deleteWithValidByIds(List.of(invoiceIds), true));
    }
}
