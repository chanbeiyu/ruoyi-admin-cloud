package org.dromara.platform.service;

import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.platform.domain.search.SearchVo;

import java.util.Map;

/**
 * 搜索信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-31
 */
public interface ISearchService {

    TableDataInfo<SearchVo> searchAppList(String query, Long appId);

    TableDataInfo<SearchVo> searchSubjectList(String query, Long appId, boolean cascade);

    TableDataInfo<SearchVo> searchStyleList(String query, Long appId, boolean cascade);
}
