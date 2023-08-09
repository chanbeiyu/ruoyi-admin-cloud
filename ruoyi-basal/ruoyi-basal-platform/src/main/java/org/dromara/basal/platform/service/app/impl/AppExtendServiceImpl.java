package org.dromara.basal.platform.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.basal.platform.domain.app.AppExtend;
import org.dromara.basal.platform.domain.app.bo.AppExtendBo;
import org.dromara.basal.platform.domain.app.vo.AppExtendVo;
import org.dromara.basal.platform.mapper.app.AppExtendMapper;
import org.dromara.basal.platform.service.app.IAppExtendService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 应用扩展信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppExtendServiceImpl implements IAppExtendService {

    private final AppExtendMapper baseMapper;

    /**
     * 查询应用扩展信息
     */
    @Override
    public AppExtendVo queryById(Long appId) {
        return baseMapper.selectVoById(appId);
    }

    /**
     * 查询应用扩展信息列表
     */
    @Override
    public TableDataInfo<AppExtendVo> queryPageList(AppExtendBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppExtend> lqw = buildQueryWrapper(bo)
            .select(AppExtend.class, f -> !f.getColumn().equals("value"));
        Page<AppExtendVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用扩展信息列表
     */
    @Override
    public List<AppExtendVo> queryList(AppExtendBo bo) {
        LambdaQueryWrapper<AppExtend> lqw = buildQueryWrapper(bo)
            .select(AppExtend.class, f -> !f.getColumn().equals("value"));
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AppExtend> buildQueryWrapper(AppExtendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppExtend> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppExtend::getAppId, bo.getAppId());
        lqw.like(StringUtils.isNotBlank(bo.getKey()), AppExtend::getKey, bo.getKey());
        lqw.like(StringUtils.isNotBlank(bo.getLabel()), AppExtend::getLabel, bo.getLabel());
        lqw.like(StringUtils.isNotBlank(bo.getVersion()), AppExtend::getVersion, bo.getVersion());
        return lqw;
    }

    /**
     * 新增应用扩展信息
     */
    @Override
    public Boolean insertByBo(AppExtendBo bo) {
        AppExtend add = MapstructUtils.convert(bo, AppExtend.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
        }
        return flag;
    }

    /**
     * 修改应用扩展信息
     */
    @Override
    public Boolean updateByBo(AppExtendBo bo) {
        AppExtend update = MapstructUtils.convert(bo, AppExtend.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppExtend entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用扩展信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
