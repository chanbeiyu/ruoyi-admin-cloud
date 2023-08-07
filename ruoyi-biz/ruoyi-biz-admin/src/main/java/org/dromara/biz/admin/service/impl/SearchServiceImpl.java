package org.dromara.biz.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.biz.admin.domain.search.SearchVo;
import org.dromara.biz.admin.service.ISearchService;
import org.dromara.biz.app.domain.AppInfo;
import org.dromara.biz.member.domain.MemberInfo;
import org.dromara.biz.member.domain.MemberType;
import org.dromara.biz.member.mapper.MemberInfoMapper;
import org.dromara.biz.member.mapper.MemberTypeMapper;
import org.dromara.biz.social.domain.SocialSubject;
import org.dromara.biz.thoughts.domain.ThotStyle;
import org.dromara.biz.thoughts.domain.ThotThought;
import org.dromara.biz.app.mapper.AppInfoMapper;
import org.dromara.biz.social.mapper.SocialSubjectMapper;
import org.dromara.biz.thoughts.mapper.ThotStyleMapper;
import org.dromara.biz.thoughts.mapper.ThotThoughtMapper;
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
public class SearchServiceImpl implements ISearchService {

    private final AppInfoMapper appInfoMapper;
    private final ThotThoughtMapper thotThoughtMapper;
    private final ThotStyleMapper thotStyleMapper;
    private final SocialSubjectMapper socialSubjectMapper;

    private final MemberInfoMapper memberInfoMapper;
    private final MemberTypeMapper memberTypeMapper;

    @Override
    public TableDataInfo<SearchVo> searchAppList(String query, Long appId) {
        return TableDataInfo.build(searchApp(query, appId));
    }

    @Override
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

    @Override
    public TableDataInfo<SearchVo> searchSubjectList(String query, Long appId, boolean cascade) {
        List<SearchVo> subjects = searchSubject(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<Long, List<SearchVo>> subjectMap = subjects.stream().
                collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(subjectMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(subjects);
    }

    @Override
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

    @Override
    public TableDataInfo<SearchVo> searchMemberInfo(String query, Long appId, boolean cascade) {
        List<SearchVo> memberInfos = searchMemberInfo(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<Long, List<SearchVo>> memberInfoMap = memberInfos.stream().
                collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(memberInfoMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(memberInfos);
    }

    @Override
    public TableDataInfo<SearchVo> searchMemberType(String query, Long appId, boolean cascade) {
        List<SearchVo> memberTypes = searchMemberType(query, appId);
        if (cascade) {
            List<SearchVo> apps = searchApp(null, appId);
            Map<Long, List<SearchVo>> memberTypeMap = memberTypes.stream().
                collect(Collectors.groupingBy(SearchVo::getParentValue));
            apps.forEach(app -> {
                app.setChildren(memberTypeMap.get(app.getValue()));
            });
            return TableDataInfo.build(apps);
        }
        return TableDataInfo.build(memberTypes);
    }

    private SearchVo searchApp(Long appId) {
        return appInfoMapper.selectVoById(appId, appInfo -> SearchVo.builder()
            .value(appInfo.getAppId())
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build());
    }

    private List<SearchVo> searchApp(List<Long> appIds) {
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.in(AppInfo::getAppId, appIds);
        return appInfoMapper.selectVoList(lqw, appInfo -> SearchVo.builder()
            .value(appInfo.getAppId())
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build());
    }

    private List<SearchVo> searchApp(String query, Long appId) {
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, AppInfo::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.like(AppInfo::getAppId, query)
                .or().like(AppInfo::getAppName, query)
                .or().like(AppInfo::getAppCode, query);
        }

        return appInfoMapper.selectVoList(lqw, appInfo -> SearchVo.builder()
            .value(appInfo.getAppId())
            .code(appInfo.getAppCode())
            .label(appInfo.getAppName())
            .build());
    }

    private List<SearchVo> searchThought(String query, Long appId) {

        LambdaQueryWrapper<ThotThought> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, ThotThought::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(ThotThought::getThoughtId, query)
                .or().like(ThotThought::getTitle, query)
                .or().like(ThotThought::getCode, query));
        }

        return thotThoughtMapper.selectVoList(lqw, thotThought -> SearchVo.builder()
            .value(thotThought.getThoughtId())
            .code(thotThought.getCode())
            .label(thotThought.getTitle())
            .parentValue(thotThought.getAppId())
            .build());
    }

    private List<SearchVo> searchSubject(String query, Long appId) {

        LambdaQueryWrapper<SocialSubject> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, SocialSubject::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(SocialSubject::getSubjectId, query)
                .or().like(SocialSubject::getSubjectName, query)
                .or().like(SocialSubject::getSubjectCode, query));
        }

        return socialSubjectMapper.selectVoList(lqw, socialSubject -> SearchVo.builder()
            .value(socialSubject.getSubjectId())
            .code(socialSubject.getSubjectCode())
            .label(socialSubject.getSubjectName())
            .parentValue(socialSubject.getAppId())
            .build());
    }

    private List<SearchVo> searchThotStyle(String query, Long appId) {

        LambdaQueryWrapper<ThotStyle> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, ThotStyle::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(ThotStyle::getStyleId, query)
                .or().like(ThotStyle::getStyleCode, query)
                .or().like(ThotStyle::getStyleName, query));
        }

        return thotStyleMapper.selectVoList(lqw, thotStyle -> SearchVo.builder()
            .value(thotStyle.getStyleId())
            .code(thotStyle.getStyleCode())
            .label(thotStyle.getStyleName())
            .parentValue(thotStyle.getAppId())
            .build());
    }

    private List<SearchVo> searchMemberType(String query, Long appId) {

        LambdaQueryWrapper<MemberType> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, MemberType::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(MemberType::getTypeId, query)
                .or().like(MemberType::getTypeCode, query)
                .or().like(MemberType::getTypeName, query));
        }

        return memberTypeMapper.selectVoList(lqw, memberType -> SearchVo.builder()
            .value(memberType.getTypeId())
            .code(memberType.getTypeCode())
            .label(memberType.getTypeName())
            .parentValue(memberType.getAppId())
            .build());
    }

    private List<SearchVo> searchMemberInfo(String query, Long appId) {

        LambdaQueryWrapper<MemberInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(appId != null, MemberInfo::getAppId, appId);

        if (StringUtils.isNotBlank(query)) {
            lqw.and(i -> i.like(MemberInfo::getMemberId, query)
                .or().like(MemberInfo::getUnionId, query)
                .or().like(MemberInfo::getNickName, query));
        }

        return memberInfoMapper.selectVoList(lqw, memberInfo -> SearchVo.builder()
            .value(memberInfo.getMemberId())
            .code(memberInfo.getUnionId())
            .label(memberInfo.getNickName())
            .parentValue(memberInfo.getAppId())
            .build());
    }

}
