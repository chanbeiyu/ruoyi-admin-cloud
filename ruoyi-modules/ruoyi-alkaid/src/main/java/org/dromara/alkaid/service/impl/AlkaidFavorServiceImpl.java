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
import org.dromara.alkaid.domain.bo.AlkaidFavorBo;
import org.dromara.alkaid.domain.vo.AlkaidFavorVo;
import org.dromara.alkaid.domain.AlkaidFavor;
import org.dromara.alkaid.mapper.AlkaidFavorMapper;
import org.dromara.alkaid.service.IAlkaidFavorService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 图集收藏信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidFavorServiceImpl implements IAlkaidFavorService {

    private final AlkaidFavorMapper baseMapper;

    /**
     * 查询图集收藏信息
     */
    @Override
    public AlkaidFavorVo queryById(Long favorId){
        return baseMapper.selectVoById(favorId);
    }

    /**
     * 查询图集收藏信息列表
     */
    @Override
    public TableDataInfo<AlkaidFavorVo> queryPageList(AlkaidFavorBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlkaidFavor> lqw = buildQueryWrapper(bo);
        Page<AlkaidFavorVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询图集收藏信息列表
     */
    @Override
    public List<AlkaidFavorVo> queryList(AlkaidFavorBo bo) {
        LambdaQueryWrapper<AlkaidFavor> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidFavor> buildQueryWrapper(AlkaidFavorBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidFavor> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAlbumId() != null, AlkaidFavor::getAlbumId, bo.getAlbumId());
        lqw.eq(bo.getFavorUserId() != null, AlkaidFavor::getFavorUserId, bo.getFavorUserId());
        lqw.eq(bo.getAlbumUserId() != null, AlkaidFavor::getAlbumUserId, bo.getAlbumUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getDescribe()), AlkaidFavor::getDescribe, bo.getDescribe());
        return lqw;
    }

    /**
     * 新增图集收藏信息
     */
    @Override
    public Boolean insertByBo(AlkaidFavorBo bo) {
        AlkaidFavor add = MapstructUtils.convert(bo, AlkaidFavor.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFavorId(add.getFavorId());
        }
        return flag;
    }

    /**
     * 修改图集收藏信息
     */
    @Override
    public Boolean updateByBo(AlkaidFavorBo bo) {
        AlkaidFavor update = MapstructUtils.convert(bo, AlkaidFavor.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidFavor entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除图集收藏信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
