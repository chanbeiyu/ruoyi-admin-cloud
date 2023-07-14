package org.dromara.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.platform.constant.DataStatus1;
import org.dromara.platform.domain.ThotCampaign;
import org.dromara.platform.domain.bo.ThotCampaignBo;
import org.dromara.platform.domain.vo.ThotCampaignVo;
import org.dromara.platform.mapper.ThotCampaignMapper;
import org.dromara.platform.service.IThotCampaignService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 活动信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotCampaignServiceImpl implements IThotCampaignService {

    private final ThotCampaignMapper baseMapper;

    /**
     * 查询活动信息
     */
    @Override
    public ThotCampaignVo queryById(Long campaignId) {
        return baseMapper.selectVoById(campaignId);
    }

    /**
     * 查询活动信息列表
     */
    @Override
    public TableDataInfo<ThotCampaignVo> queryPageList(ThotCampaignBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotCampaign> lqw = buildQueryWrapper(bo)
            .select(ThotCampaign.class, f -> !f.getColumn().equals("campaign_content"));
        Page<ThotCampaignVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询活动信息列表
     */
    @Override
    public List<ThotCampaignVo> queryList(ThotCampaignBo bo) {
        LambdaQueryWrapper<ThotCampaign> lqw = buildQueryWrapper(bo)
            .select(ThotCampaign.class, f -> !f.getColumn().equals("campaign_content"));
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThotCampaign> buildQueryWrapper(ThotCampaignBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotCampaign> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), ThotCampaign::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotCampaign::getAppId, bo.getAppIds());
        lqw.eq(bo.getCampaignCode() != null, ThotCampaign::getCampaignCode, bo.getCampaignCode());
        lqw.like(StringUtils.isNotBlank(bo.getCampaignName()), ThotCampaign::getCampaignName, bo.getCampaignName());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), ThotCampaign::getTypeCode, bo.getTypeCode());
        lqw.eq(bo.getStatus() != null, ThotCampaign::getStatus, bo.getStatus());
        lqw.between(params.get("beginBeginTime") != null && params.get("endBeginTime") != null,
            ThotCampaign::getBeginTime, params.get("beginBeginTime"), params.get("endBeginTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            ThotCampaign::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        return lqw;
    }

    /**
     * 新增活动信息
     */
    @Override
    public Boolean insertByBo(ThotCampaignBo bo) {
        ThotCampaign add = MapstructUtils.convert(bo, ThotCampaign.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCampaignId(add.getCampaignId());
        }
        return flag;
    }

    /**
     * 修改活动信息
     */
    @Override
    public Boolean updateByBo(ThotCampaignBo bo) {
        ThotCampaign update = MapstructUtils.convert(bo, ThotCampaign.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotCampaign entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 修改活动状态
     */
    @Override
    public int updateStatus(Collection<Long> ids, DataStatus1 dataStatus) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<ThotCampaign>()
                .set(ThotCampaign::getStatus, dataStatus.status)
                .in(ThotCampaign::getCampaignId, ids));
    }

    /**
     * 批量删除活动信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
