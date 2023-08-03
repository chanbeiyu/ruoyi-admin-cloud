package org.dromara.biz.member.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.biz.member.domain.bo.MemberPointsBo;
import org.dromara.biz.member.domain.vo.MemberPointsVo;
import org.dromara.biz.member.domain.MemberPoints;
import org.dromara.biz.member.mapper.MemberPointsMapper;
import org.dromara.biz.member.service.IMemberPointsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员积分Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberPointsServiceImpl implements IMemberPointsService {

    private final MemberPointsMapper baseMapper;

    /**
     * 查询会员积分
     */
    @Override
    public MemberPointsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询会员积分列表
     */
    @Override
    public TableDataInfo<MemberPointsVo> queryPageList(MemberPointsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberPoints> lqw = buildQueryWrapper(bo);
        Page<MemberPointsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员积分列表
     */
    @Override
    public List<MemberPointsVo> queryList(MemberPointsBo bo) {
        LambdaQueryWrapper<MemberPoints> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberPoints> buildQueryWrapper(MemberPointsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberPoints> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberPoints::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, MemberPoints::getMemberId, bo.getMemberId());
        lqw.eq(bo.getMemberTypeId() != null, MemberPoints::getMemberTypeId, bo.getMemberTypeId());
        lqw.eq(bo.getTotalPoints() != null, MemberPoints::getTotalPoints, bo.getTotalPoints());
        lqw.eq(bo.getLastLevel() != null, MemberPoints::getLastLevel, bo.getLastLevel());
        lqw.eq(bo.getExpiredDate() != null, MemberPoints::getExpiredDate, bo.getExpiredDate());
        lqw.eq(bo.getStatus() != null, MemberPoints::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增会员积分
     */
    @Override
    public Boolean insertByBo(MemberPointsBo bo) {
        MemberPoints add = MapstructUtils.convert(bo, MemberPoints.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会员积分
     */
    @Override
    public Boolean updateByBo(MemberPointsBo bo) {
        MemberPoints update = MapstructUtils.convert(bo, MemberPoints.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberPoints entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员积分
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
