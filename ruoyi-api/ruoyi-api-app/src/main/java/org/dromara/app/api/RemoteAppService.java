package org.dromara.app.api;

import org.dromara.app.api.domain.RemoteAppInfo;
import org.dromara.common.core.exception.ServiceException;

public interface RemoteAppService {

    RemoteAppInfo getAppById(Long appId) throws ServiceException;

}
