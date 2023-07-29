package org.dromara.biz.thoughts.service;

import org.dromara.biz.common.constant.DataStatus;
import org.dromara.biz.thoughts.domain.bo.ThotCampaignBo;
import org.dromara.biz.thoughts.domain.vo.ThotCampaignVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 活动信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IThotCampaignService {

    /**
     * 查询活动信息
     */
    ThotCampaignVo queryById(Long campaignId);

    /**
     * 查询活动信息列表
     */
    TableDataInfo<ThotCampaignVo> queryPageList(ThotCampaignBo bo, PageQuery pageQuery);

    /**
     * 查询活动信息列表
     */
    List<ThotCampaignVo> queryList(ThotCampaignBo bo);

    /**
     * 新增活动信息
     */
    Boolean insertByBo(ThotCampaignBo bo);

    /**
     * 修改活动信息
     */
    Boolean updateByBo(ThotCampaignBo bo);

    int updateStatus(Collection<Long> ids, DataStatus dataStatus);

    /**
     * 校验并批量删除活动信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
