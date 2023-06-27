package org.dromara.alkaid.service.impl;

import org.dromara.alkaid.domain.GameRank;
import org.dromara.alkaid.domain.bo.GameRankBo;
import org.dromara.alkaid.domain.vo.GameRankVo;
import org.dromara.alkaid.mapper.GameRankMapper;
import org.dromara.alkaid.service.IGameRankService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 游戏用户排行榜Service业务层处理
 *
 * @author beiyu
 */
@RequiredArgsConstructor
@Service
public class GameRankServiceImpl implements IGameRankService {

    private final GameRankMapper baseMapper;

    /**
     * 查询游戏用户排行榜
     */
    @Override
    public GameRankVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询游戏用户排行榜列表
     */
    @Override
    public TableDataInfo<GameRankVo> queryPageList(GameRankBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GameRank> lqw = buildQueryWrapper(bo);
        Page<GameRankVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询游戏用户排行榜列表
     */
    @Override
    public List<GameRankVo> queryList(GameRankBo bo) {
        LambdaQueryWrapper<GameRank> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<GameRank> buildQueryWrapper(GameRankBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<GameRank> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppCode()), GameRank::getAppCode, bo.getAppCode());
        lqw.eq(bo.getUserId() != null, GameRank::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getLeave()), GameRank::getLeave, bo.getLeave());
        lqw.eq(bo.getDate() != null, GameRank::getDate, bo.getDate());
        lqw.eq(bo.getScore() != null, GameRank::getScore, bo.getScore());
        lqw.eq(bo.getScoreType() != null, GameRank::getScoreType, bo.getScoreType());
        return lqw;
    }

    /**
     * 新增游戏用户排行榜
     */
    @Override
    public Boolean insertByBo(GameRankBo bo) {
        GameRank add = MapstructUtils.convert(bo, GameRank.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改游戏用户排行榜
     */
    @Override
    public Boolean updateByBo(GameRankBo bo) {
        GameRank update = MapstructUtils.convert(bo, GameRank.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GameRank entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除游戏用户排行榜
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
