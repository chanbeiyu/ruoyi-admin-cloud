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
import org.dromara.basal.platform.domain.social.bo.SocialNoticeTypeBo;
import org.dromara.basal.platform.domain.social.vo.SocialNoticeTypeVo;
import org.dromara.basal.platform.domain.social.SocialNoticeType;
import org.dromara.basal.platform.mapper.social.SocialNoticeTypeMapper;
import org.dromara.basal.platform.service.social.ISocialNoticeTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 信息通知类型Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialNoticeTypeServiceImpl implements ISocialNoticeTypeService {

    private final SocialNoticeTypeMapper baseMapper;

    /**
     * 查询信息通知类型
     */
    @Override
    public SocialNoticeTypeVo queryById(Long noticeTypeId) {
        return baseMapper.selectVoById(noticeTypeId);
    }

    /**
     * 查询信息通知类型列表
     */
    @Override
    public TableDataInfo<SocialNoticeTypeVo> queryPageList(SocialNoticeTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialNoticeType> lqw = buildQueryWrapper(bo);
        Page<SocialNoticeTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询信息通知类型列表
     */
    @Override
    public List<SocialNoticeTypeVo> queryList(SocialNoticeTypeBo bo) {
        LambdaQueryWrapper<SocialNoticeType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialNoticeType> buildQueryWrapper(SocialNoticeTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialNoticeType> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialNoticeType::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialNoticeType::getAppId, bo.getAppIds());
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeCode()), SocialNoticeType::getNoticeTypeCode, bo.getNoticeTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeName()), SocialNoticeType::getNoticeTypeName, bo.getNoticeTypeName());
        lqw.like(StringUtils.isNotBlank(bo.getStatus()), SocialNoticeType::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增信息通知类型
     */
    @Override
    public Boolean insertByBo(SocialNoticeTypeBo bo) {
        SocialNoticeType add = MapstructUtils.convert(bo, SocialNoticeType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setNoticeTypeId(add.getNoticeTypeId());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, add.getNoticeTypeId() + "", bo.getNoticeTypeName());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, add.getNoticeTypeCode(), bo.getNoticeTypeName());
        }
        return flag;
    }

    /**
     * 修改信息通知类型
     */
    @Override
    public Boolean updateByBo(SocialNoticeTypeBo bo) {
        SocialNoticeType update = MapstructUtils.convert(bo, SocialNoticeType.class);
        validEntityBeforeSave(update);
        boolean bool = baseMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, update.getNoticeTypeId() + "", update.getNoticeTypeName());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, update.getNoticeTypeCode(), update.getNoticeTypeName());
        }
        return bool;
    }

    /**
     * 修改信息通知状态
     *
     * @param noticeId 信息通知ID
     * @param status   状态
     * @return 结果
     */
    @Override
    public int updateStatus(Long noticeId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<SocialNoticeType>()
                .set(SocialNoticeType::getStatus, status)
                .eq(SocialNoticeType::getNoticeTypeId, noticeId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialNoticeType entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除信息通知类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        List<SocialNoticeType> socialNoticeTypes = baseMapper.selectBatchIds(ids);
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean bool = baseMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            socialNoticeTypes.forEach(o -> {
                CacheUtils.evict(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, o.getNoticeTypeId() + "");
                CacheUtils.evict(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, o.getNoticeTypeCode());
            });
        }
        return bool;
    }
}
