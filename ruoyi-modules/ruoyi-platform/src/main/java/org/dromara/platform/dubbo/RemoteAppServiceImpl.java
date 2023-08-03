package org.dromara.platform.dubbo;

import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.MapstructUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@DubboService
public class RemoteAppServiceImpl implements RemoteAppService {

    private IAppInfoService appInfoService;

    @Override
    public RemoteAppInfo getAppById(Long appId) throws ServiceException {
        AppInfoVo appInfoVo = appInfoService.queryById(appId);
        return MapstructUtils.convert(appInfoVo, RemoteAppInfo.class);
    }

}
