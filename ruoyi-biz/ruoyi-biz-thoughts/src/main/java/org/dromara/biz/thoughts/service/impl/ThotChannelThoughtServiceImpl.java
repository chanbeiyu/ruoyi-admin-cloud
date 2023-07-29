package org.dromara.biz.thoughts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.biz.thoughts.domain.vo.ThotChannelThoughtVo;
import org.dromara.biz.thoughts.domain.ThotChannelThought;
import org.dromara.biz.thoughts.mapper.ThotChannelThoughtMapper;
import org.dromara.biz.thoughts.service.IThotChannelThoughtService;

import java.util.List;
import java.util.Collection;

/**
 * 思绪专题关联信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotChannelThoughtServiceImpl implements IThotChannelThoughtService {

    private final ThotChannelThoughtMapper baseMapper;

    /**
     * 查询思绪专题关联信息
     */
    @Override
    public ThotChannelThoughtVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public List<ThotChannelThought> queryByChannelId(Long channelId) {
        ThotChannelThought thotChannelThought = new ThotChannelThought();
        thotChannelThought.setChannelId(channelId);
        LambdaQueryWrapper<ThotChannelThought> lqw = buildQueryWrapper(thotChannelThought);
        return baseMapper.selectList(lqw);
    }

    @Override
    public List<ThotChannelThought> queryByThoughtId(Long thoughtId) {
        ThotChannelThought thotChannelThought = new ThotChannelThought();
        thotChannelThought.setThoughtId(thoughtId);
        LambdaQueryWrapper<ThotChannelThought> lqw = buildQueryWrapper(thotChannelThought);
        return baseMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<ThotChannelThought> buildQueryWrapper(ThotChannelThought thotChannelThought) {
        LambdaQueryWrapper<ThotChannelThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotChannelThought.getChannelId() != null, ThotChannelThought::getChannelId, thotChannelThought.getChannelId());
        lqw.eq(thotChannelThought.getThoughtId() != null, ThotChannelThought::getThoughtId, thotChannelThought.getThoughtId());
        return lqw;
    }

    /**
     * 新增思绪专题关联信息
     */
    @Override
    public Boolean insertByBo(ThotChannelThought add) {
        validEntityBeforeSave(add);
        return baseMapper.insert(add) > 0;
    }

    /**
     * 修改思绪专题关联信息
     */
    @Override
    public Boolean updateByBo(ThotChannelThought update) {
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotChannelThought entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除思绪专题关联信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
