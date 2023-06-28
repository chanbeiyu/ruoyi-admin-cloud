package org.dromara.social.service;

import org.dromara.social.domain.SocialApp;
import org.dromara.social.domain.vo.SocialAppVo;
import org.dromara.social.domain.bo.SocialAppBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 应用信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialAppService {

    /**
     * 查询应用信息
     */
    SocialAppVo queryById(Long appId);

    /**
     * 查询应用信息列表
     */
    TableDataInfo<SocialAppVo> queryPageList(SocialAppBo bo, PageQuery pageQuery);

    /**
     * 查询应用信息列表
     */
    List<SocialAppVo> queryList(SocialAppBo bo);

    /**
     * 新增应用信息
     */
    Boolean insertByBo(SocialAppBo bo);

    /**
     * 修改应用信息
     */
    Boolean updateByBo(SocialAppBo bo);

    /**
     * 校验并批量删除应用信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
