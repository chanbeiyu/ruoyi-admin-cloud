package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.bo.AppInfoBo;
import org.dromara.alkaid.domain.vo.AppInfoVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 应用信息Service接口
 *
 * @author beiyu
 */
public interface IAppInfoService {

    /**
     * 查询应用信息
     */
    AppInfoVo queryById(String code);

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

    Boolean insertOrUpdate(AppInfoBo bo);

    /**
     * 校验并批量删除应用信息信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
