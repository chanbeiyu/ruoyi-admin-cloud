package org.dromara.basal.platform.service.social.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.social.bo.SocialFollowBo;
import org.dromara.basal.platform.domain.social.vo.SocialFollowVo;
import org.dromara.basal.platform.domain.social.SocialFollow;
import org.dromara.basal.platform.mapper.social.SocialFollowMapper;
import org.dromara.basal.platform.service.social.ISocialFollowService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 关注信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialFollowServiceImpl implements ISocialFollowService {

    private final SocialFollowMapper baseMapper;

    /**
     * 查询关注信息
     */
    @Override
    public SocialFollowVo queryById(Long followId){
        return baseMapper.selectVoById(followId);
    }

    /**
     * 查询关注信息列表
     */
    @Override
    public TableDataInfo<SocialFollowVo> queryPageList(SocialFollowBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialFollow> lqw = buildQueryWrapper(bo);
        Page<SocialFollowVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询关注信息列表
     */
    @Override
    public List<SocialFollowVo> queryList(SocialFollowBo bo) {
        LambdaQueryWrapper<SocialFollow> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialFollow> buildQueryWrapper(SocialFollowBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialFollow> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialFollow::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialFollow::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialFollow::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialFollow::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getStatus() != null, SocialFollow::getStatus, bo.getStatus());
        lqw.eq(bo.getUnfollowTime() != null, SocialFollow::getUnfollowTime, bo.getUnfollowTime());
        return lqw;
    }

    /**
     * 新增关注信息
     */
    @Override
    public Boolean insertByBo(SocialFollowBo bo) {
        SocialFollow add = MapstructUtils.convert(bo, SocialFollow.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setFollowId(add.getFollowId());
        }
        return flag;
    }

    /**
     * 修改关注信息
     */
    @Override
    public Boolean updateByBo(SocialFollowBo bo) {
        SocialFollow update = MapstructUtils.convert(bo, SocialFollow.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialFollow entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除关注信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
