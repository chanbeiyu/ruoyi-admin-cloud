package org.dromara.basal.platform.service.member.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.member.bo.MemberPointsBo;
import org.dromara.basal.platform.domain.member.vo.MemberPointsVo;
import org.dromara.basal.platform.domain.member.MemberPoints;
import org.dromara.basal.platform.mapper.member.MemberPointsMapper;
import org.dromara.basal.platform.service.member.IMemberPointsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

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
    public MemberPointsVo queryById(Long id) {
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
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberPoints::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getMemberId()), MemberPoints::getMemberId, bo.getMemberId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), MemberPoints::getAppId, bo.getAppIds());
        lqw.in(CollectionUtils.isNotEmpty(bo.getMemberTypeIds()), MemberPoints::getMemberTypeId, bo.getMemberTypeIds());
        lqw.eq(Objects.nonNull(bo.getMemberTypeId()), MemberPoints::getMemberTypeId, bo.getMemberTypeId());
        lqw.eq(Objects.nonNull(bo.getStatus()), MemberPoints::getStatus, bo.getStatus());
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
     * 修改状态
     *
     * @return 结果
     */
    @Override
    public int updateStatus(Long appId, Integer status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<MemberPoints>()
                .set(MemberPoints::getStatus, status)
                .eq(MemberPoints::getId, appId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberPoints entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员积分
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
