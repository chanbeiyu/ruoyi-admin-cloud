package org.dromara.platform.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.ThotThoughtBo;
import org.dromara.platform.domain.vo.ThotThoughtVo;
import org.dromara.platform.domain.ThotThought;
import org.dromara.platform.mapper.ThotThoughtMapper;
import org.dromara.platform.service.IThotThoughtService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

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
    public ThotThoughtVo queryById(Long thoughtId){
        return baseMapper.selectVoById(thoughtId);
    }

    /**
     * 查询思绪信息列表
     */
    @Override
    public TableDataInfo<ThotThoughtVo> queryPageList(ThotThoughtBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotThought> lqw = buildQueryWrapper(bo);
        Page<ThotThoughtVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询思绪信息列表
     */
    @Override
    public List<ThotThoughtVo> queryList(ThotThoughtBo bo) {
        LambdaQueryWrapper<ThotThought> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThotThought> buildQueryWrapper(ThotThoughtBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotThought::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getCode()), ThotThought::getCode, bo.getCode());
        lqw.eq(StringUtils.isNotBlank(bo.getMainImg()), ThotThought::getMainImg, bo.getMainImg());
        lqw.eq(StringUtils.isNotBlank(bo.getBannerImg()), ThotThought::getBannerImg, bo.getBannerImg());
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), ThotThought::getTitle, bo.getTitle());
        lqw.eq(bo.getTitleStyle() != null, ThotThought::getTitleStyle, bo.getTitleStyle());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), ThotThought::getContent, bo.getContent());
        lqw.eq(bo.getCententStyle() != null, ThotThought::getCententStyle, bo.getCententStyle());
        lqw.eq(bo.getStatus() != null, ThotThought::getStatus, bo.getStatus());
        lqw.eq(bo.getPublishTime() != null, ThotThought::getPublishTime, bo.getPublishTime());
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
    private void validEntityBeforeSave(ThotThought entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除思绪信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
