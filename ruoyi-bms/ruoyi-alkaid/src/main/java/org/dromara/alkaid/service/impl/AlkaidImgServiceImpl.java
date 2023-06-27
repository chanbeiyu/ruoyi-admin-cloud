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
import org.dromara.alkaid.domain.bo.AlkaidImgBo;
import org.dromara.alkaid.domain.vo.AlkaidImgVo;
import org.dromara.alkaid.domain.AlkaidImg;
import org.dromara.alkaid.mapper.AlkaidImgMapper;
import org.dromara.alkaid.service.IAlkaidImgService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 图片信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidImgServiceImpl implements IAlkaidImgService {

    private final AlkaidImgMapper baseMapper;

    /**
     * 查询图片信息
     */
    @Override
    public AlkaidImgVo queryById(Long imgId){
        return baseMapper.selectVoById(imgId);
    }

    /**
     * 查询图片信息列表
     */
    @Override
    public TableDataInfo<AlkaidImgVo> queryPageList(AlkaidImgBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlkaidImg> lqw = buildQueryWrapper(bo);
        Page<AlkaidImgVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询图片信息列表
     */
    @Override
    public List<AlkaidImgVo> queryList(AlkaidImgBo bo) {
        LambdaQueryWrapper<AlkaidImg> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidImg> buildQueryWrapper(AlkaidImgBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidImg> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, AlkaidImg::getUserId, bo.getUserId());
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), AlkaidImg::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getTags()), AlkaidImg::getTags, bo.getTags());
        return lqw;
    }

    /**
     * 新增图片信息
     */
    @Override
    public Boolean insertByBo(AlkaidImgBo bo) {
        AlkaidImg add = MapstructUtils.convert(bo, AlkaidImg.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setImgId(add.getImgId());
        }
        return flag;
    }

    /**
     * 修改图片信息
     */
    @Override
    public Boolean updateByBo(AlkaidImgBo bo) {
        AlkaidImg update = MapstructUtils.convert(bo, AlkaidImg.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidImg entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除图片信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
