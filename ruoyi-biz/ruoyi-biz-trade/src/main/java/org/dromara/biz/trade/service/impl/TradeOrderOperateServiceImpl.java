package org.dromara.biz.trade.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.biz.trade.domain.bo.TradeOrderOperateBo;
import org.dromara.biz.trade.domain.vo.TradeOrderOperateVo;
import org.dromara.biz.trade.domain.TradeOrderOperate;
import org.dromara.biz.trade.mapper.TradeOrderOperateMapper;
import org.dromara.biz.trade.service.ITradeOrderOperateService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 订单操作历史记录Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class TradeOrderOperateServiceImpl implements ITradeOrderOperateService {

    private final TradeOrderOperateMapper baseMapper;

    /**
     * 查询订单操作历史记录
     */
    @Override
    public TradeOrderOperateVo queryById(Long operateId){
        return baseMapper.selectVoById(operateId);
    }

    /**
     * 查询订单操作历史记录列表
     */
    @Override
    public TableDataInfo<TradeOrderOperateVo> queryPageList(TradeOrderOperateBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<TradeOrderOperate> lqw = buildQueryWrapper(bo);
        Page<TradeOrderOperateVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询订单操作历史记录列表
     */
    @Override
    public List<TradeOrderOperateVo> queryList(TradeOrderOperateBo bo) {
        LambdaQueryWrapper<TradeOrderOperate> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<TradeOrderOperate> buildQueryWrapper(TradeOrderOperateBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<TradeOrderOperate> lqw = Wrappers.lambdaQuery();
        lqw.like(bo.getOrderId() != null, TradeOrderOperate::getOrderId, bo.getOrderId());
        lqw.like(StringUtils.isNotBlank(bo.getOrderNo()), TradeOrderOperate::getOrderNo, bo.getOrderNo());
        return lqw;
    }

    /**
     * 新增订单操作历史记录
     */
    @Override
    public Boolean insertByBo(TradeOrderOperateBo bo) {
        TradeOrderOperate add = MapstructUtils.convert(bo, TradeOrderOperate.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setOperateId(add.getOperateId());
        }
        return flag;
    }

    /**
     * 修改订单操作历史记录
     */
    @Override
    public Boolean updateByBo(TradeOrderOperateBo bo) {
        TradeOrderOperate update = MapstructUtils.convert(bo, TradeOrderOperate.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(TradeOrderOperate entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除订单操作历史记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
