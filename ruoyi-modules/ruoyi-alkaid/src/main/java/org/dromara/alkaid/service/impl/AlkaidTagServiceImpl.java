package org.dromara.alkaid.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.alkaid.domain.bo.AlkaidTagBo;
import org.dromara.alkaid.domain.vo.AlkaidTagVo;
import org.dromara.alkaid.domain.AlkaidTag;
import org.dromara.alkaid.mapper.AlkaidTagMapper;
import org.dromara.alkaid.service.IAlkaidTagService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 标签信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidTagServiceImpl implements IAlkaidTagService {

    private final AlkaidTagMapper baseMapper;

    /**
     * 查询标签信息
     */
    @Override
    public AlkaidTagVo queryById(Long tagId){
        return baseMapper.selectVoById(tagId);
    }

    /**
     * 查询标签信息列表
     */
    @Override
    public TableDataInfo<AlkaidTagVo> queryPageList(AlkaidTagBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlkaidTag> lqw = buildQueryWrapper(bo);
        Page<AlkaidTagVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询标签信息列表
     */
    @Override
    public List<AlkaidTagVo> queryList(AlkaidTagBo bo) {
        LambdaQueryWrapper<AlkaidTag> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidTag> buildQueryWrapper(AlkaidTagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidTag> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), AlkaidTag::getTagName, bo.getTagName());
        lqw.eq(StringUtils.isNotBlank(bo.getTagType()), AlkaidTag::getTagType, bo.getTagType());
        return lqw;
    }

    /**
     * 新增标签信息
     */
    @Override
    public Boolean insertByBo(AlkaidTagBo bo) {
        AlkaidTag add = MapstructUtils.convert(bo, AlkaidTag.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTagId(add.getTagId());
        }
        return flag;
    }

    /**
     * 修改标签信息
     */
    @Override
    public Boolean updateByBo(AlkaidTagBo bo) {
        AlkaidTag update = MapstructUtils.convert(bo, AlkaidTag.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidTag entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除标签信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
