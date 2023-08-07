package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberPoints;
import org.dromara.biz.member.domain.vo.MemberPointsVo;
import org.dromara.biz.member.domain.bo.MemberPointsBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员积分Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberPointsService {

    /**
     * 查询会员积分
     */
    MemberPointsVo queryById(Long id);

    /**
     * 查询会员积分列表
     */
    TableDataInfo<MemberPointsVo> queryPageList(MemberPointsBo bo, PageQuery pageQuery);

    /**
     * 查询会员积分列表
     */
    List<MemberPointsVo> queryList(MemberPointsBo bo);

    /**
     * 新增会员积分
     */
    Boolean insertByBo(MemberPointsBo bo);

    /**
     * 修改会员积分
     */
    Boolean updateByBo(MemberPointsBo bo);

    int updateStatus(Long appId, Integer status);

    /**
     * 校验并批量删除会员积分信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
