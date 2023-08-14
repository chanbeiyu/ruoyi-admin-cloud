package org.dromara.basal.platform.translation;

import lombok.AllArgsConstructor;
import org.dromara.basal.platform.constant.RedisKey;
import org.dromara.basal.platform.mapper.app.AppInfoMapper;
import org.dromara.basal.platform.mapper.member.MemberInfoMapper;
import org.dromara.basal.platform.mapper.member.MemberTypeMapper;
import org.dromara.basal.platform.mapper.social.SocialNoticeMapper;
import org.dromara.basal.platform.mapper.social.SocialSubjectMapper;
import org.dromara.basal.platform.mapper.social.SocialTagMapper;
import org.dromara.basal.platform.service.app.IAppInfoService;
import org.dromara.basal.platform.service.member.IMemberInfoService;
import org.dromara.basal.platform.service.member.IMemberTypeService;
import org.dromara.basal.platform.service.social.ISocialNoticeService;
import org.dromara.basal.platform.service.social.ISocialNoticeTypeService;
import org.dromara.basal.platform.service.social.ISocialSubjectService;
import org.dromara.basal.platform.service.social.ISocialTagService;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

/**
 * @author wy_peng_chen6
 */
@Component
@AllArgsConstructor
@TranslationType(type = PlatformTranslation.key)
public class PlatformTranslation implements TranslationInterface<String> {

    public static final String key = "PLATFORM_ID_TO_NAME";

    private final IAppInfoService appInfoService;
    private final IMemberInfoService memberInfoService;
    private final IMemberTypeService memberTypeService;

    private final ISocialNoticeTypeService socialNoticeTypeService;
    private final ISocialTagService socialTagService;
    private final ISocialSubjectService socialSubjectService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case Other.APP -> {
                //return CacheUtils.get(RedisKey.APP_ID_NAME, key);
                return appInfoService.queryById(Long.parseLong(key.toString())).getAppName();
            }
            case Other.MEMBER_INFO -> {
                //return CacheUtils.get(RedisKey.MEMBER_INFO_ID_NAME, key);
                return memberInfoService.queryById(Long.parseLong(key.toString())).getNickName();
            }
            case Other.MEMBER_TYPE -> {
                //return CacheUtils.get(RedisKey.MEMBER_TYPE_ID_NAME, key);
                return memberTypeService.queryById(Long.parseLong(key.toString())).getTypeName();
            }
            case Other.SOCIAL_NOTICE -> {
                // return CacheUtils.get(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, key);
                return socialNoticeTypeService.queryById(Long.parseLong(key.toString())).getNoticeTypeName();
            }
            case Other.SOCIAL_TAG -> {
                // return CacheUtils.get(RedisKey.SOCIAL_TAG_ID_NAME, key);
                return socialTagService.queryById(Long.parseLong(key.toString())).getTagName();
            }
            case Other.SOCIAL_SUBJECT -> {
                // return CacheUtils.get(RedisKey.SOCIAL_SUBJECT_ID_NAME, key);
                return socialSubjectService.queryById(Long.parseLong(key.toString())).getSubjectName();
            }
            default -> {
                return null;
            }
        }
    }

    public interface Other {
        String APP = "app";
        String MEMBER_INFO = "member_info";
        String MEMBER_TYPE = "member_type";
        String SOCIAL_SUBJECT = "subject";
        String SOCIAL_NOTICE = "notice";
        String SOCIAL_TAG = "tag";

    }

}
