package org.dromara.biz.admin.service.search;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.app.api.RemoteAppService;
import org.dromara.app.api.domain.RemoteAppInfo;
import org.dromara.biz.admin.domain.search.SearchVo;
import org.dromara.basal.thoughts.domain.ThotStyle;
import org.dromara.basal.thoughts.domain.ThotThought;
import org.dromara.basal.thoughts.mapper.ThotStyleMapper;
import org.dromara.basal.thoughts.mapper.ThotThoughtMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 搜索信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-03
 */
@Service
@RequiredArgsConstructor
public class SearchService {

    private final ThotThoughtMapper thotThoughtMapper;
    private final ThotStyleMapper thotStyleMapper;

    @DubboReference
    private final RemoteAppService remoteAppService;

    public TableDataInfo<SearchVo> searchThoughtList(String query, Long appId, boolean cascade) {
        List<SearchVo> thoughts = searchThought(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<Long, List<SearchVo>> thoughtMap = thoughts.stream().
                collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(thoughtMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(thoughts);
    }

    public TableDataInfo<SearchVo> searchStyleList(String query, Long appId, boolean cascade) {
        List<SearchVo> thotStyles = searchThotStyle(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<Long, List<SearchVo>> thotStyleMap = thotStyles.stream().
                collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(thotStyleMap.get(app.getValue()));
            });
        }
        return TableDataInfo.build(thotStyles);
    }

    private SearchVo searchApp(Long appId) {
        RemoteAppInfo appInfo = remoteAppService.getAppById(appId);
        return SearchVo.builder()
            .value(appInfo.getAppId())
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build();
    }

    private List<SearchVo> searchApp(List<Long> appIds) {
        List<RemoteAppInfo> apps = remoteAppService.getAppByIds(appIds);
        return apps.stream().map(appInfo -> SearchVo.builder()
            .value(appInfo.getAppId())
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build()).toList();
    }

    private List<SearchVo> searchApp(String query, Long appId) {
        LambdaQueryWrapper<RemoteAppInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, RemoteAppInfo::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.like(RemoteAppInfo::getAppId, query)
                .or().like(RemoteAppInfo::getAppName, query)
                .or().like(RemoteAppInfo::getAppCode, query);
        }

        List<RemoteAppInfo> apps = remoteAppService.getAppsByQuery(query, appId);
        return apps.stream().map(appInfo -> SearchVo.builder()
            .value(appInfo.getAppId())
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build()).toList();
    }

    private List<SearchVo> searchThought(String query, Long appId) {

        LambdaQueryWrapper<ThotThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, ThotThought::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(ThotThought::getThoughtId, query)
                .or().like(ThotThought::getTitle, query)
                .or().like(ThotThought::getCode, query));
        }

        return thotThoughtMapper.selectList(lqw, thotThought -> {
            return SearchVo.builder()
                .value(thotThought.getThoughtId())
                .code(thotThought.getCode())
                .label(thotThought.getTitle())
                .parentValue(thotThought.getAppId())
                .build();
        });
    }

    private List<SearchVo> searchThotStyle(String query, Long appId) {

        LambdaQueryWrapper<ThotStyle> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, ThotStyle::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(ThotStyle::getStyleId, query)
                .or().like(ThotStyle::getStyleCode, query)
                .or().like(ThotStyle::getStyleName, query));
        }

        return thotStyleMapper.selectList(lqw, thotStyle -> {
            return SearchVo.builder()
                .value(thotStyle.getStyleId())
                .code(thotStyle.getStyleCode())
                .label(thotStyle.getStyleName())
                .parentValue(thotStyle.getAppId())
                .build();
        });
    }

}
