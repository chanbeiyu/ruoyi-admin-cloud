package org.dromara.social.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.social.domain.bo.SocialAppBo;
import org.dromara.social.domain.vo.SocialAppVo;
import org.dromara.social.domain.SocialApp;
import org.dromara.social.mapper.SocialAppMapper;
import org.dromara.social.service.ISocialAppService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 应用信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialAppServiceImpl implements ISocialAppService {

    private final SocialAppMapper baseMapper;

    /**
     * 查询应用信息
     */
    @Override
    public SocialAppVo queryById(Long appId){
        return baseMapper.selectVoById(appId);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public TableDataInfo<SocialAppVo> queryPageList(SocialAppBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialApp> lqw = buildQueryWrapper(bo);
        Page<SocialAppVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询应用信息列表
     */
    @Override
    public List<SocialAppVo> queryList(SocialAppBo bo) {
        LambdaQueryWrapper<SocialApp> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialApp> buildQueryWrapper(SocialAppBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialApp> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getAppCode()), SocialApp::getAppCode, bo.getAppCode());
        lqw.like(StringUtils.isNotBlank(bo.getAppName()), SocialApp::getAppName, bo.getAppName());
        lqw.eq(StringUtils.isNotBlank(bo.getAccessKeyId()), SocialApp::getAccessKeyId, bo.getAccessKeyId());
        lqw.eq(StringUtils.isNotBlank(bo.getSecretAccessKey()), SocialApp::getSecretAccessKey, bo.getSecretAccessKey());
        lqw.eq(StringUtils.isNotBlank(bo.getAlgorithm()), SocialApp::getAlgorithm, bo.getAlgorithm());
        lqw.eq(StringUtils.isNotBlank(bo.getSalt()), SocialApp::getSalt, bo.getSalt());
        lqw.eq(StringUtils.isNotBlank(bo.getDomains()), SocialApp::getDomains, bo.getDomains());
        lqw.eq(StringUtils.isNotBlank(bo.getDescribe()), SocialApp::getDescribe, bo.getDescribe());
        return lqw;
    }

    /**
     * 新增应用信息
     */
    @Override
    public Boolean insertByBo(SocialAppBo bo) {
        SocialApp add = MapstructUtils.convert(bo, SocialApp.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAppId(add.getAppId());
        }
        return flag;
    }

    /**
     * 修改应用信息
     */
    @Override
    public Boolean updateByBo(SocialAppBo bo) {
        SocialApp update = MapstructUtils.convert(bo, SocialApp.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialApp entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除应用信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
