package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidCampaign;
import org.dromara.alkaid.domain.vo.AlkaidCampaignVo;
import org.dromara.alkaid.domain.bo.AlkaidCampaignBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 活动信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidCampaignService {

    /**
     * 查询活动信息
     */
    AlkaidCampaignVo queryById(Long campaignId);

    /**
     * 查询活动信息列表
     */
    TableDataInfo<AlkaidCampaignVo> queryPageList(AlkaidCampaignBo bo, PageQuery pageQuery);

    /**
     * 查询活动信息列表
     */
    List<AlkaidCampaignVo> queryList(AlkaidCampaignBo bo);

    /**
     * 新增活动信息
     */
    Boolean insertByBo(AlkaidCampaignBo bo);

    /**
     * 修改活动信息
     */
    Boolean updateByBo(AlkaidCampaignBo bo);

    /**
     * 校验并批量删除活动信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
