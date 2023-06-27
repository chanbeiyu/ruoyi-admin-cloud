package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.bo.SpeechBillingBo;
import org.dromara.alkaid.domain.vo.SpeechBillingVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 语音计费Service接口
 *
 * @author beiyu
 */
public interface ISpeechBillingService {

    /**
     * 查询语音计费
     */
    SpeechBillingVo queryById(Long id);

    /**
     * 查询语音计费列表
     */
    TableDataInfo<SpeechBillingVo> queryPageList(SpeechBillingBo bo, PageQuery pageQuery);

    /**
     * 查询语音计费列表
     */
    List<SpeechBillingVo> queryList(SpeechBillingBo bo);

    /**
     * 新增语音计费
     */
    Boolean insertByBo(SpeechBillingBo bo);

    /**
     * 修改语音计费
     */
    Boolean updateByBo(SpeechBillingBo bo);

    /**
     * 校验并批量删除语音计费信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
