package org.dromara.biz.thoughts.service;

import org.dromara.biz.thoughts.domain.ThotAlbumThought;
import org.dromara.biz.thoughts.domain.vo.ThotAlbumThoughtVo;

import java.util.Collection;
import java.util.List;

/**
 * 思集信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-06
 */
public interface IThotAlbumThoughtService {

    /**
     * 查询思集信息
     */
    ThotAlbumThoughtVo queryById(Long id);

    ThotAlbumThoughtVo queryById(Long albumId, Long thoughtId);

    List<ThotAlbumThoughtVo> queryByAlbumId(Long albumId);

    List<ThotAlbumThoughtVo> queryByThoughtId(Long thoughtId);

    Boolean insertBatch(List<ThotAlbumThought> adds);

    Boolean updateIsCover(Long id, String isCover);

    Boolean deleteByIds(Long albumId);

    Boolean deleteByIds(Collection<Long> ids);
}
