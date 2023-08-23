package org.dromara.app.api;

import org.dromara.app.api.domain.RemoteAppInfo;

import java.util.List;

public interface RemoteAppService {

    RemoteAppInfo getAppById(Long appId);

    List<RemoteAppInfo> getAppByIds(List<Long> appIds);

    List<RemoteAppInfo> getAppsByQuery(String query, Long appId);

}
