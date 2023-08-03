package org.dromara.biz.trade.service;

import org.dromara.biz.trade.domain.TradeOrder;
import org.dromara.biz.trade.domain.vo.TradeOrderVo;
import org.dromara.biz.trade.domain.bo.TradeOrderBo;
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
public interface ITradeOrderService {

    /**
     * 查询订单信息
     */
    TradeOrderVo queryById(Long orderId);

    /**
     * 查询订单信息列表
     */
    TableDataInfo<TradeOrderVo> queryPageList(TradeOrderBo bo, PageQuery pageQuery);

    /**
     * 查询订单信息列表
     */
    List<TradeOrderVo> queryList(TradeOrderBo bo);

    /**
     * 新增订单信息
     */
    Boolean insertByBo(TradeOrderBo bo);

    /**
     * 修改订单信息
     */
    Boolean updateByBo(TradeOrderBo bo);

    /**
     * 校验并批量删除订单信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
