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
import org.dromara.basal.platform.domain.trade.bo.TradeOrderInvoiceBo;
import org.dromara.basal.platform.domain.trade.vo.TradeOrderInvoiceVo;
import org.dromara.basal.platform.domain.trade.TradeOrderInvoice;
import org.dromara.basal.platform.mapper.trade.TradeOrderInvoiceMapper;
import org.dromara.basal.platform.service.trade.ITradeOrderInvoiceService;

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
public class TradeOrderInvoiceServiceImpl implements ITradeOrderInvoiceService {

    private final TradeOrderInvoiceMapper baseMapper;

    /**
     * 查询订单信息
     */
    @Override
    public TradeOrderInvoiceVo queryById(Long invoiceId){
        return baseMapper.selectVoById(invoiceId);
    }

    /**
     * 查询订单信息列表
     */
    @Override
    public TableDataInfo<TradeOrderInvoiceVo> queryPageList(TradeOrderInvoiceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TradeOrderInvoice> lqw = buildQueryWrapper(bo);
        Page<TradeOrderInvoiceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询订单信息列表
     */
    @Override
    public List<TradeOrderInvoiceVo> queryList(TradeOrderInvoiceBo bo) {
        LambdaQueryWrapper<TradeOrderInvoice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TradeOrderInvoice> buildQueryWrapper(TradeOrderInvoiceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrderInvoice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, TradeOrderInvoice::getAppId, bo.getAppId());
        lqw.in(bo.getAppIds() != null, TradeOrderInvoice::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, TradeOrderInvoice::getMemberId, bo.getMemberId());
        lqw.like(bo.getOrderId() != null, TradeOrderInvoice::getOrderId, bo.getOrderId());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrderInvoice::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getInvoiceType() != null, TradeOrderInvoice::getInvoiceType, bo.getInvoiceType());
        lqw.like(StringUtils.isNotBlank(bo.getInvoiceHeader()), TradeOrderInvoice::getInvoiceHeader, bo.getInvoiceHeader());
        lqw.eq(bo.getStatus() != null, TradeOrderInvoice::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginBuildTime"), params.get("endBuildTime")),
            TradeOrderInvoice::getBuildTime ,params.get("beginBuildTime"), params.get("endBuildTime"));
        return lqw;
    }

    /**
     * 新增订单信息
     */
    @Override
    public Boolean insertByBo(TradeOrderInvoiceBo bo) {
        TradeOrderInvoice add = MapstructUtils.convert(bo, TradeOrderInvoice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setInvoiceId(add.getInvoiceId());
        }
        return flag;
    }

    /**
     * 修改订单信息
     */
    @Override
    public Boolean updateByBo(TradeOrderInvoiceBo bo) {
        TradeOrderInvoice update = MapstructUtils.convert(bo, TradeOrderInvoice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TradeOrderInvoice entity){
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
