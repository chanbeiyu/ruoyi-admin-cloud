package org.dromara.biz.admin.service;

import org.dromara.biz.admin.domain.search.SearchVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;

/**
 * 搜索信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-31
 */
public interface ISearchService {

    TableDataInfo<SearchVo> searchThoughtList(String query, Long appId, boolean cascade);

    TableDataInfo<SearchVo> searchStyleList(String query, Long appId, boolean cascade);

}
