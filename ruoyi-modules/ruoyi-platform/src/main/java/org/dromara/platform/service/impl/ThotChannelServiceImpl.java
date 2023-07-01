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
import org.dromara.platform.domain.bo.ThotChannelBo;
import org.dromara.platform.domain.vo.ThotChannelVo;
import org.dromara.platform.domain.ThotChannel;
import org.dromara.platform.mapper.ThotChannelMapper;
import org.dromara.platform.service.IThotChannelService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 频道信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotChannelServiceImpl implements IThotChannelService {

    private final ThotChannelMapper baseMapper;

    /**
     * 查询频道信息
     */
    @Override
    public ThotChannelVo queryById(Long channelId){
        return baseMapper.selectVoById(channelId);
    }

    /**
     * 查询频道信息列表
     */
    @Override
    public TableDataInfo<ThotChannelVo> queryPageList(ThotChannelBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotChannel> lqw = buildQueryWrapper(bo);
        Page<ThotChannelVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询频道信息列表
     */
    @Override
    public List<ThotChannelVo> queryList(ThotChannelBo bo) {
        LambdaQueryWrapper<ThotChannel> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThotChannel> buildQueryWrapper(ThotChannelBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotChannel> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotChannel::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getChannelCode()), ThotChannel::getChannelCode, bo.getChannelCode());
        lqw.like(StringUtils.isNotBlank(bo.getChannelName()), ThotChannel::getChannelName, bo.getChannelName());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ThotChannel::getDescription, bo.getDescription());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            ThotChannel::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增频道信息
     */
    @Override
    public Boolean insertByBo(ThotChannelBo bo) {
        ThotChannel add = MapstructUtils.convert(bo, ThotChannel.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setChannelId(add.getChannelId());
        }
        return flag;
    }

    /**
     * 修改频道信息
     */
    @Override
    public Boolean updateByBo(ThotChannelBo bo) {
        ThotChannel update = MapstructUtils.convert(bo, ThotChannel.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotChannel entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除频道信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
