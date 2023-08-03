package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberCoinsRecord;
import org.dromara.biz.member.domain.vo.MemberCoinsRecordVo;
import org.dromara.biz.member.domain.bo.MemberCoinsRecordBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 代币记录信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberCoinsRecordService {

    /**
     * 查询代币记录信息
     */
    MemberCoinsRecordVo queryById(Long recordId);

    /**
     * 查询代币记录信息列表
     */
    TableDataInfo<MemberCoinsRecordVo> queryPageList(MemberCoinsRecordBo bo, PageQuery pageQuery);

    /**
     * 查询代币记录信息列表
     */
    List<MemberCoinsRecordVo> queryList(MemberCoinsRecordBo bo);

    /**
     * 新增代币记录信息
     */
    Boolean insertByBo(MemberCoinsRecordBo bo);

    /**
     * 修改代币记录信息
     */
    Boolean updateByBo(MemberCoinsRecordBo bo);

    /**
     * 校验并批量删除代币记录信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
