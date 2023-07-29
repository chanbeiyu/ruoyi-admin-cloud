package org.dromara.biz.thoughts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.thoughts.domain.ThotAlbumThought;
import org.dromara.biz.thoughts.domain.vo.ThotAlbumThoughtVo;
import org.dromara.biz.thoughts.mapper.ThotAlbumThoughtMapper;
import org.dromara.biz.thoughts.service.IThotAlbumThoughtService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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

    /**
     * 查询思集信息
     */
    @Override
    public ThotAlbumThoughtVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public ThotAlbumThoughtVo queryById(Long albumId, Long thoughtId) {
        return baseMapper.selectAlbumThought(albumId, thoughtId);
    }

    @Override
    public List<ThotAlbumThoughtVo> queryByAlbumId(Long albumId) {
        return baseMapper.selectAlbumThoughtByAlbumId(albumId);
    }

    @Override
    public List<ThotAlbumThoughtVo> queryByThoughtId(Long thoughtId) {
        return baseMapper.selectAlbumThoughtByThoughtId(thoughtId);
    }

    private LambdaQueryWrapper<ThotAlbumThought> buildQueryWrapper(ThotAlbumThought thotAlbumThought) {
        LambdaQueryWrapper<ThotAlbumThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(thotAlbumThought.getAlbumId() != null, ThotAlbumThought::getAlbumId, thotAlbumThought.getAlbumId());
        lqw.eq(thotAlbumThought.getThoughtId() != null, ThotAlbumThought::getThoughtId, thotAlbumThought.getThoughtId());
        lqw.eq(thotAlbumThought.getIsCover() != null, ThotAlbumThought::getIsCover, thotAlbumThought.getIsCover());
        return lqw;
    }

    @Override
    public Boolean insertBatch(List<ThotAlbumThought> adds) {
        return baseMapper.insertBatch(adds);
    }

    @Override
    public Boolean updateIsCover(Long id, String isCover) {
        ThotAlbumThought update = new ThotAlbumThought();
        update.setId(id);
        update.setIsCover(isCover);
        return baseMapper.updateById(update) > 0;
    }

    @Override
    public Boolean deleteByIds(Long albumId) {
        QueryWrapper<ThotAlbumThought> wrapper = new QueryWrapper<>();
        wrapper.eq("album_id", albumId);
        return baseMapper.delete(wrapper) > 0;
    }

    @Override
    public Boolean deleteByIds(Collection<Long> ids) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
