package org.dromara.basal.platform.dubbo.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.app.api.RemoteAppService;
import org.dromara.app.api.domain.RemoteAppInfo;
import org.dromara.basal.platform.domain.app.AppInfo;
import org.dromara.basal.platform.mapper.app.AppInfoMapper;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@DubboService
public class RemoteAppServiceImpl implements RemoteAppService {

    private final AppInfoMapper appInfoMapper;

    @Override
    public RemoteAppInfo getAppById(Long appId) throws ServiceException {
        return appInfoMapper.selectVoById(appId, RemoteAppInfo.class);
    }

    @Override
    public List<RemoteAppInfo> getAppByIds(List<Long> appIds) throws ServiceException {
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.in(AppInfo::getAppId, appIds);
        return appInfoMapper.selectVoList(lqw, RemoteAppInfo.class);
    }

    @Override
    public List<RemoteAppInfo> getAppsByQuery(String query, Long appId) throws ServiceException {
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, AppInfo::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.like(AppInfo::getAppId, query)
                .or().like(AppInfo::getAppName, query)
                .or().like(AppInfo::getAppCode, query);
        }

        return appInfoMapper.selectVoList(lqw, RemoteAppInfo.class);
    }

}
