package org.dromara.biz.thoughts.translation;

import lombok.AllArgsConstructor;
import org.dromara.app.api.RemoteAppService;
import org.dromara.biz.common.constant.RedisKey;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.springframework.stereotype.Component;

/**
 * @author wy_peng_chen6
 */
@Component
@AllArgsConstructor
@TranslationType(type = ThoughtsTranslation.key)
public class ThoughtsTranslation implements TranslationInterface<String> {

    public static final String key = "THOUGHTS_ID_TO_NAME";

    private RemoteAppService remoteAppService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case Other.APP -> {
                return remoteAppService.getAppById(Long.parseLong(key.toString())).getAppName();
            }
            default -> {
                return null;
            }
        }
    }

    public interface Other {
        String APP = "app";
    }

}
