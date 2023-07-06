package org.dromara.platform.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.vo.ThotTopicAttendVo;
import org.dromara.platform.domain.ThotTopicAttend;
import org.dromara.platform.mapper.ThotTopicAttendMapper;
import org.dromara.platform.service.IThotTopicAttendService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 话题参与Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotTopicAttendServiceImpl implements IThotTopicAttendService {

    private final ThotTopicAttendMapper baseMapper;

    /**
     * 查询话题参与
     */
    @Override
    public ThotTopicAttendVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public List<ThotTopicAttend> queryByTopicId(Long topicId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setTopicId(topicId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return baseMapper.selectList(lqw);
    }

    @Override
    public List<ThotTopicAttend> queryByAlbumId(Long albumId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setAlbumId(albumId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return baseMapper.selectList(lqw);
    }

    @Override
    public List<ThotTopicAttend> queryByThoughtId(Long thoughtId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setThoughtId(thoughtId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return baseMapper.selectList(lqw);
    }

    @Override
    public List<ThotTopicAttend> queryByAttendId(Long attendId) {
        ThotTopicAttend thotTopicAttend = new ThotTopicAttend();
        thotTopicAttend.setAttendId(attendId);
        LambdaQueryWrapper<ThotTopicAttend> lqw = buildQueryWrapper(thotTopicAttend);
        return baseMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<ThotTopicAttend> buildQueryWrapper(ThotTopicAttend thotTopicAttend) {
        LambdaQueryWrapper<ThotTopicAttend> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotTopicAttend.getAttendId() != null, ThotTopicAttend::getAttendId, thotTopicAttend.getAttendId());
        lqw.eq(thotTopicAttend.getTopicId() != null, ThotTopicAttend::getTopicId, thotTopicAttend.getTopicId());
        lqw.eq(thotTopicAttend.getAlbumId() != null, ThotTopicAttend::getAlbumId, thotTopicAttend.getAlbumId());
        lqw.eq(thotTopicAttend.getThoughtId() != null, ThotTopicAttend::getThoughtId, thotTopicAttend.getThoughtId());
        return lqw;
    }

    /**
     * 新增话题参与
     */
    @Override
    public Boolean insertByBo(ThotTopicAttend add) {
        validEntityBeforeSave(add);
        return baseMapper.insert(add) > 0;
    }

    /**
     * 修改话题参与
     */
    @Override
    public Boolean updateByBo(ThotTopicAttend update) {
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotTopicAttend entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除话题参与
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
