package org.dromara.alkaid.service.impl;

import org.dromara.alkaid.domain.SpeechRecord;
import org.dromara.alkaid.domain.bo.SpeechRecordBo;
import org.dromara.alkaid.domain.vo.SpeechRecordVo;
import org.dromara.alkaid.mapper.SpeechRecordMapper;
import org.dromara.alkaid.service.ISpeechRecordService;
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
 * 语音记录管理Service业务层处理
 *
 * @author beiyu
 */
@RequiredArgsConstructor
@Service
public class SpeechRecordServiceImpl implements ISpeechRecordService {

    private final SpeechRecordMapper baseMapper;

    /**
     * 查询语音记录管理
     */
    @Override
    public SpeechRecordVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询语音记录管理列表
     */
    @Override
    public TableDataInfo<SpeechRecordVo> queryPageList(SpeechRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SpeechRecord> lqw = buildQueryWrapper(bo);
        Page<SpeechRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询语音记录管理列表
     */
    @Override
    public List<SpeechRecordVo> queryList(SpeechRecordBo bo) {
        LambdaQueryWrapper<SpeechRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SpeechRecord> buildQueryWrapper(SpeechRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SpeechRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getId() != null, SpeechRecord::getId, bo.getId());
        lqw.eq(bo.getUserId() != null, SpeechRecord::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getAppCode()), SpeechRecord::getAppCode, bo.getAppCode());
        lqw.eq(bo.getClient() != null, SpeechRecord::getClient, bo.getClient());
        lqw.eq(bo.getCostChar() != null, SpeechRecord::getCostChar, bo.getCostChar());
        lqw.eq(bo.getCostTime() != null, SpeechRecord::getCostTime, bo.getCostTime());
        lqw.eq(bo.getCostTimes() != null, SpeechRecord::getCostTimes, bo.getCostTimes());
        lqw.eq(bo.getType() != null, SpeechRecord::getType, bo.getType());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            SpeechRecord::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增语音记录管理
     */
    @Override
    public Boolean insertByBo(SpeechRecordBo bo) {
        SpeechRecord add = MapstructUtils.convert(bo, SpeechRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改语音记录管理
     */
    @Override
    public Boolean updateByBo(SpeechRecordBo bo) {
        SpeechRecord update = MapstructUtils.convert(bo, SpeechRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SpeechRecord entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除语音记录管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}
