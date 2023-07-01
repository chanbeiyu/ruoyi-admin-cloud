package org.dromara.platform.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.platform.domain.bo.AppAdviceBo;
import org.dromara.platform.domain.vo.AppAdviceVo;
import org.dromara.platform.domain.AppAdvice;
import org.dromara.platform.mapper.AppAdviceMapper;
import org.dromara.platform.service.IAppAdviceService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 意见反馈信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppAdviceServiceImpl implements IAppAdviceService {

    private final AppAdviceMapper baseMapper;

    /**
     * 查询意见反馈信息
     */
    @Override
    public AppAdviceVo queryById(Long adviceId){
        return baseMapper.selectVoById(adviceId);
    }

    /**
     * 查询意见反馈信息列表
     */
    @Override
    public TableDataInfo<AppAdviceVo> queryPageList(AppAdviceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AppAdvice> lqw = buildQueryWrapper(bo);
        Page<AppAdviceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询意见反馈信息列表
     */
    @Override
    public List<AppAdviceVo> queryList(AppAdviceBo bo) {
        LambdaQueryWrapper<AppAdvice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AppAdvice> buildQueryWrapper(AppAdviceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppAdvice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppAdvice::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, AppAdvice::getMemberId, bo.getMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getContact()), AppAdvice::getContact, bo.getContact());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), AppAdvice::getContent, bo.getContent());
        lqw.eq(bo.getCreateTime() != null, AppAdvice::getCreateTime, bo.getCreateTime());
        return lqw;
    }

    /**
     * 新增意见反馈信息
     */
    @Override
    public Boolean insertByBo(AppAdviceBo bo) {
        AppAdvice add = MapstructUtils.convert(bo, AppAdvice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAdviceId(add.getAdviceId());
        }
        return flag;
    }

    /**
     * 修改意见反馈信息
     */
    @Override
    public Boolean updateByBo(AppAdviceBo bo) {
        AppAdvice update = MapstructUtils.convert(bo, AppAdvice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AppAdvice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除意见反馈信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
