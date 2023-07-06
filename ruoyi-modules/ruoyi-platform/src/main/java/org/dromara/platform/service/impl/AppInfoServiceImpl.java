package org.dromara.platform.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.AppInfo;
import org.dromara.platform.domain.bo.AppInfoBo;
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
        lqw.eq(StringUtils.isNotBlank(bo.getAccessKeyId()), AppInfo::getAccessKeyId, bo.getAccessKeyId());
        lqw.eq(StringUtils.isNotBlank(bo.getSecretAccessKey()), AppInfo::getSecretAccessKey, bo.getSecretAccessKey());
        lqw.eq(StringUtils.isNotBlank(bo.getSalt()), AppInfo::getSalt, bo.getSalt());
        lqw.eq(StringUtils.isNotBlank(bo.getDomains()), AppInfo::getDomains, bo.getDomains());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), AppInfo::getDescription, bo.getDescription());
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
