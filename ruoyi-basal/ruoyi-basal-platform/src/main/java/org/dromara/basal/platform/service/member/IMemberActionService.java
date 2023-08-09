package org.dromara.basal.platform.service.member;

import org.dromara.basal.platform.domain.member.vo.MemberActionVo;
import org.dromara.basal.platform.domain.member.bo.MemberActionBo;
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
public interface IMemberActionService {

    /**
     * 查询会员积分
     */
    MemberActionVo queryById(Long actionId);

    /**
     * 查询会员积分列表
     */
    TableDataInfo<MemberActionVo> queryPageList(MemberActionBo bo, PageQuery pageQuery);

    /**
     * 查询会员积分列表
     */
    List<MemberActionVo> queryList(MemberActionBo bo);

    /**
     * 新增会员积分
     */
    Boolean insertByBo(MemberActionBo bo);

    /**
     * 修改会员积分
     */
    Boolean updateByBo(MemberActionBo bo);

    /**
     * 校验并批量删除会员积分信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
