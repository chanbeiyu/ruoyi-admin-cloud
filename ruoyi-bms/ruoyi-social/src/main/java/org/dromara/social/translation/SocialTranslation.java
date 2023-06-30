package org.dromara.social.translation;

import lombok.AllArgsConstructor;
import org.dromara.common.translation.annotation.TranslationType;
import org.dromara.common.translation.core.TranslationInterface;
import org.dromara.social.constant.SocialTransConstant;
import org.dromara.social.service.ISocialAppService;
import org.dromara.social.service.ISocialNoticeTypeService;
import org.dromara.social.service.ISocialSubjectService;
import org.dromara.social.service.ISocialTagService;
import org.springframework.stereotype.Component;

/**
 * @author wy_peng_chen6
 */
@Component
@AllArgsConstructor
@TranslationType(type = SocialTransConstant.SOCIAL_ID_TO_NAME)
public class SocialTranslation implements TranslationInterface<String> {

    private ISocialAppService socialAppService;
    private ISocialSubjectService socialSubjectService;
    private ISocialNoticeTypeService socialNoticeTypeService;
    private ISocialTagService socialTagService;

    @Override
    public String translation(Object key, String other) {
        switch (other) {
            case SocialTransConstant.Other.APP -> {
                return socialAppService.queryById(Long.parseLong(key.toString())).getAppName();
            }
            case SocialTransConstant.Other.NOTICE -> {
                return socialNoticeTypeService.queryById(Long.parseLong(key.toString())).getNoticeTypeName();
            }
            case SocialTransConstant.Other.TAG -> {
                return socialTagService.queryById(Long.parseLong(key.toString())).getTagName();
            }
            case SocialTransConstant.Other.SUBJECT -> {
                return socialSubjectService.queryById(Long.parseLong(key.toString())).getSubjectName();
            }
            default -> {
                return null;
            }
        }
    }

}
