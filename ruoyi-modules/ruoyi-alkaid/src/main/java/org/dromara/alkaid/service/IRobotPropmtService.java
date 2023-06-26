package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.bo.RobotPropmtBo;
import org.dromara.alkaid.domain.vo.RobotPropmtVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * Ai PropmtService接口
 *
 * @author beiyu
 */
public interface IRobotPropmtService {

    /**
     * 查询Ai Propmt
     */
    RobotPropmtVo queryById(Long id);

    /**
     * 查询Ai Propmt列表
     */
    TableDataInfo<RobotPropmtVo> queryPageList(RobotPropmtBo bo, PageQuery pageQuery);

    /**
     * 查询Ai Propmt列表
     */
    List<RobotPropmtVo> queryList(RobotPropmtBo bo);

    /**
     * 新增Ai Propmt
     */
    Boolean insertByBo(RobotPropmtBo bo);

    /**
     * 修改Ai Propmt
     */
    Boolean updateByBo(RobotPropmtBo bo);

    /**
     * 校验并批量删除Ai Propmt信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
