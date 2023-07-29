package org.dromara.biz.social.service;

import org.dromara.biz.social.domain.vo.SocialLikeVo;
import org.dromara.biz.social.domain.bo.SocialLikeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 点赞信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialLikeService {

    /**
     * 查询点赞信息
     */
    SocialLikeVo queryById(Long likeId);

    /**
     * 查询点赞信息列表
     */
    TableDataInfo<SocialLikeVo> queryPageList(SocialLikeBo bo, PageQuery pageQuery);

    /**
     * 查询点赞信息列表
     */
    List<SocialLikeVo> queryList(SocialLikeBo bo);

    /**
     * 新增点赞信息
     */
    Boolean insertByBo(SocialLikeBo bo);

    /**
     * 修改点赞信息
     */
    Boolean updateByBo(SocialLikeBo bo);

    /**
     * 校验并批量删除点赞信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
