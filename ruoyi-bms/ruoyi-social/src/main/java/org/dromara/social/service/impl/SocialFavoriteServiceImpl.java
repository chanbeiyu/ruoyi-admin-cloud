package org.dromara.social.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.social.domain.bo.SocialFavoriteBo;
import org.dromara.social.domain.vo.SocialFavoriteVo;
import org.dromara.social.domain.SocialFavorite;
import org.dromara.social.mapper.SocialFavoriteMapper;
import org.dromara.social.service.ISocialFavoriteService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 收藏信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialFavoriteServiceImpl implements ISocialFavoriteService {

    private final SocialFavoriteMapper baseMapper;

    /**
     * 查询收藏信息
     */
    @Override
    public SocialFavoriteVo queryById(Long favoriteId){
        return baseMapper.selectVoById(favoriteId);
    }

    /**
     * 查询收藏信息列表
     */
    @Override
    public TableDataInfo<SocialFavoriteVo> queryPageList(SocialFavoriteBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialFavorite> lqw = buildQueryWrapper(bo);
        Page<SocialFavoriteVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询收藏信息列表
     */
    @Override
    public List<SocialFavoriteVo> queryList(SocialFavoriteBo bo) {
        LambdaQueryWrapper<SocialFavorite> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialFavorite> buildQueryWrapper(SocialFavoriteBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialFavorite> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialFavorite::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, SocialFavorite::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialFavorite::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getSubjectId() != null, SocialFavorite::getSubjectId, bo.getSubjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetId()), SocialFavorite::getTargetId, bo.getTargetId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetContent()), SocialFavorite::getTargetContent, bo.getTargetContent());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SocialFavorite::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增收藏信息
     */
    @Override
    public Boolean insertByBo(SocialFavoriteBo bo) {
        SocialFavorite add = MapstructUtils.convert(bo, SocialFavorite.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFavoriteId(add.getFavoriteId());
        }
        return flag;
    }

    /**
     * 修改收藏信息
     */
    @Override
    public Boolean updateByBo(SocialFavoriteBo bo) {
        SocialFavorite update = MapstructUtils.convert(bo, SocialFavorite.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialFavorite entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除收藏信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
