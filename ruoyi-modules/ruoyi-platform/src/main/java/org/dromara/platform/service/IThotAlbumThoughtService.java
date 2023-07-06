package org.dromara.platform.service;

import org.dromara.platform.domain.ThotAlbumThought;
import org.dromara.platform.domain.vo.ThotAlbumThoughtVo;

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

    List<ThotAlbumThought> queryByAlbumId(Long albumId);

    List<ThotAlbumThought> queryByThoughtId(Long thoughtId);

    /**
     * 新增思集信息
     */
    Boolean insertByBo(ThotAlbumThought add);

    /**
     * 修改思集信息
     */
    Boolean updateByBo(ThotAlbumThought update);

    /**
     * 校验并批量删除思集信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
