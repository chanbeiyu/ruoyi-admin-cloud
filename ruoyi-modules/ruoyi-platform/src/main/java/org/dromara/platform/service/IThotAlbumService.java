package org.dromara.platform.service;

import org.dromara.platform.constant.DataStatus1;
import org.dromara.platform.domain.ThotAlbum;
import org.dromara.platform.domain.vo.ThotAlbumVo;
import org.dromara.platform.domain.bo.ThotAlbumBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.platform.domain.vo.ThotThoughtVo;

import java.util.Collection;
import java.util.List;

/**
 * 思集信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IThotAlbumService {

    /**
     * 查询思集信息
     */
    ThotAlbumVo queryById(Long albumId);

    /**
     * 查询思集信息列表
     */
    TableDataInfo<ThotAlbumVo> queryPageList(ThotAlbumBo bo, PageQuery pageQuery);

    /**
     * 查询思集信息列表
     */
    List<ThotAlbumVo> queryList(ThotAlbumBo bo);

    TableDataInfo<ThotThoughtVo> queryAlbumThoughts(Long albumId);

    /**
     * 新增思集信息
     */
    Boolean insertByBo(ThotAlbumBo bo);

    /**
     * 修改思集信息
     */
    Boolean updateByBo(ThotAlbumBo bo);

    int updateStatus(Collection<Long> ids, DataStatus1 dataStatus);

    /**
     * 校验并批量删除思集信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}