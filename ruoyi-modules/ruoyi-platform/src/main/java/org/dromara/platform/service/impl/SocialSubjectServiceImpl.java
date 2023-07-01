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
import org.dromara.platform.domain.bo.SocialSubjectBo;
import org.dromara.platform.domain.vo.SocialSubjectVo;
import org.dromara.platform.domain.SocialSubject;
import org.dromara.platform.mapper.SocialSubjectMapper;
import org.dromara.platform.service.ISocialSubjectService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 内容主题Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialSubjectServiceImpl implements ISocialSubjectService {

    private final SocialSubjectMapper baseMapper;

    /**
     * 查询内容主题
     */
    @Override
    public SocialSubjectVo queryById(Long subjectId){
        return baseMapper.selectVoById(subjectId);
    }

    /**
     * 查询内容主题列表
     */
    @Override
    public TableDataInfo<SocialSubjectVo> queryPageList(SocialSubjectBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialSubject> lqw = buildQueryWrapper(bo);
        Page<SocialSubjectVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询内容主题列表
     */
    @Override
    public List<SocialSubjectVo> queryList(SocialSubjectBo bo) {
        LambdaQueryWrapper<SocialSubject> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialSubject> buildQueryWrapper(SocialSubjectBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialSubject> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialSubject::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getSubjectCode()), SocialSubject::getSubjectCode, bo.getSubjectCode());
        lqw.like(StringUtils.isNotBlank(bo.getSubjectName()), SocialSubject::getSubjectName, bo.getSubjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), SocialSubject::getDescription, bo.getDescription());
        return lqw;
    }

    /**
     * 新增内容主题
     */
    @Override
    public Boolean insertByBo(SocialSubjectBo bo) {
        SocialSubject add = MapstructUtils.convert(bo, SocialSubject.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSubjectId(add.getSubjectId());
        }
        return flag;
    }

    /**
     * 修改内容主题
     */
    @Override
    public Boolean updateByBo(SocialSubjectBo bo) {
        SocialSubject update = MapstructUtils.convert(bo, SocialSubject.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialSubject entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除内容主题
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
