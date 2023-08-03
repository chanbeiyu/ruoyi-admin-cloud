package org.dromara.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.platform.mapper.app.AppInfoMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author chanbeiyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class PlatformApplicationRunner implements ApplicationRunner {

    private final AppInfoMapper appInfoMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("初始化 Platform 配置成功");
    }

    private void initAppMaping() {
        List<AppInfo> appInfos = appInfoMapper.selectList();
        appInfos.forEach(o -> {
            CacheUtils.put(RedisKey.APP_ID_NAME, o.getAppId(), o.getAppName());
            CacheUtils.put(RedisKey.APP_CODE_NAME, o.getAppCode(), o.getAppName());
        });
    }

}
