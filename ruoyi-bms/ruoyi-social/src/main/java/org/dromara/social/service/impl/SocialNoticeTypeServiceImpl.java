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
import org.dromara.social.domain.bo.SocialNoticeTypeBo;
import org.dromara.social.domain.vo.SocialNoticeTypeVo;
import org.dromara.social.domain.SocialNoticeType;
import org.dromara.social.mapper.SocialNoticeTypeMapper;
import org.dromara.social.service.ISocialNoticeTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 信息通知类型Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialNoticeTypeServiceImpl implements ISocialNoticeTypeService {

    private final SocialNoticeTypeMapper baseMapper;

    /**
     * 查询信息通知类型
     */
    @Override
    public SocialNoticeTypeVo queryById(Long noticeTypeId){
        return baseMapper.selectVoById(noticeTypeId);
    }

    /**
     * 查询信息通知类型列表
     */
    @Override
    public TableDataInfo<SocialNoticeTypeVo> queryPageList(SocialNoticeTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SocialNoticeType> lqw = buildQueryWrapper(bo);
        Page<SocialNoticeTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询信息通知类型列表
     */
    @Override
    public List<SocialNoticeTypeVo> queryList(SocialNoticeTypeBo bo) {
        LambdaQueryWrapper<SocialNoticeType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialNoticeType> buildQueryWrapper(SocialNoticeTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialNoticeType> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), SocialNoticeType::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getNoticeTypeCode()), SocialNoticeType::getNoticeTypeCode, bo.getNoticeTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getNoticeTypeName()), SocialNoticeType::getNoticeTypeName, bo.getNoticeTypeName());
        return lqw;
    }

    /**
     * 新增信息通知类型
     */
    @Override
    public Boolean insertByBo(SocialNoticeTypeBo bo) {
        SocialNoticeType add = MapstructUtils.convert(bo, SocialNoticeType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setNoticeTypeId(add.getNoticeTypeId());
        }
        return flag;
    }

    /**
     * 修改信息通知类型
     */
    @Override
    public Boolean updateByBo(SocialNoticeTypeBo bo) {
        SocialNoticeType update = MapstructUtils.convert(bo, SocialNoticeType.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialNoticeType entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除信息通知类型
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
