package org.dromara.social.service;

import org.dromara.social.domain.SocialComment;
import org.dromara.social.domain.vo.SocialCommentVo;
import org.dromara.social.domain.bo.SocialCommentBo;

import java.util.Collection;
import java.util.List;

/**
 * 评论信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialCommentService {

    /**
     * 查询评论信息
     */
    SocialCommentVo queryById(Long commentId);


    /**
     * 查询评论信息列表
     */
    List<SocialCommentVo> queryList(SocialCommentBo bo);

    /**
     * 新增评论信息
     */
    Boolean insertByBo(SocialCommentBo bo);

    /**
     * 修改评论信息
     */
    Boolean updateByBo(SocialCommentBo bo);

    /**
     * 校验并批量删除评论信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
