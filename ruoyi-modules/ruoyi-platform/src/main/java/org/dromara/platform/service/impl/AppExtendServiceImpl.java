package org.dromara.platform.service.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.platform.domain.AppAdvice;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.AppExtendBo;
import org.dromara.platform.domain.vo.AppExtendVo;
import org.dromara.platform.domain.AppExtend;
import org.dromara.platform.mapper.AppExtendMapper;
import org.dromara.platform.service.IAppExtendService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 应用扩展信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppExtendServiceImpl implements IAppExtendService {

    private final AppExtendMapper baseMapper;

    /**
     * 查询应用扩展信息
     */
    @Override
    public AppExtendVo queryById(Long appId){
        return baseMapper.selectVoById(appId);
    }

    /**
     * 查询应用扩展信息列表
     */
    @Override
    public TableDataInfo<AppExtendVo> queryPageList(AppExtendBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppExtend> lqw = buildQueryWrapper(bo);
        Page<AppExtendVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用扩展信息列表
     */
    @Override
    public List<AppExtendVo> queryList(AppExtendBo bo) {
        LambdaQueryWrapper<AppExtend> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AppExtend> buildQueryWrapper(AppExtendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppExtend> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getExtendId() != null, AppExtend::getExtendId, bo.getExtendId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), AppExtend::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getContactWechat()), AppExtend::getContactWechat, bo.getContactWechat());
        lqw.eq(StringUtils.isNotBlank(bo.getContactPhone()), AppExtend::getContactPhone, bo.getContactPhone());
        lqw.eq(StringUtils.isNotBlank(bo.getContactEmail()), AppExtend::getContactEmail, bo.getContactEmail());
        lqw.eq(StringUtils.isNotBlank(bo.getServiceAgreement()), AppExtend::getServiceAgreement, bo.getServiceAgreement());
        lqw.eq(StringUtils.isNotBlank(bo.getPrivacyPolicy()), AppExtend::getPrivacyPolicy, bo.getPrivacyPolicy());
        lqw.eq(StringUtils.isNotBlank(bo.getBehaviourNorm()), AppExtend::getBehaviourNorm, bo.getBehaviourNorm());
        lqw.eq(StringUtils.isNotBlank(bo.getPersonalInfoChecklist()), AppExtend::getPersonalInfoChecklist, bo.getPersonalInfoChecklist());
        return lqw;
    }

    /**
     * 新增应用扩展信息
     */
    @Override
    public Boolean insertByBo(AppExtendBo bo) {
        AppExtend add = MapstructUtils.convert(bo, AppExtend.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
        }
        return flag;
    }

    /**
     * 修改应用扩展信息
     */
    @Override
    public Boolean updateByBo(AppExtendBo bo) {
        AppExtend update = MapstructUtils.convert(bo, AppExtend.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppExtend entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用扩展信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
