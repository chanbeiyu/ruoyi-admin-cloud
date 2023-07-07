package org.dromara.platform.service;

import org.dromara.platform.domain.bo.AppInfoBo;
import org.dromara.platform.domain.vo.AppInfoVo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 应用信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface IAppInfoService {

    /**
     * 查询应用信息
     */
    AppInfoVo queryById(Long appId);

    /**
     * 查询应用信息列表
     */
    TableDataInfo<AppInfoVo> queryPageList(AppInfoBo bo, PageQuery pageQuery);

    /**
     * 查询应用信息列表
     */
    List<AppInfoVo> queryList(AppInfoBo bo);

    /**
     * 新增应用信息
     */
    Boolean insertByBo(AppInfoBo bo);

    /**
     * 修改应用信息
     */
    Boolean updateByBo(AppInfoBo bo);

    int updateStatus(Long appId, String status);

    /**
     * 校验并批量删除应用信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
