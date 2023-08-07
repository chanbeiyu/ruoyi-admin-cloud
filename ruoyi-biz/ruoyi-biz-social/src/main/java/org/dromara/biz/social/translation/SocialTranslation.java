package org.dromara.biz.social.translation;

import lombok.AllArgsConstructor;
import org.dromara.biz.common.constant.RedisKey;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

/**
 * @author wy_peng_chen6
 */
@Component
@AllArgsConstructor
@TranslationType(type = SocialTranslation.key)
public class SocialTranslation implements TranslationInterface<String> {

    public static final String key = "SOCIAL_ID_TO_NAME";

    //private RemoteAppService remoteAppService;
    //private ISocialSubjectService socialSubjectService;
    //private ISocialNoticeTypeService socialNoticeTypeService;
    //private ISocialTagService socialTagService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case Other.APP -> {
                return CacheUtils.get(RedisKey.APP_ID_NAME, key);
                //return remoteAppService.getAppById(Long.parseLong(key.toString())).getAppName();
            }
            case Other.SOCIAL_NOTICE -> {
                return CacheUtils.get(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, key);
                //return socialNoticeTypeService.queryById(Long.parseLong(key.toString())).getNoticeTypeName();
            }
            case Other.SOCIAL_TAG -> {
                return CacheUtils.get(RedisKey.SOCIAL_TAG_ID_NAME, key);
                //return socialTagService.queryById(Long.parseLong(key.toString())).getTagName();
            }
            case Other.SOCIAL_SUBJECT -> {
                return CacheUtils.get(RedisKey.SOCIAL_SUBJECT_ID_NAME, key);
                //return socialSubjectService.queryById(Long.parseLong(key.toString())).getSubjectName();
            }
            default -> {
                return null;
            }
        }
    }

    public interface Other {
        String APP = "app";
        String SOCIAL_SUBJECT = "subject";
        String SOCIAL_NOTICE = "notice";
        String SOCIAL_TAG = "tag";

    }

}
