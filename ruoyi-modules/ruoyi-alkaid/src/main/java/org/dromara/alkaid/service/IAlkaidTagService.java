package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidTag;
import org.dromara.alkaid.domain.vo.AlkaidTagVo;
import org.dromara.alkaid.domain.bo.AlkaidTagBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 标签信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidTagService {

    /**
     * 查询标签信息
     */
    AlkaidTagVo queryById(Long tagId);

    /**
     * 查询标签信息列表
     */
    TableDataInfo<AlkaidTagVo> queryPageList(AlkaidTagBo bo, PageQuery pageQuery);

    /**
     * 查询标签信息列表
     */
    List<AlkaidTagVo> queryList(AlkaidTagBo bo);

    /**
     * 新增标签信息
     */
    Boolean insertByBo(AlkaidTagBo bo);

    /**
     * 修改标签信息
     */
    Boolean updateByBo(AlkaidTagBo bo);

    /**
     * 校验并批量删除标签信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
