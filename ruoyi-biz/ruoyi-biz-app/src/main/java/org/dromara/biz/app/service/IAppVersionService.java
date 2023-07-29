package org.dromara.biz.app.service;

import org.dromara.biz.app.domain.vo.AppVersionVo;
import org.dromara.biz.app.domain.bo.AppVersionBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 应用版本信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IAppVersionService {

    /**
     * 查询应用版本信息
     */
    AppVersionVo queryById(Long versionId);

    /**
     * 查询应用版本信息列表
     */
    TableDataInfo<AppVersionVo> queryPageList(AppVersionBo bo, PageQuery pageQuery);

    /**
     * 查询应用版本信息列表
     */
    List<AppVersionVo> queryList(AppVersionBo bo);

    /**
     * 新增应用版本信息
     */
    Boolean insertByBo(AppVersionBo bo);

    /**
     * 修改应用版本信息
     */
    Boolean updateByBo(AppVersionBo bo);

    /**
     * 校验并批量删除应用版本信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
