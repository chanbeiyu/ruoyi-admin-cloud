package org.dromara.social.service;

import org.dromara.social.domain.SocialNotice;
import org.dromara.social.domain.vo.SocialNoticeVo;
import org.dromara.social.domain.bo.SocialNoticeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 信息通知Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialNoticeService {

    /**
     * 查询信息通知
     */
    SocialNoticeVo queryById(Long noticeId);

    /**
     * 查询信息通知列表
     */
    TableDataInfo<SocialNoticeVo> queryPageList(SocialNoticeBo bo, PageQuery pageQuery);

    /**
     * 查询信息通知列表
     */
    List<SocialNoticeVo> queryList(SocialNoticeBo bo);

    /**
     * 新增信息通知
     */
    Boolean insertByBo(SocialNoticeBo bo);

    /**
     * 修改信息通知
     */
    Boolean updateByBo(SocialNoticeBo bo);

    /**
     * 校验并批量删除信息通知信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
