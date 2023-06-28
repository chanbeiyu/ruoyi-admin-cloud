package org.dromara.social.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.social.domain.bo.SocialNoticeBo;
import org.dromara.social.domain.vo.SocialNoticeVo;
import org.dromara.social.domain.SocialNotice;
import org.dromara.social.mapper.SocialNoticeMapper;
import org.dromara.social.service.ISocialNoticeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 信息通知Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialNoticeServiceImpl implements ISocialNoticeService {

    private final SocialNoticeMapper baseMapper;

    /**
     * 查询信息通知
     */
    @Override
    public SocialNoticeVo queryById(Long noticeId){
        return baseMapper.selectVoById(noticeId);
    }

    /**
     * 查询信息通知列表
     */
    @Override
    public TableDataInfo<SocialNoticeVo> queryPageList(SocialNoticeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialNotice> lqw = buildQueryWrapper(bo);
        Page<SocialNoticeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询信息通知列表
     */
    @Override
    public List<SocialNoticeVo> queryList(SocialNoticeBo bo) {
        LambdaQueryWrapper<SocialNotice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialNotice> buildQueryWrapper(SocialNoticeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialNotice> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialNotice::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, SocialNotice::getMemberId, bo.getMemberId());
        lqw.eq(bo.getTriggerMemberId() != null, SocialNotice::getTriggerMemberId, bo.getTriggerMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getTriggerId()), SocialNotice::getTriggerId, bo.getTriggerId());
        lqw.eq(StringUtils.isNotBlank(bo.getTriggerContent()), SocialNotice::getTriggerContent, bo.getTriggerContent());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), SocialNotice::getTypeCode, bo.getTypeCode());
        lqw.eq(bo.getStatus() != null, SocialNotice::getStatus, bo.getStatus());
        lqw.between(params.get("beginSendTime") != null && params.get("endSendTime") != null,
            SocialNotice::getSendTime ,params.get("beginSendTime"), params.get("endSendTime"));
        lqw.between(params.get("beginReadTime") != null && params.get("endReadTime") != null,
            SocialNotice::getReadTime ,params.get("beginReadTime"), params.get("endReadTime"));
        return lqw;
    }

    /**
     * 新增信息通知
     */
    @Override
    public Boolean insertByBo(SocialNoticeBo bo) {
        SocialNotice add = MapstructUtils.convert(bo, SocialNotice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setNoticeId(add.getNoticeId());
        }
        return flag;
    }

    /**
     * 修改信息通知
     */
    @Override
    public Boolean updateByBo(SocialNoticeBo bo) {
        SocialNotice update = MapstructUtils.convert(bo, SocialNotice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialNotice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除信息通知
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
