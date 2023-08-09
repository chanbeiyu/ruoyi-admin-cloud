package org.dromara.basal.platform.service.trade.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.trade.bo.TradeOrderItemBo;
import org.dromara.basal.platform.domain.trade.vo.TradeOrderItemVo;
import org.dromara.basal.platform.domain.trade.TradeOrderItem;
import org.dromara.basal.platform.mapper.trade.TradeOrderItemMapper;
import org.dromara.basal.platform.service.trade.ITradeOrderItemService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单商品Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class TradeOrderItemServiceImpl implements ITradeOrderItemService {

    private final TradeOrderItemMapper baseMapper;

    /**
     * 查询订单商品
     */
    @Override
    public TradeOrderItemVo queryById(Long itemId){
        return baseMapper.selectVoById(itemId);
    }

    /**
     * 查询订单商品列表
     */
    @Override
    public TableDataInfo<TradeOrderItemVo> queryPageList(TradeOrderItemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TradeOrderItem> lqw = buildQueryWrapper(bo);
        Page<TradeOrderItemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询订单商品列表
     */
    @Override
    public List<TradeOrderItemVo> queryList(TradeOrderItemBo bo) {
        LambdaQueryWrapper<TradeOrderItem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TradeOrderItem> buildQueryWrapper(TradeOrderItemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrderItem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getOrderId() != null, TradeOrderItem::getOrderId, bo.getOrderId());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrderItem::getOrderNo, bo.getOrderNo());
        lqw.like(bo.getProductId() != null, TradeOrderItem::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), TradeOrderItem::getProductName, bo.getProductName());
        lqw.like(StringUtils.isNotBlank(bo.getProductBrand()), TradeOrderItem::getProductBrand, bo.getProductBrand());
        lqw.like(bo.getProductSkuId() != null, TradeOrderItem::getProductSkuId, bo.getProductSkuId());
        lqw.like(StringUtils.isNotBlank(bo.getProductSkuCode()), TradeOrderItem::getProductSkuCode, bo.getProductSkuCode());
        return lqw;
    }

    /**
     * 新增订单商品
     */
    @Override
    public Boolean insertByBo(TradeOrderItemBo bo) {
        TradeOrderItem add = MapstructUtils.convert(bo, TradeOrderItem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setItemId(add.getItemId());
        }
        return flag;
    }

    /**
     * 修改订单商品
     */
    @Override
    public Boolean updateByBo(TradeOrderItemBo bo) {
        TradeOrderItem update = MapstructUtils.convert(bo, TradeOrderItem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TradeOrderItem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除订单商品
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
