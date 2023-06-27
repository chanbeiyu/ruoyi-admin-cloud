package org.dromara.alkaid.service.impl;

import org.dromara.alkaid.domain.RobotPropmt;
import org.dromara.alkaid.domain.bo.RobotPropmtBo;
import org.dromara.alkaid.domain.vo.RobotPropmtVo;
import org.dromara.alkaid.mapper.RobotPropmtMapper;
import org.dromara.alkaid.service.IRobotPropmtService;
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
 * Ai PropmtService业务层处理
 *
 * @author beiyu
 */
@RequiredArgsConstructor
@Service
public class RobotPropmtServiceImpl implements IRobotPropmtService {

    private final RobotPropmtMapper baseMapper;

    /**
     * 查询Ai Propmt
     */
    @Override
    public RobotPropmtVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询Ai Propmt列表
     */
    @Override
    public TableDataInfo<RobotPropmtVo> queryPageList(RobotPropmtBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<RobotPropmt> lqw = buildQueryWrapper(bo);
        Page<RobotPropmtVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询Ai Propmt列表
     */
    @Override
    public List<RobotPropmtVo> queryList(RobotPropmtBo bo) {
        LambdaQueryWrapper<RobotPropmt> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<RobotPropmt> buildQueryWrapper(RobotPropmtBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<RobotPropmt> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getSubject()), RobotPropmt::getSubject, bo.getSubject());
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), RobotPropmt::getTitle, bo.getTitle());
        lqw.like(StringUtils.isNotBlank(bo.getContent()), RobotPropmt::getContent, bo.getContent());
        lqw.eq(bo.getPlatform() != null, RobotPropmt::getPlatform, bo.getPlatform());
        lqw.eq(bo.getSource() != null, RobotPropmt::getSource, bo.getSource());
        return lqw;
    }

    /**
     * 新增Ai Propmt
     */
    @Override
    public Boolean insertByBo(RobotPropmtBo bo) {
        RobotPropmt add = MapstructUtils.convert(bo, RobotPropmt.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改Ai Propmt
     */
    @Override
    public Boolean updateByBo(RobotPropmtBo bo) {
        RobotPropmt update = MapstructUtils.convert(bo, RobotPropmt.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(RobotPropmt entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除Ai Propmt
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
