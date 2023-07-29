package org.dromara.biz.social.service;

import org.dromara.biz.social.domain.vo.SocialFollowVo;
import org.dromara.biz.social.domain.bo.SocialFollowBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 关注信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialFollowService {

    /**
     * 查询关注信息
     */
    SocialFollowVo queryById(Long followId);

    /**
     * 查询关注信息列表
     */
    TableDataInfo<SocialFollowVo> queryPageList(SocialFollowBo bo, PageQuery pageQuery);

    /**
     * 查询关注信息列表
     */
    List<SocialFollowVo> queryList(SocialFollowBo bo);

    /**
     * 新增关注信息
     */
    Boolean insertByBo(SocialFollowBo bo);

    /**
     * 修改关注信息
     */
    Boolean updateByBo(SocialFollowBo bo);

    /**
     * 校验并批量删除关注信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
