package org.dromara.basal.platform.service.member.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.member.bo.MemberTypeRelatedBo;
import org.dromara.basal.platform.domain.member.vo.MemberTypeRelatedVo;
import org.dromara.basal.platform.domain.member.MemberTypeRelated;
import org.dromara.basal.platform.mapper.member.MemberTypeRelatedMapper;
import org.dromara.basal.platform.service.member.IMemberTypeRelatedService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员类型关联信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberTypeRelatedServiceImpl implements IMemberTypeRelatedService {

    private final MemberTypeRelatedMapper baseMapper;

    /**
     * 查询会员类型关联信息
     */
    @Override
    public MemberTypeRelatedVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询会员类型关联信息列表
     */
    @Override
    public TableDataInfo<MemberTypeRelatedVo> queryPageList(MemberTypeRelatedBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberTypeRelated> lqw = buildQueryWrapper(bo);
        Page<MemberTypeRelatedVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员类型关联信息列表
     */
    @Override
    public List<MemberTypeRelatedVo> queryList(MemberTypeRelatedBo bo) {
        LambdaQueryWrapper<MemberTypeRelated> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberTypeRelated> buildQueryWrapper(MemberTypeRelatedBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberTypeRelated> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMemberId() != null, MemberTypeRelated::getMemberId, bo.getMemberId());
        lqw.eq(bo.getMemberTypeId() != null, MemberTypeRelated::getMemberTypeId, bo.getMemberTypeId());
        return lqw;
    }

    /**
     * 新增会员类型关联信息
     */
    @Override
    public Boolean insertByBo(MemberTypeRelatedBo bo) {
        MemberTypeRelated add = MapstructUtils.convert(bo, MemberTypeRelated.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改会员类型关联信息
     */
    @Override
    public Boolean updateByBo(MemberTypeRelatedBo bo) {
        MemberTypeRelated update = MapstructUtils.convert(bo, MemberTypeRelated.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberTypeRelated entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员类型关联信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
