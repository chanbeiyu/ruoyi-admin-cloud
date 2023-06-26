package org.dromara.alkaid.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.alkaid.domain.bo.AlkaidAlbumBo;
import org.dromara.alkaid.domain.vo.AlkaidAlbumVo;
import org.dromara.alkaid.domain.AlkaidAlbum;
import org.dromara.alkaid.mapper.AlkaidAlbumMapper;
import org.dromara.alkaid.service.IAlkaidAlbumService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 图集信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidAlbumServiceImpl implements IAlkaidAlbumService {

    private final AlkaidAlbumMapper baseMapper;

    /**
     * 查询图集信息
     */
    @Override
    public AlkaidAlbumVo queryById(Long albumId){
        return baseMapper.selectVoById(albumId);
    }

    /**
     * 查询图集信息列表
     */
    @Override
    public TableDataInfo<AlkaidAlbumVo> queryPageList(AlkaidAlbumBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlkaidAlbum> lqw = buildQueryWrapper(bo);
        Page<AlkaidAlbumVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询图集信息列表
     */
    @Override
    public List<AlkaidAlbumVo> queryList(AlkaidAlbumBo bo) {
        LambdaQueryWrapper<AlkaidAlbum> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidAlbum> buildQueryWrapper(AlkaidAlbumBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidAlbum> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, AlkaidAlbum::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), AlkaidAlbum::getTitle, bo.getTitle());
        lqw.eq(bo.getCategory() != null, AlkaidAlbum::getCategory, bo.getCategory());
        lqw.eq(StringUtils.isNotBlank(bo.getTags()), AlkaidAlbum::getTags, bo.getTags());
        lqw.eq(StringUtils.isNotBlank(bo.getCoverImg()), AlkaidAlbum::getCoverImg, bo.getCoverImg());
        lqw.eq(StringUtils.isNotBlank(bo.getIsTop()), AlkaidAlbum::getIsTop, bo.getIsTop());
        lqw.eq(bo.getStatus() != null, AlkaidAlbum::getStatus, bo.getStatus());
        lqw.between(params.get("beginPublishTime") != null && params.get("endPublishTime") != null,
            AlkaidAlbum::getPublishTime ,params.get("beginPublishTime"), params.get("endPublishTime"));
        lqw.between(params.get("beginDeleteTime") != null && params.get("endDeleteTime") != null,
            AlkaidAlbum::getDeleteTime ,params.get("beginDeleteTime"), params.get("endDeleteTime"));
        return lqw;
    }

    /**
     * 新增图集信息
     */
    @Override
    public Boolean insertByBo(AlkaidAlbumBo bo) {
        AlkaidAlbum add = MapstructUtils.convert(bo, AlkaidAlbum.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAlbumId(add.getAlbumId());
        }
        return flag;
    }

    /**
     * 修改图集信息
     */
    @Override
    public Boolean updateByBo(AlkaidAlbumBo bo) {
        AlkaidAlbum update = MapstructUtils.convert(bo, AlkaidAlbum.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidAlbum entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除图集信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
