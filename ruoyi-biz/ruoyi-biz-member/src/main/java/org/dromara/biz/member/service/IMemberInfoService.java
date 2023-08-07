package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberInfo;
import org.dromara.biz.member.domain.vo.MemberInfoVo;
import org.dromara.biz.member.domain.bo.MemberInfoBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 成员信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberInfoService {

    /**
     * 查询成员信息
     */
    MemberInfoVo queryById(Long memberId);

    /**
     * 查询成员信息列表
     */
    TableDataInfo<MemberInfoVo> queryPageList(MemberInfoBo bo, PageQuery pageQuery);

    /**
     * 查询成员信息列表
     */
    List<MemberInfoVo> queryList(MemberInfoBo bo);

    /**
     * 新增成员信息
     */
    Boolean insertByBo(MemberInfoBo bo);

    /**
     * 修改成员信息
     */
    Boolean updateByBo(MemberInfoBo bo);

    int updateStatus(Long appId, String status);

    /**
     * 校验并批量删除成员信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
