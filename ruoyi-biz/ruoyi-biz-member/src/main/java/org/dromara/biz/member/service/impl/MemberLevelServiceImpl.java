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
import org.dromara.biz.member.domain.bo.MemberLevelBo;
import org.dromara.biz.member.domain.vo.MemberLevelVo;
import org.dromara.biz.member.domain.MemberLevel;
import org.dromara.biz.member.mapper.MemberLevelMapper;
import org.dromara.biz.member.service.IMemberLevelService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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
        lqw.eq(bo.getAppId() != null, MemberLevel::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberTypeId() != null, MemberLevel::getMemberTypeId, bo.getMemberTypeId());
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
