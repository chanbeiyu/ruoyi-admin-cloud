package org.dromara.biz.trade.service;

import org.dromara.biz.trade.domain.TradeOrderOperate;
import org.dromara.biz.trade.domain.vo.TradeOrderOperateVo;
import org.dromara.biz.trade.domain.bo.TradeOrderOperateBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 订单操作历史记录Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface ITradeOrderOperateService {

    /**
     * 查询订单操作历史记录
     */
    TradeOrderOperateVo queryById(Long operateId);

    /**
     * 查询订单操作历史记录列表
     */
    TableDataInfo<TradeOrderOperateVo> queryPageList(TradeOrderOperateBo bo, PageQuery pageQuery);

    /**
     * 查询订单操作历史记录列表
     */
    List<TradeOrderOperateVo> queryList(TradeOrderOperateBo bo);

    /**
     * 新增订单操作历史记录
     */
    Boolean insertByBo(TradeOrderOperateBo bo);

    /**
     * 修改订单操作历史记录
     */
    Boolean updateByBo(TradeOrderOperateBo bo);

    /**
     * 校验并批量删除订单操作历史记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
