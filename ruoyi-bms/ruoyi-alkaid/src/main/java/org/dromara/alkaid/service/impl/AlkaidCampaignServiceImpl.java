package org.dromara.alkaid.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.alkaid.domain.bo.AlkaidCampaignBo;
import org.dromara.alkaid.domain.vo.AlkaidCampaignVo;
import org.dromara.alkaid.domain.AlkaidCampaign;
import org.dromara.alkaid.mapper.AlkaidCampaignMapper;
import org.dromara.alkaid.service.IAlkaidCampaignService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 活动信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidCampaignServiceImpl implements IAlkaidCampaignService {

    private final AlkaidCampaignMapper baseMapper;

    /**
     * 查询活动信息
     */
    @Override
    public AlkaidCampaignVo queryById(Long campaignId){
        return baseMapper.selectVoById(campaignId);
    }

    /**
     * 查询活动信息列表
     */
    @Override
    public TableDataInfo<AlkaidCampaignVo> queryPageList(AlkaidCampaignBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlkaidCampaign> lqw = buildQueryWrapper(bo);
        Page<AlkaidCampaignVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询活动信息列表
     */
    @Override
    public List<AlkaidCampaignVo> queryList(AlkaidCampaignBo bo) {
        LambdaQueryWrapper<AlkaidCampaign> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidCampaign> buildQueryWrapper(AlkaidCampaignBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidCampaign> lqw = Wrappers.lambdaQuery();
        lqw.between(params.get("beginCampaignTitle") != null && params.get("endCampaignTitle") != null,
            AlkaidCampaign::getCampaignTitle ,params.get("beginCampaignTitle"), params.get("endCampaignTitle"));
        lqw.eq(bo.getCampaignType() != null, AlkaidCampaign::getCampaignType, bo.getCampaignType());
        lqw.eq(bo.getStatus() != null, AlkaidCampaign::getStatus, bo.getStatus());
        lqw.between(params.get("beginBeginTime") != null && params.get("endBeginTime") != null,
            AlkaidCampaign::getBeginTime ,params.get("beginBeginTime"), params.get("endBeginTime"));
        lqw.between(params.get("beginPublishTime") != null && params.get("endPublishTime") != null,
            AlkaidCampaign::getPublishTime ,params.get("beginPublishTime"), params.get("endPublishTime"));
        return lqw;
    }

    /**
     * 新增活动信息
     */
    @Override
    public Boolean insertByBo(AlkaidCampaignBo bo) {
        AlkaidCampaign add = MapstructUtils.convert(bo, AlkaidCampaign.class);
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
    public Boolean updateByBo(AlkaidCampaignBo bo) {
        AlkaidCampaign update = MapstructUtils.convert(bo, AlkaidCampaign.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidCampaign entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除活动信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
