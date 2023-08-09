package org.dromara.basal.platform.service.social;

import org.dromara.basal.platform.domain.social.vo.SocialTagVo;
import org.dromara.basal.platform.domain.social.bo.SocialTagBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 标签信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialTagService {

    /**
     * 查询标签信息
     */
    SocialTagVo queryById(Long tagId);

    /**
     * 查询标签信息列表
     */
    TableDataInfo<SocialTagVo> queryPageList(SocialTagBo bo, PageQuery pageQuery);

    /**
     * 查询标签信息列表
     */
    List<SocialTagVo> queryList(SocialTagBo bo);

    /**
     * 新增标签信息
     */
    Boolean insertByBo(SocialTagBo bo);

    /**
     * 修改标签信息
     */
    Boolean updateByBo(SocialTagBo bo);

    int updateStatus(Long tagId, String status);

    /**
     * 校验并批量删除标签信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
