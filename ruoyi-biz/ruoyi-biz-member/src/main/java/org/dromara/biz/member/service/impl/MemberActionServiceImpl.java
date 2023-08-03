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
import org.dromara.biz.member.domain.bo.MemberActionBo;
import org.dromara.biz.member.domain.vo.MemberActionVo;
import org.dromara.biz.member.domain.MemberAction;
import org.dromara.biz.member.mapper.MemberActionMapper;
import org.dromara.biz.member.service.IMemberActionService;

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
public class MemberActionServiceImpl implements IMemberActionService {

    private final MemberActionMapper baseMapper;

    /**
     * 查询会员积分
     */
    @Override
    public MemberActionVo queryById(Long actionId){
        return baseMapper.selectVoById(actionId);
    }

    /**
     * 查询会员积分列表
     */
    @Override
    public TableDataInfo<MemberActionVo> queryPageList(MemberActionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberAction> lqw = buildQueryWrapper(bo);
        Page<MemberActionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员积分列表
     */
    @Override
    public List<MemberActionVo> queryList(MemberActionBo bo) {
        LambdaQueryWrapper<MemberAction> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberAction> buildQueryWrapper(MemberActionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberAction> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberAction::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getActionCode()), MemberAction::getActionCode, bo.getActionCode());
        lqw.like(StringUtils.isNotBlank(bo.getActionName()), MemberAction::getActionName, bo.getActionName());
        lqw.eq(bo.getStatus() != null, MemberAction::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增会员积分
     */
    @Override
    public Boolean insertByBo(MemberActionBo bo) {
        MemberAction add = MapstructUtils.convert(bo, MemberAction.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActionId(add.getActionId());
        }
        return flag;
    }

    /**
     * 修改会员积分
     */
    @Override
    public Boolean updateByBo(MemberActionBo bo) {
        MemberAction update = MapstructUtils.convert(bo, MemberAction.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberAction entity){
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
