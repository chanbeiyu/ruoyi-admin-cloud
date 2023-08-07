package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberType;
import org.dromara.biz.member.domain.vo.MemberTypeVo;
import org.dromara.biz.member.domain.bo.MemberTypeBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员类型信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberTypeService {

    /**
     * 查询会员类型信息
     */
    MemberTypeVo queryById(Long typeId);

    /**
     * 查询会员类型信息列表
     */
    TableDataInfo<MemberTypeVo> queryPageList(MemberTypeBo bo, PageQuery pageQuery);

    /**
     * 查询会员类型信息列表
     */
    List<MemberTypeVo> queryList(MemberTypeBo bo);

    /**
     * 新增会员类型信息
     */
    Boolean insertByBo(MemberTypeBo bo);

    /**
     * 修改会员类型信息
     */
    Boolean updateByBo(MemberTypeBo bo);

    int updateStatus(Long appId, String status);

    /**
     * 校验并批量删除会员类型信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
