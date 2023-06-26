package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidComment;
import org.dromara.alkaid.domain.vo.AlkaidCommentVo;
import org.dromara.alkaid.domain.bo.AlkaidCommentBo;

import java.util.Collection;
import java.util.List;

/**
 * 图集评论信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidCommentService {

    /**
     * 查询图集评论信息
     */
    AlkaidCommentVo queryById(Long commentId);


    /**
     * 查询图集评论信息列表
     */
    List<AlkaidCommentVo> queryList(AlkaidCommentBo bo);

    /**
     * 新增图集评论信息
     */
    Boolean insertByBo(AlkaidCommentBo bo);

    /**
     * 修改图集评论信息
     */
    Boolean updateByBo(AlkaidCommentBo bo);

    /**
     * 校验并批量删除图集评论信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
