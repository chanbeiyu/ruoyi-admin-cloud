package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidFavor;
import org.dromara.alkaid.domain.vo.AlkaidFavorVo;
import org.dromara.alkaid.domain.bo.AlkaidFavorBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 图集收藏信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidFavorService {

    /**
     * 查询图集收藏信息
     */
    AlkaidFavorVo queryById(Long favorId);

    /**
     * 查询图集收藏信息列表
     */
    TableDataInfo<AlkaidFavorVo> queryPageList(AlkaidFavorBo bo, PageQuery pageQuery);

    /**
     * 查询图集收藏信息列表
     */
    List<AlkaidFavorVo> queryList(AlkaidFavorBo bo);

    /**
     * 新增图集收藏信息
     */
    Boolean insertByBo(AlkaidFavorBo bo);

    /**
     * 修改图集收藏信息
     */
    Boolean updateByBo(AlkaidFavorBo bo);

    /**
     * 校验并批量删除图集收藏信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
