package org.dromara.biz.thoughts.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.common.constant.DataStatus;
import org.dromara.biz.thoughts.domain.ThotThought;
import org.dromara.biz.thoughts.domain.bo.ThotThoughtBo;
import org.dromara.biz.thoughts.domain.vo.ThotThoughtVo;
import org.dromara.biz.thoughts.mapper.ThotThoughtMapper;
import org.dromara.biz.thoughts.service.IThotThoughtService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 思绪信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotThoughtServiceImpl implements IThotThoughtService {

    private final ThotThoughtMapper baseMapper;

    /**
     * 查询思绪信息
     */
    @Override
    public ThotThoughtVo queryById(Long thoughtId) {
        return baseMapper.selectVoById(thoughtId);
    }

    /**
     * 查询思绪信息
     */
    @Override
    public List<ThotThoughtVo> queryById(List<Long> thoughtIds) {
        return baseMapper.selectVoBatchIds(thoughtIds, ThotThoughtVo.class);
    }

    /**
     * 查询思绪信息列表
     */
    @Override
    public TableDataInfo<ThotThoughtVo> queryPageList(ThotThoughtBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotThought> lqw = buildQueryWrapper(bo)
            .select(ThotThought.class, f -> !f.getColumn().equals("content"));
        Page<ThotThoughtVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询思绪信息列表
     */
    @Override
    public List<ThotThoughtVo> queryList(ThotThoughtBo bo) {
        LambdaQueryWrapper<ThotThought> lqw = buildQueryWrapper(bo)
            .select(ThotThought.class, f -> !f.getColumn().equals("content"));
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThotThought> buildQueryWrapper(ThotThoughtBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotThought::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotThought::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), ThotThought::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), ThotThought::getTitle, bo.getTitle());
        lqw.eq(bo.getStatus() != null, ThotThought::getStatus, bo.getStatus());
        lqw.between(params.get("beginPublishTime") != null && params.get("endPublishTime") != null,
            ThotThought::getPublishTime, params.get("beginPublishTime"), params.get("endPublishTime"));
        return lqw;
    }

    /**
     * 新增思绪信息
     */
    @Override
    public Boolean insertByBo(ThotThoughtBo bo) {
        ThotThought add = MapstructUtils.convert(bo, ThotThought.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setThoughtId(add.getThoughtId());
        }
        return flag;
    }

    /**
     * 修改思绪信息
     */
    @Override
    public Boolean updateByBo(ThotThoughtBo bo) {
        ThotThought update = MapstructUtils.convert(bo, ThotThought.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotThought entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 修改思绪状态
     */
    @Override
    public int updateStatus(Collection<Long> ids, DataStatus dataStatus) {
        LambdaUpdateWrapper<ThotThought> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(dataStatus == DataStatus.PUBLISH, ThotThought::getPublishTime, new Date())
            .set(ThotThought::getStatus, dataStatus.status)
            .in(ThotThought::getThoughtId, ids);
        return baseMapper.update(null, wrapper);
    }

    /**
     * 批量删除思绪信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
