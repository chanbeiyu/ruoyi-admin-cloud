package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.bo.GameRankBo;
import org.dromara.alkaid.domain.vo.GameRankVo;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;

import java.util.Collection;
import java.util.List;

/**
 * 游戏用户排行榜Service接口
 *
 * @author beiyu
 */
public interface IGameRankService {

    /**
     * 查询游戏用户排行榜
     */
    GameRankVo queryById(Long id);

    /**
     * 查询游戏用户排行榜列表
     */
    TableDataInfo<GameRankVo> queryPageList(GameRankBo bo, PageQuery pageQuery);

    /**
     * 查询游戏用户排行榜列表
     */
    List<GameRankVo> queryList(GameRankBo bo);

    /**
     * 新增游戏用户排行榜
     */
    Boolean insertByBo(GameRankBo bo);

    /**
     * 修改游戏用户排行榜
     */
    Boolean updateByBo(GameRankBo bo);

    /**
     * 校验并批量删除游戏用户排行榜信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
