package org.dromara.basal.platform.service.social.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.basal.platform.constant.RedisKey;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.social.bo.SocialSubjectBo;
import org.dromara.basal.platform.domain.social.vo.SocialSubjectVo;
import org.dromara.basal.platform.domain.social.SocialSubject;
import org.dromara.basal.platform.mapper.social.SocialSubjectMapper;
import org.dromara.basal.platform.service.social.ISocialSubjectService;

import java.util.*;

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
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialSubject::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialSubject::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getSubjectCode()), SocialSubject::getSubjectCode, bo.getSubjectCode());
        lqw.like(StringUtils.isNotBlank(bo.getSubjectName()), SocialSubject::getSubjectName, bo.getSubjectName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SocialSubject::getStatus, bo.getStatus());
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
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_ID_NAME, add.getSubjectId() + "", bo.getSubjectName());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_CODE_NAME, add.getSubjectCode(), bo.getSubjectName());
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
        boolean bool = baseMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_ID_NAME, update.getSubjectId() + "", update.getSubjectName());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_CODE_NAME, update.getSubjectCode(), update.getSubjectName());
        }
        return bool;
    }

    /**
     * 修改主题通知状态
     *
     * @param subjectId 主题ID
     * @param status 状态
     * @return 结果
     */
    @Override
    public int updateStatus(Long subjectId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<SocialSubject>()
                .set(SocialSubject::getStatus, status)
                .eq(SocialSubject::getSubjectId, subjectId));
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
        List<SocialSubject> socialSubjects = baseMapper.selectBatchIds(ids);
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean bool = baseMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialSubjects.forEach(o -> {
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getSubjectId() + "");
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getSubjectCode());
            });
        }
        return bool;
    }
}
