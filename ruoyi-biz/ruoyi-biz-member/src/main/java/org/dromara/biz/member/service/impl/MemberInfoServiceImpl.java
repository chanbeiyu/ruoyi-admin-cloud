package org.dromara.biz.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.biz.member.domain.bo.MemberInfoBo;
import org.dromara.biz.member.domain.vo.MemberInfoVo;
import org.dromara.biz.member.domain.MemberInfo;
import org.dromara.biz.member.mapper.MemberInfoMapper;
import org.dromara.biz.member.service.IMemberInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 成员信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberInfoServiceImpl implements IMemberInfoService {

    private final MemberInfoMapper baseMapper;

    /**
     * 查询成员信息
     */
    @Override
    public MemberInfoVo queryById(Long memberId){
        return baseMapper.selectVoById(memberId);
    }

    /**
     * 查询成员信息列表
     */
    @Override
    public TableDataInfo<MemberInfoVo> queryPageList(MemberInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberInfo> lqw = buildQueryWrapper(bo);
        Page<MemberInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询成员信息列表
     */
    @Override
    public List<MemberInfoVo> queryList(MemberInfoBo bo) {
        LambdaQueryWrapper<MemberInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberInfo> buildQueryWrapper(MemberInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getUnionId()), MemberInfo::getUnionId, bo.getUnionId());
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberInfo::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getTypeId()), MemberInfo::getTypeId, bo.getTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getNickName()), MemberInfo::getNickName, bo.getNickName());
        lqw.eq(bo.getStatus() != null, MemberInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增成员信息
     */
    @Override
    public Boolean insertByBo(MemberInfoBo bo) {
        MemberInfo add = MapstructUtils.convert(bo, MemberInfo.class);
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
    public Boolean updateByBo(MemberInfoBo bo) {
        MemberInfo update = MapstructUtils.convert(bo, MemberInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 修改状态
     *
     * @return 结果
     */
    @Override
    public int updateStatus(Long appId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<MemberInfo>()
                .set(MemberInfo::getStatus, status)
                .eq(MemberInfo::getMemberId, appId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberInfo entity){
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
