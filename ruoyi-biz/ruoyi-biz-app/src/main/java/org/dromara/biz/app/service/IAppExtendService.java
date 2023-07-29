package org.dromara.biz.app.service;

import org.dromara.biz.app.domain.vo.AppExtendVo;
import org.dromara.biz.app.domain.bo.AppExtendBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 应用扩展信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IAppExtendService {

    /**
     * 查询应用扩展信息
     */
    AppExtendVo queryById(Long appId);

    /**
     * 查询应用扩展信息列表
     */
    TableDataInfo<AppExtendVo> queryPageList(AppExtendBo bo, PageQuery pageQuery);

    /**
     * 查询应用扩展信息列表
     */
    List<AppExtendVo> queryList(AppExtendBo bo);

    /**
     * 新增应用扩展信息
     */
    Boolean insertByBo(AppExtendBo bo);

    /**
     * 修改应用扩展信息
     */
    Boolean updateByBo(AppExtendBo bo);

    /**
     * 校验并批量删除应用扩展信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
