package org.dromara.biz.trade.service;

import org.dromara.biz.trade.domain.TradeOrderInvoice;
import org.dromara.biz.trade.domain.vo.TradeOrderInvoiceVo;
import org.dromara.biz.trade.domain.bo.TradeOrderInvoiceBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface ITradeOrderInvoiceService {

    /**
     * 查询订单信息
     */
    TradeOrderInvoiceVo queryById(Long invoiceId);

    /**
     * 查询订单信息列表
     */
    TableDataInfo<TradeOrderInvoiceVo> queryPageList(TradeOrderInvoiceBo bo, PageQuery pageQuery);

    /**
     * 查询订单信息列表
     */
    List<TradeOrderInvoiceVo> queryList(TradeOrderInvoiceBo bo);

    /**
     * 新增订单信息
     */
    Boolean insertByBo(TradeOrderInvoiceBo bo);

    /**
     * 修改订单信息
     */
    Boolean updateByBo(TradeOrderInvoiceBo bo);

    /**
     * 校验并批量删除订单信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
