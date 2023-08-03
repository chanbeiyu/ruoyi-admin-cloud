package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberPointsRecord;
import org.dromara.biz.member.domain.vo.MemberPointsRecordVo;
import org.dromara.biz.member.domain.bo.MemberPointsRecordBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员积分记录Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberPointsRecordService {

    /**
     * 查询会员积分记录
     */
    MemberPointsRecordVo queryById(Long recordId);

    /**
     * 查询会员积分记录列表
     */
    TableDataInfo<MemberPointsRecordVo> queryPageList(MemberPointsRecordBo bo, PageQuery pageQuery);

    /**
     * 查询会员积分记录列表
     */
    List<MemberPointsRecordVo> queryList(MemberPointsRecordBo bo);

    /**
     * 新增会员积分记录
     */
    Boolean insertByBo(MemberPointsRecordBo bo);

    /**
     * 修改会员积分记录
     */
    Boolean updateByBo(MemberPointsRecordBo bo);

    /**
     * 校验并批量删除会员积分记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
