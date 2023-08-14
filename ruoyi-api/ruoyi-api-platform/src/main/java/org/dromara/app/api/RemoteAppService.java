package org.dromara.app.api;

import org.dromara.app.api.domain.RemoteAppInfo;
import org.dromara.common.core.exception.ServiceException;

import java.util.List;

public interface RemoteAppService {

    RemoteAppInfo getAppById(Long appId) throws ServiceException;

    List<RemoteAppInfo> getAppByIds(List<Long> appIds) throws ServiceException;


    List<RemoteAppInfo> getAppsByQuery(String query, Long appId) throws ServiceException;

}
