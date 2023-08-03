package org.dromara.biz.trade.service.impl;

import org.dromara.biz.trade.domain.TradeOrderInvoice;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.biz.trade.domain.bo.TradeOrderBo;
import org.dromara.biz.trade.domain.vo.TradeOrderVo;
import org.dromara.biz.trade.domain.TradeOrder;
import org.dromara.biz.trade.mapper.TradeOrderMapper;
import org.dromara.biz.trade.service.ITradeOrderService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class TradeOrderServiceImpl implements ITradeOrderService {

    private final TradeOrderMapper baseMapper;

    /**
     * 查询订单信息
     */
    @Override
    public TradeOrderVo queryById(Long orderId){
        return baseMapper.selectVoById(orderId);
    }

    /**
     * 查询订单信息列表
     */
    @Override
    public TableDataInfo<TradeOrderVo> queryPageList(TradeOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TradeOrder> lqw = buildQueryWrapper(bo);
        Page<TradeOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询订单信息列表
     */
    @Override
    public List<TradeOrderVo> queryList(TradeOrderBo bo) {
        LambdaQueryWrapper<TradeOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TradeOrder> buildQueryWrapper(TradeOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, TradeOrder::getAppId, bo.getAppId());
        lqw.in(bo.getAppIds() != null, TradeOrder::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, TradeOrder::getMemberId, bo.getMemberId());
        lqw.eq(bo.getOrderType() != null, TradeOrder::getOrderType, bo.getOrderType());
        lqw.eq(bo.getPaymentType() != null, TradeOrder::getPaymentType, bo.getPaymentType());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrder::getOrderNo, bo.getOrderNo());
        lqw.like(StringUtils.isNotBlank(bo.getPaymentNo()), TradeOrder::getPaymentNo, bo.getPaymentNo());
        lqw.eq(bo.getDeliveryId() != null, TradeOrder::getDeliveryId, bo.getDeliveryId());
        lqw.eq(bo.getStatus() != null, TradeOrder::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginPaymentTime"), params.get("endPaymentTime")),
            TradeOrder::getPaymentTime ,params.get("beginPaymentTime"), params.get("endPaymentTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginDeliveryTime"), params.get("endDeliveryTime")),
            TradeOrder::getDeliveryTime ,params.get("beginDeliveryTime"), params.get("endDeliveryTime"));
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginReceiverTime"), params.get("endReceiverTime")),
            TradeOrder::getReceiverTime ,params.get("beginReceiverTime"), params.get("endReceiverTime"));
        return lqw;
    }

    /**
     * 新增订单信息
     */
    @Override
    public Boolean insertByBo(TradeOrderBo bo) {
        TradeOrder add = MapstructUtils.convert(bo, TradeOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOrderId(add.getOrderId());
        }
        return flag;
    }

    /**
     * 修改订单信息
     */
    @Override
    public Boolean updateByBo(TradeOrderBo bo) {
        TradeOrder update = MapstructUtils.convert(bo, TradeOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TradeOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除订单信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
