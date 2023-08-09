package org.dromara.basal.platform.service.member.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.dromara.basal.platform.domain.member.vo.MemberTypeVo;
import org.dromara.basal.platform.service.member.IMemberTypeService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.member.bo.MemberLevelBo;
import org.dromara.basal.platform.domain.member.vo.MemberLevelVo;
import org.dromara.basal.platform.domain.member.MemberLevel;
import org.dromara.basal.platform.mapper.member.MemberLevelMapper;
import org.dromara.basal.platform.service.member.IMemberLevelService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 会员级别信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberLevelServiceImpl implements IMemberLevelService {

    private final MemberLevelMapper baseMapper;
    private final IMemberTypeService memberTypeService;

    /**
     * 查询会员级别信息
     */
    @Override
    public MemberLevelVo queryById(Long levelId){
        return baseMapper.selectVoById(levelId);
    }

    /**
     * 查询会员级别信息列表
     */
    @Override
    public TableDataInfo<MemberLevelVo> queryPageList(MemberLevelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberLevel> lqw = buildQueryWrapper(bo);
        Page<MemberLevelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员级别信息列表
     */
    @Override
    public List<MemberLevelVo> queryList(MemberLevelBo bo) {
        LambdaQueryWrapper<MemberLevel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberLevel> buildQueryWrapper(MemberLevelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberLevel> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), MemberLevel::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getMemberTypeId()), MemberLevel::getMemberTypeId, bo.getMemberTypeId());
        lqw.like(StringUtils.isNotBlank(bo.getLevelCode()), MemberLevel::getLevelCode, bo.getLevelCode());
        lqw.like(StringUtils.isNotBlank(bo.getLevelName()), MemberLevel::getLevelName, bo.getLevelName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MemberLevel::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增会员级别信息
     */
    @Override
    public Boolean insertByBo(MemberLevelBo bo) {
        MemberLevel add = MapstructUtils.convert(bo, MemberLevel.class);
        MemberTypeVo memberTypeVo = memberTypeService.queryById(bo.getMemberTypeId());
        add.setAppId(memberTypeVo.getAppId());

        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setLevelId(add.getLevelId());
        }
        return flag;
    }

    /**
     * 修改会员级别信息
     */
    @Override
    public Boolean updateByBo(MemberLevelBo bo) {
        MemberLevel update = MapstructUtils.convert(bo, MemberLevel.class);
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
            new LambdaUpdateWrapper<MemberLevel>()
                .set(MemberLevel::getStatus, status)
                .eq(MemberLevel::getLevelId, appId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberLevel entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员级别信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
