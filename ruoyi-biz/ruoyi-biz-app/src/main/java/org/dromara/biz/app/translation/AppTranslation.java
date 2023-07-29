package org.dromara.biz.app.translation;

import lombok.AllArgsConstructor;
import org.dromara.biz.app.service.IAppInfoService;
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
@TranslationType(type = AppTranslation.key)
public class AppTranslation implements TranslationInterface<String> {

    public static final String key = "APP_ID_TO_NAME";

    private IAppInfoService appInfoService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case Other.APP -> {
                return CacheUtils.get(RedisKey.APP_ID_NAME, key);
                //return appInfoService.queryById(Long.parseLong(key.toString())).getAppName();
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
