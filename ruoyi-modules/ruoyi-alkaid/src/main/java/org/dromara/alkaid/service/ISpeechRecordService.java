package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.bo.SpeechRecordBo;
import org.dromara.alkaid.domain.vo.SpeechRecordVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 语音记录管理Service接口
 *
 * @author beiyu
 */
public interface ISpeechRecordService {

    /**
     * 查询语音记录管理
     */
    SpeechRecordVo queryById(Long id);

    /**
     * 查询语音记录管理列表
     */
    TableDataInfo<SpeechRecordVo> queryPageList(SpeechRecordBo bo, PageQuery pageQuery);

    /**
     * 查询语音记录管理列表
     */
    List<SpeechRecordVo> queryList(SpeechRecordBo bo);

    /**
     * 新增语音记录管理
     */
    Boolean insertByBo(SpeechRecordBo bo);

    /**
     * 修改语音记录管理
     */
    Boolean updateByBo(SpeechRecordBo bo);

    /**
     * 校验并批量删除语音记录管理信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
