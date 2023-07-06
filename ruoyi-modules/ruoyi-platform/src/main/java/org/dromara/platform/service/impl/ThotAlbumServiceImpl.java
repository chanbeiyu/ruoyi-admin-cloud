package org.dromara.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.*;
import org.dromara.platform.domain.vo.ThotThoughtVo;
import org.dromara.platform.service.IThotAlbumThoughtService;
import org.dromara.platform.service.IThotThoughtService;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.ThotAlbumBo;
import org.dromara.platform.domain.vo.ThotAlbumVo;
import org.dromara.platform.mapper.ThotAlbumMapper;
import org.dromara.platform.service.IThotAlbumService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 思集信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotAlbumServiceImpl implements IThotAlbumService {

    private final ThotAlbumMapper baseMapper;

    private final IThotAlbumThoughtService thotAlbumThoughtService;

    private final IThotThoughtService thotThoughtService;

    /**
     * 查询思集信息
     */
    @Override
    public ThotAlbumVo queryById(Long albumId){
        return baseMapper.selectVoById(albumId);
    }

    /**
     * 查询思集信息列表
     */
    @Override
    public TableDataInfo<ThotAlbumVo> queryPageList(ThotAlbumBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotAlbum> lqw = buildQueryWrapper(bo);
        Page<ThotAlbumVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询思集信息列表
     */
    @Override
    public List<ThotAlbumVo> queryList(ThotAlbumBo bo) {
        LambdaQueryWrapper<ThotAlbum> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询思集信息列表
     */
    @Override
    public TableDataInfo<ThotThoughtVo> queryAlbumThoughts(Long albumId) {
        ThotAlbumVo thotAlbumVo = baseMapper.selectVoById(albumId);
        List<ThotThoughtVo> thoughts = null;
        List<ThotAlbumThought> thotAlbumThoughts = thotAlbumThoughtService.queryByAlbumId(albumId);
        if(CollectionUtils.isNotEmpty(thotAlbumThoughts)) {
            thoughts = thotAlbumThoughts.parallelStream()
                .map(o -> {
                    ThotThoughtVo thotThoughtVo = thotThoughtService.queryById(o.getThoughtId());
                    thotThoughtVo.setAlbumId(o.getAlbumId());
                    thotThoughtVo.setAlbumTitle(thotAlbumVo.getAlbumTitle());
                    thotThoughtVo.setAlbumIsCover(o.getIsCover());
                    thotThoughtVo.setAlbumCreateTime(o.getCreateTime());
                    return thotThoughtVo;
                }).toList();
        }
        return TableDataInfo.build(thoughts);
    }

    private LambdaQueryWrapper<ThotAlbum> buildQueryWrapper(ThotAlbumBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotAlbum> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotAlbum::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotAlbum::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getAlbumTitle()), ThotAlbum::getAlbumTitle, bo.getAlbumTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ThotAlbum::getDescription, bo.getDescription());
        lqw.eq(bo.getStatus() != null, ThotAlbum::getStatus, bo.getStatus());
        lqw.between(params.get("beginPublishTime") != null && params.get("endPublishTime") != null,
            ThotAlbum::getPublishTime ,params.get("beginPublishTime"), params.get("endPublishTime"));
        return lqw;
    }

    /**
     * 新增思集信息
     */
    @Override
    public Boolean insertByBo(ThotAlbumBo bo) {
        ThotAlbum add = MapstructUtils.convert(bo, ThotAlbum.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAlbumId(add.getAlbumId());
        }
        return flag;
    }

    /**
     * 修改思集信息
     */
    @Override
    public Boolean updateByBo(ThotAlbumBo bo) {
        ThotAlbum update = MapstructUtils.convert(bo, ThotAlbum.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotAlbum entity){
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
