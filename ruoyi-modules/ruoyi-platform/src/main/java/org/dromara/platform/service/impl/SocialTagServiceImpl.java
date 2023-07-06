package org.dromara.platform.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.SocialTagBo;
import org.dromara.platform.domain.vo.SocialTagVo;
import org.dromara.platform.domain.SocialTag;
import org.dromara.platform.mapper.SocialTagMapper;
import org.dromara.platform.service.ISocialTagService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 标签信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialTagServiceImpl implements ISocialTagService {

    private final SocialTagMapper baseMapper;

    /**
     * 查询标签信息
     */
    @Override
    public SocialTagVo queryById(Long tagId){
        return baseMapper.selectVoById(tagId);
    }

    /**
     * 查询标签信息列表
     */
    @Override
    public TableDataInfo<SocialTagVo> queryPageList(SocialTagBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialTag> lqw = buildQueryWrapper(bo);
        Page<SocialTagVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询标签信息列表
     */
    @Override
    public List<SocialTagVo> queryList(SocialTagBo bo) {
        LambdaQueryWrapper<SocialTag> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialTag> buildQueryWrapper(SocialTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialTag> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTagCode()), SocialTag::getTagCode, bo.getTagCode());
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), SocialTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagType()), SocialTag::getTagType, bo.getTagType());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialTag::getAppId, bo.getAppId());
        lqw.eq(bo.getSubjectId() != null, SocialTag::getSubjectId, bo.getSubjectId());
        lqw.in(bo.getSubjectIds() != null, SocialTag::getSubjectId, bo.getSubjectIds());
        return lqw;
    }

    /**
     * 新增标签信息
     */
    @Override
    public Boolean insertByBo(SocialTagBo bo) {
        SocialTag add = MapstructUtils.convert(bo, SocialTag.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTagId(add.getTagId());
        }
        return flag;
    }

    /**
     * 修改标签信息
     */
    @Override
    public Boolean updateByBo(SocialTagBo bo) {
        SocialTag update = MapstructUtils.convert(bo, SocialTag.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialTag entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除标签信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
