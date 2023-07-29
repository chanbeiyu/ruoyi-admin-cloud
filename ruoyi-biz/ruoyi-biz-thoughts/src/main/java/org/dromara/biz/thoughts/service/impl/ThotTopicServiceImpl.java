package org.dromara.biz.thoughts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.common.constant.DataStatus;
import org.dromara.biz.thoughts.domain.ThotTopic;
import org.dromara.biz.thoughts.domain.bo.ThotTopicBo;
import org.dromara.biz.thoughts.domain.vo.ThotTopicVo;
import org.dromara.biz.thoughts.mapper.ThotTopicMapper;
import org.dromara.biz.thoughts.service.IThotTopicService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 话题信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotTopicServiceImpl implements IThotTopicService {

    private final ThotTopicMapper baseMapper;

    /**
     * 查询话题信息
     */
    @Override
    public ThotTopicVo queryById(Long topicId) {
        return baseMapper.selectVoById(topicId);
    }

    /**
     * 查询话题信息列表
     */
    @Override
    public TableDataInfo<ThotTopicVo> queryPageList(ThotTopicBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotTopic> lqw = buildQueryWrapper(bo)
            .select(ThotTopic.class, f -> !f.getColumn().equals("topic_content"));
        Page<ThotTopicVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询话题信息列表
     */
    @Override
    public List<ThotTopicVo> queryList(ThotTopicBo bo) {
        LambdaQueryWrapper<ThotTopic> lqw = buildQueryWrapper(bo)
            .select(ThotTopic.class, f -> !f.getColumn().equals("topic_content"));
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThotTopic> buildQueryWrapper(ThotTopicBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotTopic> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), ThotTopic::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotTopic::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getTopicCode()), ThotTopic::getTopicCode, bo.getTopicCode());
        lqw.like(StringUtils.isNotBlank(bo.getTopicName()), ThotTopic::getTopicName, bo.getTopicName());
        lqw.eq(bo.getStatus() != null, ThotTopic::getStatus, bo.getStatus());
        lqw.between(params.get("beginBeginTime") != null && params.get("endBeginTime") != null,
            ThotTopic::getBeginTime, params.get("beginBeginTime"), params.get("endBeginTime"));
        lqw.between(params.get("beginEndTime") != null && params.get("endEndTime") != null,
            ThotTopic::getEndTime, params.get("beginEndTime"), params.get("endEndTime"));
        return lqw;
    }

    /**
     * 新增话题信息
     */
    @Override
    public Boolean insertByBo(ThotTopicBo bo) {
        ThotTopic add = MapstructUtils.convert(bo, ThotTopic.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTopicId(add.getTopicId());
        }
        return flag;
    }

    /**
     * 修改话题信息
     */
    @Override
    public Boolean updateByBo(ThotTopicBo bo) {
        ThotTopic update = MapstructUtils.convert(bo, ThotTopic.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotTopic entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 修改话题状态
     */
    @Override
    public int updateStatus(Collection<Long> ids, DataStatus dataStatus) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<ThotTopic>()
                .set(ThotTopic::getStatus, dataStatus.status)
                .in(ThotTopic::getTopicId, ids));
    }

    /**
     * 批量删除话题信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
