package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidAlbum;
import org.dromara.alkaid.domain.vo.AlkaidAlbumVo;
import org.dromara.alkaid.domain.bo.AlkaidAlbumBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 图集信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidAlbumService {

    /**
     * 查询图集信息
     */
    AlkaidAlbumVo queryById(Long albumId);

    /**
     * 查询图集信息列表
     */
    TableDataInfo<AlkaidAlbumVo> queryPageList(AlkaidAlbumBo bo, PageQuery pageQuery);

    /**
     * 查询图集信息列表
     */
    List<AlkaidAlbumVo> queryList(AlkaidAlbumBo bo);

    /**
     * 新增图集信息
     */
    Boolean insertByBo(AlkaidAlbumBo bo);

    /**
     * 修改图集信息
     */
    Boolean updateByBo(AlkaidAlbumBo bo);

    /**
     * 校验并批量删除图集信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
