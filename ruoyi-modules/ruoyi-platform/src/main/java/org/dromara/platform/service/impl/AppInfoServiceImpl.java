package org.dromara.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.AppInfo;
import org.dromara.platform.domain.bo.AppExtendBo;
import org.dromara.platform.domain.bo.AppInfoBo;
import org.dromara.platform.service.IAppExtendService;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.vo.AppInfoVo;
import org.dromara.platform.mapper.AppInfoMapper;
import org.dromara.platform.service.IAppInfoService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
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

    private final AppInfoMapper baseMapper;
    private final IAppExtendService appExtendService;

    /**
     * 查询应用信息
     */
    @Override
    public AppInfoVo queryById(Long appId){
        return baseMapper.selectVoById(appId);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public TableDataInfo<AppInfoVo> queryPageList(AppInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppInfo> lqw = buildQueryWrapper(bo);
        Page<AppInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<AppInfoVo> queryList(AppInfoBo bo) {
        LambdaQueryWrapper<AppInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
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
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
            AppExtendBo extendBo = new AppExtendBo();
            extendBo.setAppId(add.getAppId());
            appExtendService.insertByBo(extendBo);
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
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 修改APP状态
     *
     * @param appId APPID
     * @param status 状态
     * @return 结果
     */
    @Override
    public int updateStatus(Long appId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<AppInfo>()
                .set(AppInfo::getStatus, status)
                .eq(AppInfo::getAppId, appId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
