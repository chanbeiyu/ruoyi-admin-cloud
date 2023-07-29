package org.dromara.biz.social.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.common.constant.RedisKey;
import org.dromara.biz.social.domain.SocialTag;
import org.dromara.biz.social.domain.bo.SocialTagBo;
import org.dromara.biz.social.domain.vo.SocialSubjectVo;
import org.dromara.biz.social.domain.vo.SocialTagVo;
import org.dromara.biz.social.mapper.SocialTagMapper;
import org.dromara.biz.social.service.ISocialSubjectService;
import org.dromara.biz.social.service.ISocialTagService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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

    private final ISocialSubjectService socialSubjectService;

    /**
     * 查询标签信息
     */
    @Override
    public SocialTagVo queryById(Long tagId) {
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
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialTag::getAppId, bo.getAppId());
        lqw.eq(Objects.nonNull(bo.getSubjectId()), SocialTag::getSubjectId, bo.getSubjectId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getSubjectIds()), SocialTag::getSubjectId, bo.getSubjectIds());
        lqw.like(StringUtils.isNotBlank(bo.getTagCode()), SocialTag::getTagCode, bo.getTagCode());
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), SocialTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagType()), SocialTag::getTagType, bo.getTagType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SocialTag::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增标签信息
     */
    @Override
    public Boolean insertByBo(SocialTagBo bo) {
        SocialTag add = MapstructUtils.convert(bo, SocialTag.class);
        if (add == null) {
            return false;
        }

        SocialSubjectVo socialSubjectVo = socialSubjectService.queryById(bo.getSubjectId());
        if (socialSubjectVo == null) {
            return false;
        }

        validEntityBeforeSave(add);
        add.setAppId(socialSubjectVo.getAppId());
        boolean flag = baseMapper.insert(add) > 0;

        if (flag && add != null) {
            bo.setTagId(add.getTagId());
            CacheUtils.put(RedisKey.SOCIAL_TAG_ID_NAME, add.getTagId() + "", bo.getTagName());
            CacheUtils.put(RedisKey.SOCIAL_TAG_CODE_NAME, add.getTagCode(), bo.getTagName());
        }
        return flag;
    }

    /**
     * 修改标签信息
     */
    @Override
    public Boolean updateByBo(SocialTagBo bo) {
        SocialTag update = MapstructUtils.convert(bo, SocialTag.class);
        if (update == null) {
            return false;
        }

        validEntityBeforeSave(update);

        SocialSubjectVo socialSubjectVo = socialSubjectService.queryById(bo.getSubjectId());
        if (socialSubjectVo == null) {
            return false;
        }
        update.setAppId(socialSubjectVo.getAppId());
        boolean bool = baseMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.SOCIAL_TAG_ID_NAME, update.getTagId() + "", update.getTagName());
            CacheUtils.put(RedisKey.SOCIAL_TAG_CODE_NAME, update.getTagCode(), update.getTagName());
        }
        return bool;
    }

    /**
     * 修改标签通知状态
     *
     * @param tagId  标签ID
     * @param status 状态
     * @return 结果
     */
    @Override
    public int updateStatus(Long tagId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<SocialTag>()
                .set(SocialTag::getStatus, status)
                .eq(SocialTag::getTagId, tagId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialTag entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除标签信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        List<SocialTag> socialTags = baseMapper.selectBatchIds(ids);
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean bool = baseMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialTags.forEach(o -> {
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getTagId() + "");
                CacheUtils.evict(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getTagCode());
            });
        }
        return bool;
    }
}
