package org.dromara.alkaid.service.impl;

import org.dromara.alkaid.domain.AppInfo;
import org.dromara.alkaid.domain.bo.AppInfoBo;
import org.dromara.alkaid.domain.vo.AppInfoVo;
import org.dromara.alkaid.mapper.AppInfoMapper;
import org.dromara.alkaid.service.IAppInfoService;
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
 * 应用信息Service业务层处理
 *
 * @author beiyu
 */
@RequiredArgsConstructor
@Service
public class AppInfoServiceImpl implements IAppInfoService {

    private final AppInfoMapper baseMapper;

    /**
     * 查询应用信息
     */
    @Override
    public AppInfoVo queryById(String code) {
        return baseMapper.selectVoById(code);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public TableDataInfo<AppInfoVo> queryPageList(AppInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppInfo> lqw = buildQueryWrapper(bo);
        Page<AppInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<AppInfoVo> queryList(AppInfoBo bo) {
        LambdaQueryWrapper<AppInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AppInfo> buildQueryWrapper(AppInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppInfo> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCode()), AppInfo::getCode, bo.getCode());
        lqw.like(StringUtils.isNotBlank(bo.getName()), AppInfo::getName, bo.getName());
        lqw.eq(bo.getType() != null, AppInfo::getType, bo.getType());
        lqw.eq(bo.getTenantId() != null, AppInfo::getTenantId, bo.getTenantId());
        lqw.eq(bo.getCreateDept() != null, AppInfo::getCreateDept, bo.getCreateDept());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByBo(AppInfoBo bo) {
        AppInfo add = MapstructUtils.convert(bo, AppInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCode(add.getCode());
        }
        return flag;
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean updateByBo(AppInfoBo bo) {
        AppInfo update = MapstructUtils.convert(bo, AppInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.insertOrUpdate(update);
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean insertOrUpdate(AppInfoBo bo) {
        AppInfo update = MapstructUtils.convert(bo, AppInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.insertOrUpdate(update);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppInfo entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
