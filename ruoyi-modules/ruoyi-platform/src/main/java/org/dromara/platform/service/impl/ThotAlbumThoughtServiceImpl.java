package org.dromara.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.vo.ThotThoughtVo;
import org.dromara.platform.service.IThotThoughtService;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.vo.ThotAlbumThoughtVo;
import org.dromara.platform.domain.ThotAlbumThought;
import org.dromara.platform.mapper.ThotAlbumThoughtMapper;
import org.dromara.platform.service.IThotAlbumThoughtService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 思集信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
@RequiredArgsConstructor
@Service
public class ThotAlbumThoughtServiceImpl implements IThotAlbumThoughtService {

    private final ThotAlbumThoughtMapper baseMapper;

    private final IThotThoughtService thotThoughtService;

    /**
     * 查询思集信息
     */
    @Override
    public ThotAlbumThoughtVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    @Override
    public List<ThotAlbumThought> queryByAlbumId(Long albumId){
        ThotAlbumThought thotAlbumThought = new ThotAlbumThought();
        thotAlbumThought.setAlbumId(albumId);
        LambdaQueryWrapper<ThotAlbumThought> lqw = buildQueryWrapper(thotAlbumThought);
        return baseMapper.selectList(lqw);
    }

    @Override
    public List<ThotAlbumThought> queryByThoughtId(Long thoughtId){
        ThotAlbumThought thotAlbumThought = new ThotAlbumThought();
        thotAlbumThought.setThoughtId(thoughtId);
        LambdaQueryWrapper<ThotAlbumThought> lqw = buildQueryWrapper(thotAlbumThought);
        return baseMapper.selectList(lqw);
    }

    private LambdaQueryWrapper<ThotAlbumThought> buildQueryWrapper(ThotAlbumThought thotAlbumThought) {
        LambdaQueryWrapper<ThotAlbumThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotAlbumThought.getAlbumId() != null, ThotAlbumThought::getAlbumId, thotAlbumThought.getAlbumId());
        lqw.eq(thotAlbumThought.getThoughtId() != null, ThotAlbumThought::getThoughtId, thotAlbumThought.getThoughtId());
        lqw.eq(thotAlbumThought.getIsCover() != null, ThotAlbumThought::getIsCover, thotAlbumThought.getIsCover());
        return lqw;
    }

    /**
     * 新增思集信息
     */
    @Override
    public Boolean insertByBo(ThotAlbumThought add) {
        validEntityBeforeSave(add);
        return baseMapper.insert(add) > 0;
    }

    /**
     * 修改思集信息
     */
    @Override
    public Boolean updateByBo(ThotAlbumThought update) {
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotAlbumThought entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除思集信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
