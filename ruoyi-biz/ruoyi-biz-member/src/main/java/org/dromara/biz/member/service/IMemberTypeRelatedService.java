package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberTypeRelated;
import org.dromara.biz.member.domain.vo.MemberTypeRelatedVo;
import org.dromara.biz.member.domain.bo.MemberTypeRelatedBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员类型关联信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberTypeRelatedService {

    /**
     * 查询会员类型关联信息
     */
    MemberTypeRelatedVo queryById(Long id);

    /**
     * 查询会员类型关联信息列表
     */
    TableDataInfo<MemberTypeRelatedVo> queryPageList(MemberTypeRelatedBo bo, PageQuery pageQuery);

    /**
     * 查询会员类型关联信息列表
     */
    List<MemberTypeRelatedVo> queryList(MemberTypeRelatedBo bo);

    /**
     * 新增会员类型关联信息
     */
    Boolean insertByBo(MemberTypeRelatedBo bo);

    /**
     * 修改会员类型关联信息
     */
    Boolean updateByBo(MemberTypeRelatedBo bo);

    /**
     * 校验并批量删除会员类型关联信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
