package org.dromara.platform.service;

import org.dromara.platform.domain.ThotTopicAttend;
import org.dromara.platform.domain.vo.ThotTopicAttendVo;

import java.util.Collection;
import java.util.List;

/**
 * 话题参与Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
public interface IThotTopicAttendService {

    /**
     * 查询话题参与
     */
    ThotTopicAttendVo queryById(Long id);

    List<ThotTopicAttend> queryByTopicId(Long topicId);

    List<ThotTopicAttend> queryByAlbumId(Long albumId);

    List<ThotTopicAttend> queryByThoughtId(Long thoughtId);

    List<ThotTopicAttend> queryByAttendId(Long attendId);

    /**
     * 新增话题参与
     */
    Boolean insertByBo(ThotTopicAttend add);

    /**
     * 修改话题参与
     */
    Boolean updateByBo(ThotTopicAttend update);

    /**
     * 校验并批量删除话题参与信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
