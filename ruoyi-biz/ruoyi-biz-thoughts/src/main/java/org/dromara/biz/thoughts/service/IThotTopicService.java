package org.dromara.biz.thoughts.service;

import org.dromara.biz.common.constant.DataStatus;
import org.dromara.biz.thoughts.domain.vo.ThotTopicVo;
import org.dromara.biz.thoughts.domain.bo.ThotTopicBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 话题信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IThotTopicService {

    /**
     * 查询话题信息
     */
    ThotTopicVo queryById(Long topicId);

    /**
     * 查询话题信息列表
     */
    TableDataInfo<ThotTopicVo> queryPageList(ThotTopicBo bo, PageQuery pageQuery);

    /**
     * 查询话题信息列表
     */
    List<ThotTopicVo> queryList(ThotTopicBo bo);

    /**
     * 新增话题信息
     */
    Boolean insertByBo(ThotTopicBo bo);

    /**
     * 修改话题信息
     */
    Boolean updateByBo(ThotTopicBo bo);

    /**
     * 修改话题状态
     */
    int updateStatus(Collection<Long> ids, DataStatus dataStatus);

    /**
     * 校验并批量删除话题信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
