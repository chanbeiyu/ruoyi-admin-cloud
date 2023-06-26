package org.dromara.alkaid.service.impl;

import org.dromara.alkaid.domain.SpeechBilling;
import org.dromara.alkaid.domain.bo.SpeechBillingBo;
import org.dromara.alkaid.domain.vo.SpeechBillingVo;
import org.dromara.alkaid.mapper.SpeechBillingMapper;
import org.dromara.alkaid.service.ISpeechBillingService;
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
 * 语音计费Service业务层处理
 *
 * @author beiyu
 */
@RequiredArgsConstructor
@Service
public class SpeechBillingServiceImpl implements ISpeechBillingService {

    private final SpeechBillingMapper baseMapper;

    /**
     * 查询语音计费
     */
    @Override
    public SpeechBillingVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询语音计费列表
     */
    @Override
    public TableDataInfo<SpeechBillingVo> queryPageList(SpeechBillingBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SpeechBilling> lqw = buildQueryWrapper(bo);
        Page<SpeechBillingVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询语音计费列表
     */
    @Override
    public List<SpeechBillingVo> queryList(SpeechBillingBo bo) {
        LambdaQueryWrapper<SpeechBilling> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SpeechBilling> buildQueryWrapper(SpeechBillingBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SpeechBilling> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, SpeechBilling::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getAppCode()), SpeechBilling::getAppCode, bo.getAppCode());
        lqw.eq(bo.getCreateDept() != null, SpeechBilling::getCreateDept, bo.getCreateDept());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            SpeechBilling::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增语音计费
     */
    @Override
    public Boolean insertByBo(SpeechBillingBo bo) {
        SpeechBilling add = MapstructUtils.convert(bo, SpeechBilling.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改语音计费
     */
    @Override
    public Boolean updateByBo(SpeechBillingBo bo) {
        SpeechBilling update = MapstructUtils.convert(bo, SpeechBilling.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SpeechBilling entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除语音计费
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
