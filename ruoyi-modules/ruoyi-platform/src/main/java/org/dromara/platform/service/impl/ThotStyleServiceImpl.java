package org.dromara.platform.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.AppVersion;
import org.dromara.platform.domain.SocialNoticeType;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.ThotStyleBo;
import org.dromara.platform.domain.vo.ThotStyleVo;
import org.dromara.platform.domain.ThotStyle;
import org.dromara.platform.mapper.ThotStyleMapper;
import org.dromara.platform.service.IThotStyleService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 样式信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class ThotStyleServiceImpl implements IThotStyleService {

    private final ThotStyleMapper baseMapper;

    /**
     * 查询样式信息
     */
    @Override
    public ThotStyleVo queryById(Long styleId){
        return baseMapper.selectVoById(styleId);
    }

    /**
     * 查询样式信息列表
     */
    @Override
    public TableDataInfo<ThotStyleVo> queryPageList(ThotStyleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ThotStyle> lqw = buildQueryWrapper(bo);
        Page<ThotStyleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询样式信息列表
     */
    @Override
    public List<ThotStyleVo> queryList(ThotStyleBo bo) {
        LambdaQueryWrapper<ThotStyle> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ThotStyle> buildQueryWrapper(ThotStyleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ThotStyle> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, ThotStyle::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), ThotStyle::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getStyleCode()), ThotStyle::getStyleCode, bo.getStyleCode());
        lqw.like(StringUtils.isNotBlank(bo.getStyleName()), ThotStyle::getStyleName, bo.getStyleName());
        lqw.eq(StringUtils.isNotBlank(bo.getStyleContent()), ThotStyle::getStyleContent, bo.getStyleContent());
        lqw.eq(StringUtils.isNotBlank(bo.getDescription()), ThotStyle::getDescription, bo.getDescription());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ThotStyle::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增样式信息
     */
    @Override
    public Boolean insertByBo(ThotStyleBo bo) {
        ThotStyle add = MapstructUtils.convert(bo, ThotStyle.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setStyleId(add.getStyleId());
        }
        return flag;
    }

    /**
     * 修改样式信息
     */
    @Override
    public Boolean updateByBo(ThotStyleBo bo) {
        ThotStyle update = MapstructUtils.convert(bo, ThotStyle.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 修改样式状态
     *
     * @param styleId 样式ID
     * @param status 样式状态
     * @return 结果
     */
    @Override
    public int updateStyleStatus(Long styleId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<ThotStyle>()
                .set(ThotStyle::getStatus, status)
                .eq(ThotStyle::getStyleId, styleId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ThotStyle entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除样式信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
