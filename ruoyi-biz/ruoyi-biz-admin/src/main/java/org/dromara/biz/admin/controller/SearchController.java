package org.dromara.biz.admin.controller;

import lombok.RequiredArgsConstructor;
import org.dromara.biz.admin.domain.search.SearchVo;
import org.dromara.biz.admin.service.ISearchService;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.web.core.BaseController;
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
@RequestMapping("/thoughts")
public class SearchController extends BaseController {

    private final ISearchService searchService;

    /**
     * 查询主题列表，用于搜索/选择
     */
    //@SaCheckPermission("platfrom:search:style")
    @GetMapping("/thought/search")
    public TableDataInfo<SearchVo> searchThought(@RequestParam(required = false) String query,
                                                 @RequestParam(required = false) Long appId,
                                                 @RequestParam(required = false, defaultValue = "false") boolean cascade) {
        return searchService.searchThoughtList(query, appId, cascade);
    }

    /**
     * 查询主题列表，用于搜索/选择
     */
    //@SaCheckPermission("platfrom:search:style")
    @GetMapping("/style/search")
    public TableDataInfo<SearchVo> searchStyle(@RequestParam(required = false) String query,
                                               @RequestParam(required = false) Long appId,
                                               @RequestParam(required = false, defaultValue = "false") boolean cascade) {
        return searchService.searchStyleList(query, appId, cascade);
    }

}
