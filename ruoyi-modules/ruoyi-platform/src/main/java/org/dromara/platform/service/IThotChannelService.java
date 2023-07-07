package org.dromara.platform.service;

import org.dromara.platform.domain.vo.ThotChannelVo;
import org.dromara.platform.domain.bo.ThotChannelBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 频道信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IThotChannelService {

    /**
     * 查询频道信息
     */
    ThotChannelVo queryById(Long channelId);

    /**
     * 查询频道信息列表
     */
    TableDataInfo<ThotChannelVo> queryPageList(ThotChannelBo bo, PageQuery pageQuery);

    /**
     * 查询频道信息列表
     */
    List<ThotChannelVo> queryList(ThotChannelBo bo);

    /**
     * 新增频道信息
     */
    Boolean insertByBo(ThotChannelBo bo);

    /**
     * 修改频道信息
     */
    Boolean updateByBo(ThotChannelBo bo);

    int updateStatus(Long channelId, String status);

    /**
     * 校验并批量删除频道信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
