package org.dromara.basal.platform.service.app.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.basal.platform.domain.app.AppInfo;
import org.dromara.basal.platform.domain.app.bo.AppExtendBo;
import org.dromara.basal.platform.domain.app.bo.AppInfoBo;
import org.dromara.basal.platform.domain.app.vo.AppInfoVo;
import org.dromara.basal.platform.mapper.app.AppInfoMapper;
import org.dromara.basal.platform.service.app.IAppExtendService;
import org.dromara.basal.platform.service.app.IAppInfoService;
import org.dromara.basal.platform.constant.RedisKey;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 应用信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class AppInfoServiceImpl implements IAppInfoService {

    private final AppInfoMapper appInfoMapper;
    private final IAppExtendService appExtendService;

    /**
     * 查询应用信息
     */
    @Override
    public AppInfoVo queryById(Long appId) {
        return appInfoMapper.selectVoById(appId);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public TableDataInfo<AppInfoVo> queryPageList(AppInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppInfo> lqw = buildQueryWrapper(bo);
        Page<AppInfoVo> result = appInfoMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<AppInfoVo> queryList(AppInfoBo bo) {
        LambdaQueryWrapper<AppInfo> lqw = buildQueryWrapper(bo);
        return appInfoMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AppInfo> buildQueryWrapper(AppInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(Objects.nonNull(bo.getAppId()), AppInfo::getAppId, bo.getAppId());
        lqw.like(StringUtils.isNotBlank(bo.getAppCode()), AppInfo::getAppCode, bo.getAppCode());
        lqw.like(StringUtils.isNotBlank(bo.getAppName()), AppInfo::getAppName, bo.getAppName());
        lqw.eq(Objects.nonNull(bo.getIsInternal()), AppInfo::getIsInternal, bo.getIsInternal());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), AppInfo::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByBo(AppInfoBo bo) {
        AppInfo add = MapstructUtils.convert(bo, AppInfo.class);
        validEntityBeforeSave(add);
        boolean flag = appInfoMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
            AppExtendBo extendBo = new AppExtendBo();
            extendBo.setAppId(add.getAppId());
            appExtendService.insertByBo(extendBo);
            CacheUtils.put(RedisKey.APP_ID_NAME, add.getAppId(), bo.getAppName());
            CacheUtils.put(RedisKey.APP_CODE_NAME, add.getAppCode(), bo.getAppName());
        }
        return flag;
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean updateByBo(AppInfoBo bo) {
        AppInfo update = MapstructUtils.convert(bo, AppInfo.class);
        validEntityBeforeSave(update);
        boolean bool = appInfoMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.APP_ID_NAME, update.getAppId(), update.getAppName());
            CacheUtils.put(RedisKey.APP_CODE_NAME, update.getAppCode(), update.getAppName());
        }
        return bool;
    }

    /**
     * 修改APP状态
     *
     * @param appId  APPID
     * @param status 状态
     * @return 结果
     */
    @Override
    public int updateStatus(Long appId, String status) {
        return appInfoMapper.update(null,
            new LambdaUpdateWrapper<AppInfo>()
                .set(AppInfo::getStatus, status)
                .eq(AppInfo::getAppId, appId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppInfo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        List<AppInfo> appInfos = appInfoMapper.selectBatchIds(ids);
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        boolean bool = appInfoMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            appInfos.forEach(o -> {
                CacheUtils.evict(RedisKey.APP_ID_NAME, o.getAppId());
                CacheUtils.evict(RedisKey.APP_CODE_NAME, o.getAppCode());
            });
        }
        return bool;
    }
}
