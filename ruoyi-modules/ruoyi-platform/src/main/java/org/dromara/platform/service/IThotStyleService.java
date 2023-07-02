package org.dromara.platform.service;

import org.dromara.platform.domain.ThotStyle;
import org.dromara.platform.domain.vo.ThotStyleVo;
import org.dromara.platform.domain.bo.ThotStyleBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 样式信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IThotStyleService {

    /**
     * 查询样式信息
     */
    ThotStyleVo queryById(Long styleId);

    /**
     * 查询样式信息列表
     */
    TableDataInfo<ThotStyleVo> queryPageList(ThotStyleBo bo, PageQuery pageQuery);

    /**
     * 查询样式信息列表
     */
    List<ThotStyleVo> queryList(ThotStyleBo bo);

    /**
     * 新增样式信息
     */
    Boolean insertByBo(ThotStyleBo bo);

    /**
     * 修改样式信息
     */
    Boolean updateByBo(ThotStyleBo bo);

    int updateStyleStatus(Long styleId, String status);

    /**
     * 校验并批量删除样式信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
