package org.dromara.biz.admin.service.thoughts;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.admin.domain.vo.thoughts.ThotChannelThoughtVo;
import org.dromara.basal.thoughts.domain.ThotChannelThought;
import org.dromara.basal.thoughts.mapper.ThotChannelThoughtMapper;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 思绪专题关联信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotChannelThoughtService
    implements IBaseService<ThotChannelThought, ThotChannelThoughtVo, ThotChannelThought> {

    private final ThotChannelThoughtMapper thotChannelThoughtMapper;

    @Override
    public IBaseMapper<ThotChannelThought> mapper() {
        return thotChannelThoughtMapper;
    }

    public List<ThotChannelThought> queryByChannelId(Long channelId) {
        ThotChannelThought thotChannelThought = new ThotChannelThought();
        thotChannelThought.setChannelId(channelId);
        LambdaQueryWrapper<ThotChannelThought> lqw = buildQueryWrapper(thotChannelThought);
        return thotChannelThoughtMapper.selectList(lqw);
    }

    public List<ThotChannelThought> queryByThoughtId(Long thoughtId) {
        ThotChannelThought thotChannelThought = new ThotChannelThought();
        thotChannelThought.setThoughtId(thoughtId);
        LambdaQueryWrapper<ThotChannelThought> lqw = buildQueryWrapper(thotChannelThought);
        return thotChannelThoughtMapper.selectList(lqw);
    }

    @Override
    public LambdaQueryWrapper<ThotChannelThought> buildQueryWrapper(ThotChannelThought thotChannelThought) {
        LambdaQueryWrapper<ThotChannelThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotChannelThought.getChannelId() != null, ThotChannelThought::getChannelId,
            thotChannelThought.getChannelId());
        lqw.eq(thotChannelThought.getThoughtId() != null, ThotChannelThought::getThoughtId,
            thotChannelThought.getThoughtId());
        return lqw;
    }

}
