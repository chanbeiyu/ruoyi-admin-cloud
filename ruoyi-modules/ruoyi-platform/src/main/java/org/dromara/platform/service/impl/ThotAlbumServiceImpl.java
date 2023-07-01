package org.dromara.platform.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.ThotAlbumBo;
import org.dromara.platform.domain.vo.ThotAlbumVo;
import org.dromara.platform.domain.ThotAlbum;
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

    private LambdaQueryWrapper<ThotAlbum> buildQueryWrapper(ThotAlbumBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotAlbum> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotAlbum::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getAlbumTitle()), ThotAlbum::getAlbumTitle, bo.getAlbumTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ThotAlbum::getDescription, bo.getDescription());
        lqw.eq(bo.getStatus() != null, ThotAlbum::getStatus, bo.getStatus());
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
