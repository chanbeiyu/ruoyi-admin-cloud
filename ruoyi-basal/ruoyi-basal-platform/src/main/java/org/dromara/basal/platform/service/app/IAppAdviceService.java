package org.dromara.basal.platform.service.app;

import org.dromara.basal.platform.domain.app.vo.AppAdviceVo;
import org.dromara.basal.platform.domain.app.bo.AppAdviceBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 意见反馈信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface IAppAdviceService {

    /**
     * 查询意见反馈信息
     */
    AppAdviceVo queryById(Long adviceId);

    /**
     * 查询意见反馈信息列表
     */
    TableDataInfo<AppAdviceVo> queryPageList(AppAdviceBo bo, PageQuery pageQuery);

    /**
     * 查询意见反馈信息列表
     */
    List<AppAdviceVo> queryList(AppAdviceBo bo);

    /**
     * 新增意见反馈信息
     */
    Boolean insertByBo(AppAdviceBo bo);

    /**
     * 修改意见反馈信息
     */
    Boolean updateByBo(AppAdviceBo bo);

    /**
     * 校验并批量删除意见反馈信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
