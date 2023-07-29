package org.dromara.biz.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.app.domain.AppVersion;
import org.dromara.biz.app.domain.bo.AppVersionBo;
import org.dromara.biz.app.domain.vo.AppVersionVo;
import org.dromara.biz.app.mapper.AppVersionMapper;
import org.dromara.biz.app.service.IAppVersionService;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 应用版本信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppVersionServiceImpl implements IAppVersionService {

    private final AppVersionMapper baseMapper;

    /**
     * 查询应用版本信息
     */
    @Override
    public AppVersionVo queryById(Long versionId) {
        return baseMapper.selectVoById(versionId);
    }

    /**
     * 查询应用版本信息列表
     */
    @Override
    public TableDataInfo<AppVersionVo> queryPageList(AppVersionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppVersion> lqw = buildQueryWrapper(bo);
        Page<AppVersionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用版本信息列表
     */
    @Override
    public List<AppVersionVo> queryList(AppVersionBo bo) {
        LambdaQueryWrapper<AppVersion> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AppVersion> buildQueryWrapper(AppVersionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppVersion> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppVersion::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppVersion::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getVersion()), AppVersion::getVersion, bo.getVersion());
        lqw.eq(StringUtils.isNotBlank(bo.getBuildVersion()), AppVersion::getBuildVersion, bo.getBuildVersion());
        lqw.eq(bo.getForced() != null, AppVersion::getForced, bo.getForced());
        lqw.eq(bo.getPublishTime() != null, AppVersion::getPublishTime, bo.getPublishTime());
        return lqw;
    }

    /**
     * 新增应用版本信息
     */
    @Override
    public Boolean insertByBo(AppVersionBo bo) {
        AppVersion add = MapstructUtils.convert(bo, AppVersion.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setVersionId(add.getVersionId());
        }
        return flag;
    }

    /**
     * 修改应用版本信息
     */
    @Override
    public Boolean updateByBo(AppVersionBo bo) {
        AppVersion update = MapstructUtils.convert(bo, AppVersion.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppVersion entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用版本信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
