package org.dromara.biz.thoughts.service;

import org.dromara.biz.thoughts.domain.ThotChannelThought;
import org.dromara.biz.thoughts.domain.vo.ThotChannelThoughtVo;

import java.util.Collection;
import java.util.List;

/**
 * 思绪专题关联信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
public interface IThotChannelThoughtService {

    /**
     * 查询思绪专题关联信息
     */
    ThotChannelThoughtVo queryById(Long id);

    List<ThotChannelThought> queryByChannelId(Long channelId);

    List<ThotChannelThought> queryByThoughtId(Long thoughtId);

    /**
     * 新增思绪专题关联信息
     */
    Boolean insertByBo(ThotChannelThought add);

    /**
     * 修改思绪专题关联信息
     */
    Boolean updateByBo(ThotChannelThought update);

    /**
     * 校验并批量删除思绪专题关联信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
