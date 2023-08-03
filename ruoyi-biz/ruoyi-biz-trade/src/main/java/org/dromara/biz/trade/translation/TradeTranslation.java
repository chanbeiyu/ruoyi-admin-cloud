package org.dromara.biz.trade.translation;

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
@TranslationType(type = TradeTranslation.key)
public class TradeTranslation implements TranslationInterface<String> {

    public static final String key = "TRADE_ID_TO_NAME";

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
            case Other.NOTICE -> {
                return CacheUtils.get(RedisKey.SOCIAL_NOTICTYPE_ID_NAME, key);
                //return socialNoticeTypeService.queryById(Long.parseLong(key.toString())).getNoticeTypeName();
            }
            case Other.TAG -> {
                return CacheUtils.get(RedisKey.SOCIAL_TAG_ID_NAME, key);
                //return socialTagService.queryById(Long.parseLong(key.toString())).getTagName();
            }
            case Other.SUBJECT -> {
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
        String SUBJECT = "subject";
        String NOTICE = "notice";
        String TAG = "tag";

    }

}
