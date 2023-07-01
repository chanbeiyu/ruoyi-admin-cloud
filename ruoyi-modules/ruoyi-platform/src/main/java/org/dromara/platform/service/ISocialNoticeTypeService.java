package org.dromara.platform.service;

import org.dromara.platform.domain.vo.SocialNoticeTypeVo;
import org.dromara.platform.domain.bo.SocialNoticeTypeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 信息通知类型Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialNoticeTypeService {

    /**
     * 查询信息通知类型
     */
    SocialNoticeTypeVo queryById(Long noticeTypeId);

    /**
     * 查询信息通知类型列表
     */
    TableDataInfo<SocialNoticeTypeVo> queryPageList(SocialNoticeTypeBo bo, PageQuery pageQuery);

    /**
     * 查询信息通知类型列表
     */
    List<SocialNoticeTypeVo> queryList(SocialNoticeTypeBo bo);

    /**
     * 新增信息通知类型
     */
    Boolean insertByBo(SocialNoticeTypeBo bo);

    /**
     * 修改信息通知类型
     */
    Boolean updateByBo(SocialNoticeTypeBo bo);

    /**
     * 校验并批量删除信息通知类型信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
