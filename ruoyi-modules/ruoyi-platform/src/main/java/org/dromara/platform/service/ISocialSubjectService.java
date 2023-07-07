package org.dromara.platform.service;

import org.dromara.platform.domain.vo.SocialSubjectVo;
import org.dromara.platform.domain.bo.SocialSubjectBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 内容主题Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialSubjectService {

    /**
     * 查询内容主题
     */
    SocialSubjectVo queryById(Long subjectId);

    /**
     * 查询内容主题列表
     */
    TableDataInfo<SocialSubjectVo> queryPageList(SocialSubjectBo bo, PageQuery pageQuery);

    /**
     * 查询内容主题列表
     */
    List<SocialSubjectVo> queryList(SocialSubjectBo bo);

    /**
     * 新增内容主题
     */
    Boolean insertByBo(SocialSubjectBo bo);

    /**
     * 修改内容主题
     */
    Boolean updateByBo(SocialSubjectBo bo);

    int updateStatus(Long subjectId, String status);

    /**
     * 校验并批量删除内容主题信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
