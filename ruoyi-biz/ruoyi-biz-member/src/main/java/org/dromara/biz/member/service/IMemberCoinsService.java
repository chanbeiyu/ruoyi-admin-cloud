package org.dromara.biz.member.service;

import org.dromara.biz.member.domain.MemberCoins;
import org.dromara.biz.member.domain.vo.MemberCoinsVo;
import org.dromara.biz.member.domain.bo.MemberCoinsBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 代币信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
public interface IMemberCoinsService {

    /**
     * 查询代币信息
     */
    MemberCoinsVo queryById(Long id);

    /**
     * 查询代币信息列表
     */
    TableDataInfo<MemberCoinsVo> queryPageList(MemberCoinsBo bo, PageQuery pageQuery);

    /**
     * 查询代币信息列表
     */
    List<MemberCoinsVo> queryList(MemberCoinsBo bo);

    /**
     * 新增代币信息
     */
    Boolean insertByBo(MemberCoinsBo bo);

    /**
     * 修改代币信息
     */
    Boolean updateByBo(MemberCoinsBo bo);

    int updateStatus(Long appId, Integer status);

    /**
     * 校验并批量删除代币信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
