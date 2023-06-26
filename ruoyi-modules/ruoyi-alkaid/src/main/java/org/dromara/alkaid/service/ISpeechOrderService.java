package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.bo.SpeechOrderBo;
import org.dromara.alkaid.domain.vo.SpeechOrderVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 语音订单Service接口
 *
 * @author beiyu
 */
public interface ISpeechOrderService {

    /**
     * 查询语音订单
     */
    SpeechOrderVo queryById(Long id);

    /**
     * 查询语音订单列表
     */
    TableDataInfo<SpeechOrderVo> queryPageList(SpeechOrderBo bo, PageQuery pageQuery);

    /**
     * 查询语音订单列表
     */
    List<SpeechOrderVo> queryList(SpeechOrderBo bo);

    /**
     * 新增语音订单
     */
    Boolean insertByBo(SpeechOrderBo bo);

    /**
     * 修改语音订单
     */
    Boolean updateByBo(SpeechOrderBo bo);

    /**
     * 校验并批量删除语音订单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
