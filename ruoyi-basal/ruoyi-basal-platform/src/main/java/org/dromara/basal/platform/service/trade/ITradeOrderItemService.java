package org.dromara.basal.platform.service.trade;

import org.dromara.basal.platform.domain.trade.vo.TradeOrderItemVo;
import org.dromara.basal.platform.domain.trade.bo.TradeOrderItemBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单商品Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface ITradeOrderItemService {

    /**
     * 查询订单商品
     */
    TradeOrderItemVo queryById(Long itemId);

    /**
     * 查询订单商品列表
     */
    TableDataInfo<TradeOrderItemVo> queryPageList(TradeOrderItemBo bo, PageQuery pageQuery);

    /**
     * 查询订单商品列表
     */
    List<TradeOrderItemVo> queryList(TradeOrderItemBo bo);

    /**
     * 新增订单商品
     */
    Boolean insertByBo(TradeOrderItemBo bo);

    /**
     * 修改订单商品
     */
    Boolean updateByBo(TradeOrderItemBo bo);

    /**
     * 校验并批量删除订单商品信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
