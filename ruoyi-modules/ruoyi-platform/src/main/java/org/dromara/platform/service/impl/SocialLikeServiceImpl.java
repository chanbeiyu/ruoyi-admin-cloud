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
import org.dromara.platform.domain.SocialSubject;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.SocialLikeBo;
import org.dromara.platform.domain.vo.SocialLikeVo;
import org.dromara.platform.domain.SocialLike;
import org.dromara.platform.mapper.SocialLikeMapper;
import org.dromara.platform.service.ISocialLikeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 点赞信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialLikeServiceImpl implements ISocialLikeService {

    private final SocialLikeMapper baseMapper;

    /**
     * 查询点赞信息
     */
    @Override
    public SocialLikeVo queryById(Long likeId){
        return baseMapper.selectVoById(likeId);
    }

    /**
     * 查询点赞信息列表
     */
    @Override
    public TableDataInfo<SocialLikeVo> queryPageList(SocialLikeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialLike> lqw = buildQueryWrapper(bo);
        Page<SocialLikeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询点赞信息列表
     */
    @Override
    public List<SocialLikeVo> queryList(SocialLikeBo bo) {
        LambdaQueryWrapper<SocialLike> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialLike> buildQueryWrapper(SocialLikeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialLike> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialLike::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialLike::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialLike::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialLike::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getSubjectId() != null, SocialLike::getSubjectId, bo.getSubjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetId()), SocialLike::getTargetId, bo.getTargetId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetContent()), SocialLike::getTargetContent, bo.getTargetContent());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SocialLike::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增点赞信息
     */
    @Override
    public Boolean insertByBo(SocialLikeBo bo) {
        SocialLike add = MapstructUtils.convert(bo, SocialLike.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLikeId(add.getLikeId());
        }
        return flag;
    }

    /**
     * 修改点赞信息
     */
    @Override
    public Boolean updateByBo(SocialLikeBo bo) {
        SocialLike update = MapstructUtils.convert(bo, SocialLike.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialLike entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除点赞信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
