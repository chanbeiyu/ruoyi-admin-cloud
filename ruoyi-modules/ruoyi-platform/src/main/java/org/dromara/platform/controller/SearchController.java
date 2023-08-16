package org.dromara.platform.controller;

import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
import org.dromara.platform.domain.search.SearchVo;
import org.dromara.platform.service.ISearchService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 应用信息
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping
public class SearchController extends BaseController {

    private final ISearchService searchService;

    /**
     * 查询应用列表，用于搜索
     */
    //@SaCheckPermission("platfrom:search:app")
    @GetMapping("/app/search/info")
    public TableDataInfo<SearchVo> searchApp(@RequestParam(required = false) String query,
                                             @RequestParam(required = false) Long appId,
                                             @RequestParam(required = false, defaultValue = "false") boolean cascade) {
        return searchService.searchAppList(query, appId);
    }


    /**
     * 查询主题列表，用于搜索/选择
     */
    //@SaCheckPermission("platfrom:search:subject")
    @GetMapping("/social/search/subject")
    public TableDataInfo<SearchVo> searchSubject(@RequestParam(required = false) String query,
                                                 @RequestParam(required = false) Long appId,
                                                 @RequestParam(required = false, defaultValue = "false") boolean cascade) {
        return searchService.searchSubjectList(query, appId, cascade);
    }

    /**
     * 查询主题列表，用于搜索/选择
     */
    //@SaCheckPermission("platfrom:search:style")
    @GetMapping("/member/search/info")
    public TableDataInfo<SearchVo> searchMemberInfo(@RequestParam(required = false) String query,
                                                    @RequestParam(required = false) Long appId,
                                                    @RequestParam(required = false, defaultValue = "false") boolean cascade) {
        return searchService.searchMemberInfo(query, appId, cascade);
    }

    /**
     * 查询主题列表，用于搜索/选择
     */
    //@SaCheckPermission("platfrom:search:style")
    @GetMapping("/member/search/type")
    public TableDataInfo<SearchVo> searchMemberType(@RequestParam(required = false) String query,
                                                    @RequestParam(required = false) Long appId,
                                                    @RequestParam(required = false, defaultValue = "false") boolean cascade) {
        return searchService.searchMemberType(query, appId, cascade);
    }

}
