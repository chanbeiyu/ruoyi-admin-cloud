package org.dromara.platform.service;

import org.dromara.platform.domain.ThotThought;
import org.dromara.platform.domain.vo.ThotThoughtVo;
import org.dromara.platform.domain.bo.ThotThoughtBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 思绪信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IThotThoughtService {

    /**
     * 查询思绪信息
     */
    ThotThoughtVo queryById(Long thoughtId);

    /**
     * 查询思绪信息列表
     */
    TableDataInfo<ThotThoughtVo> queryPageList(ThotThoughtBo bo, PageQuery pageQuery);

    /**
     * 查询思绪信息列表
     */
    List<ThotThoughtVo> queryList(ThotThoughtBo bo);

    /**
     * 新增思绪信息
     */
    Boolean insertByBo(ThotThoughtBo bo);

    /**
     * 修改思绪信息
     */
    Boolean updateByBo(ThotThoughtBo bo);

    /**
     * 校验并批量删除思绪信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
