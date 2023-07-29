package org.dromara.biz.social.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.biz.common.constant.RedisKey;
import org.dromara.biz.social.domain.SocialNoticeType;
import org.dromara.biz.social.domain.SocialSubject;
import org.dromara.biz.social.domain.SocialTag;
import org.dromara.biz.social.mapper.SocialNoticeTypeMapper;
import org.dromara.biz.social.mapper.SocialSubjectMapper;
import org.dromara.biz.social.mapper.SocialTagMapper;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.tenant.core.TenantEntity;
import org.dromara.common.tenant.helper.TenantHelper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author chanbeiyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class SocialApplicationRunner implements ApplicationRunner {

    private final SocialNoticeTypeMapper socialNoticeTypeMapper;
    private final SocialSubjectMapper socialSubjectMapper;
    private final SocialTagMapper socialTagMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initSocialMaping();
        log.info("初始化 initSocialMaping 配置成功");
    }

    private void initSocialMaping() {
        List<SocialNoticeType> socialNoticeTypes = socialNoticeTypeMapper.selectList();
        List<SocialSubject> socialSubjects = socialSubjectMapper.selectList();
        List<SocialTag> socialTags = socialTagMapper.selectList();

        socialNoticeTypes.forEach(o -> {
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, o.getNoticeTypeId(), o.getNoticeTypeName());
            CacheUtils.put(RedisKey.SOCIAL_NOTICTYPE_CODE_NAME, o.getNoticeTypeCode(), o.getNoticeTypeName());
        });
        socialSubjects.forEach(o -> {
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_ID_NAME, o.getSubjectId(), o.getSubjectName());
            CacheUtils.put(RedisKey.SOCIAL_SUBJECT_CODE_NAME, o.getSubjectCode(), o.getSubjectName());
        });
        socialTags.forEach(o -> {
            CacheUtils.put(RedisKey.SOCIAL_TAG_ID_NAME, o.getTagId(), o.getTagName());
            CacheUtils.put(RedisKey.SOCIAL_TAG_CODE_NAME, o.getTagCode(), o.getTagName());
        });
    }

}
