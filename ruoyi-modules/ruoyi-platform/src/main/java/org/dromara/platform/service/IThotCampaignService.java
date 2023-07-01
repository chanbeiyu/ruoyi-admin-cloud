package org.dromara.platform.service;

import org.dromara.platform.domain.ThotCampaign;
import org.dromara.platform.domain.vo.ThotCampaignVo;
import org.dromara.platform.domain.bo.ThotCampaignBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

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

    /**
     * 校验并批量删除活动信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
