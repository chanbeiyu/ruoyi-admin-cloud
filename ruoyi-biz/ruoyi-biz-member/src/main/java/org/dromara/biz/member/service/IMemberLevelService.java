package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberLevel;
import org.dromara.biz.member.domain.vo.MemberLevelVo;
import org.dromara.biz.member.domain.bo.MemberLevelBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 会员级别信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberLevelService {

    /**
     * 查询会员级别信息
     */
    MemberLevelVo queryById(Long levelId);

    /**
     * 查询会员级别信息列表
     */
    TableDataInfo<MemberLevelVo> queryPageList(MemberLevelBo bo, PageQuery pageQuery);

    /**
     * 查询会员级别信息列表
     */
    List<MemberLevelVo> queryList(MemberLevelBo bo);

    /**
     * 新增会员级别信息
     */
    Boolean insertByBo(MemberLevelBo bo);

    /**
     * 修改会员级别信息
     */
    Boolean updateByBo(MemberLevelBo bo);

    int updateStatus(Long appId, String status);

    /**
     * 校验并批量删除会员级别信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
