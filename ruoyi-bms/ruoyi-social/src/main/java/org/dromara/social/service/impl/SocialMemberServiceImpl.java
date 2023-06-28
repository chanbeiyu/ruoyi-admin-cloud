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
import org.dromara.social.domain.bo.SocialMemberBo;
import org.dromara.social.domain.vo.SocialMemberVo;
import org.dromara.social.domain.SocialMember;
import org.dromara.social.mapper.SocialMemberMapper;
import org.dromara.social.service.ISocialMemberService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 成员信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialMemberServiceImpl implements ISocialMemberService {

    private final SocialMemberMapper baseMapper;

    /**
     * 查询成员信息
     */
    @Override
    public SocialMemberVo queryById(Long memberId){
        return baseMapper.selectVoById(memberId);
    }

    /**
     * 查询成员信息列表
     */
    @Override
    public TableDataInfo<SocialMemberVo> queryPageList(SocialMemberBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialMember> lqw = buildQueryWrapper(bo);
        Page<SocialMemberVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询成员信息列表
     */
    @Override
    public List<SocialMemberVo> queryList(SocialMemberBo bo) {
        LambdaQueryWrapper<SocialMember> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialMember> buildQueryWrapper(SocialMemberBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialMember> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUnionId()), SocialMember::getUnionId, bo.getUnionId());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialMember::getAppId, bo.getAppId());
        lqw.eq(bo.getVipLevel() != null, SocialMember::getVipLevel, bo.getVipLevel());
        lqw.eq(bo.getPoints() != null, SocialMember::getPoints, bo.getPoints());
        lqw.eq(bo.getPointsLevel() != null, SocialMember::getPointsLevel, bo.getPointsLevel());
        lqw.eq(bo.getStatus() != null, SocialMember::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增成员信息
     */
    @Override
    public Boolean insertByBo(SocialMemberBo bo) {
        SocialMember add = MapstructUtils.convert(bo, SocialMember.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMemberId(add.getMemberId());
        }
        return flag;
    }

    /**
     * 修改成员信息
     */
    @Override
    public Boolean updateByBo(SocialMemberBo bo) {
        SocialMember update = MapstructUtils.convert(bo, SocialMember.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialMember entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除成员信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
