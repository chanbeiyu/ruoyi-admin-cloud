package org.dromara.alkaid.service.impl;

import org.dromara.alkaid.domain.SpeechOrder;
import org.dromara.alkaid.domain.bo.SpeechOrderBo;
import org.dromara.alkaid.domain.vo.SpeechOrderVo;
import org.dromara.alkaid.mapper.SpeechOrderMapper;
import org.dromara.alkaid.service.ISpeechOrderService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 语音订单Service业务层处理
 *
 * @author beiyu
 */
@RequiredArgsConstructor
@Service
public class SpeechOrderServiceImpl implements ISpeechOrderService {

    private final SpeechOrderMapper baseMapper;

    /**
     * 查询语音订单
     */
    @Override
    public SpeechOrderVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询语音订单列表
     */
    @Override
    public TableDataInfo<SpeechOrderVo> queryPageList(SpeechOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SpeechOrder> lqw = buildQueryWrapper(bo);
        Page<SpeechOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询语音订单列表
     */
    @Override
    public List<SpeechOrderVo> queryList(SpeechOrderBo bo) {
        LambdaQueryWrapper<SpeechOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SpeechOrder> buildQueryWrapper(SpeechOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SpeechOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, SpeechOrder::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getAppCode()), SpeechOrder::getAppCode, bo.getAppCode());
        lqw.eq(bo.getClient() != null, SpeechOrder::getClient, bo.getClient());
        lqw.eq(bo.getPlatform() != null, SpeechOrder::getPlatform, bo.getPlatform());
        lqw.like(StringUtils.isNotBlank(bo.getOrderId()), SpeechOrder::getOrderId, bo.getOrderId());
        lqw.eq(bo.getStatus() != null, SpeechOrder::getStatus, bo.getStatus());
        lqw.like(StringUtils.isNotBlank(bo.getPaymentSubject()), SpeechOrder::getPaymentSubject, bo.getPaymentSubject());
        lqw.eq(bo.getPaymentPlatform() != null, SpeechOrder::getPaymentPlatform, bo.getPaymentPlatform());
        lqw.eq(StringUtils.isNotBlank(bo.getPaymentOrder()), SpeechOrder::getPaymentOrder, bo.getPaymentOrder());
        lqw.between(params.get("beginPaymentTime") != null && params.get("endPaymentTime") != null,
            SpeechOrder::getPaymentTime, params.get("beginPaymentTime"), params.get("endPaymentTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getPaymentRemark()), SpeechOrder::getPaymentRemark, bo.getPaymentRemark());
        return lqw;
    }

    /**
     * 新增语音订单
     */
    @Override
    public Boolean insertByBo(SpeechOrderBo bo) {
        SpeechOrder add = MapstructUtils.convert(bo, SpeechOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改语音订单
     */
    @Override
    public Boolean updateByBo(SpeechOrderBo bo) {
        SpeechOrder update = MapstructUtils.convert(bo, SpeechOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SpeechOrder entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除语音订单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
