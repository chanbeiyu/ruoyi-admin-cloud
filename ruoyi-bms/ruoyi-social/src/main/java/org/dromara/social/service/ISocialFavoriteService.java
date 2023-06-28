package org.dromara.social.service;

import org.dromara.social.domain.SocialFavorite;
import org.dromara.social.domain.vo.SocialFavoriteVo;
import org.dromara.social.domain.bo.SocialFavoriteBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 收藏信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
public interface ISocialFavoriteService {

    /**
     * 查询收藏信息
     */
    SocialFavoriteVo queryById(Long favoriteId);

    /**
     * 查询收藏信息列表
     */
    TableDataInfo<SocialFavoriteVo> queryPageList(SocialFavoriteBo bo, PageQuery pageQuery);

    /**
     * 查询收藏信息列表
     */
    List<SocialFavoriteVo> queryList(SocialFavoriteBo bo);

    /**
     * 新增收藏信息
     */
    Boolean insertByBo(SocialFavoriteBo bo);

    /**
     * 修改收藏信息
     */
    Boolean updateByBo(SocialFavoriteBo bo);

    /**
     * 校验并批量删除收藏信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
