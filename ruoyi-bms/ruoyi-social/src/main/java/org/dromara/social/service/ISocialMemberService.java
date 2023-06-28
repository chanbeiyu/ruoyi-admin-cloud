package org.dromara.social.service;

import org.dromara.social.domain.SocialMember;
import org.dromara.social.domain.vo.SocialMemberVo;
import org.dromara.social.domain.bo.SocialMemberBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 成员信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialMemberService {

    /**
     * 查询成员信息
     */
    SocialMemberVo queryById(Long memberId);

    /**
     * 查询成员信息列表
     */
    TableDataInfo<SocialMemberVo> queryPageList(SocialMemberBo bo, PageQuery pageQuery);

    /**
     * 查询成员信息列表
     */
    List<SocialMemberVo> queryList(SocialMemberBo bo);

    /**
     * 新增成员信息
     */
    Boolean insertByBo(SocialMemberBo bo);

    /**
     * 修改成员信息
     */
    Boolean updateByBo(SocialMemberBo bo);

    /**
     * 校验并批量删除成员信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
