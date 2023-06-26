package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidMessage;
import org.dromara.alkaid.domain.vo.AlkaidMessageVo;
import org.dromara.alkaid.domain.bo.AlkaidMessageBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 用户消息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidMessageService {

    /**
     * 查询用户消息
     */
    AlkaidMessageVo queryById(Long messageId);

    /**
     * 查询用户消息列表
     */
    TableDataInfo<AlkaidMessageVo> queryPageList(AlkaidMessageBo bo, PageQuery pageQuery);

    /**
     * 查询用户消息列表
     */
    List<AlkaidMessageVo> queryList(AlkaidMessageBo bo);

    /**
     * 新增用户消息
     */
    Boolean insertByBo(AlkaidMessageBo bo);

    /**
     * 修改用户消息
     */
    Boolean updateByBo(AlkaidMessageBo bo);

    /**
     * 校验并批量删除用户消息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
