package org.dromara.biz.member.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.biz.member.domain.bo.MemberCoinsRecordBo;
import org.dromara.biz.member.domain.vo.MemberCoinsRecordVo;
import org.dromara.biz.member.domain.MemberCoinsRecord;
import org.dromara.biz.member.mapper.MemberCoinsRecordMapper;
import org.dromara.biz.member.service.IMemberCoinsRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 代币记录信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberCoinsRecordServiceImpl implements IMemberCoinsRecordService {

    private final MemberCoinsRecordMapper baseMapper;

    /**
     * 查询代币记录信息
     */
    @Override
    public MemberCoinsRecordVo queryById(Long recordId){
        return baseMapper.selectVoById(recordId);
    }

    /**
     * 查询代币记录信息列表
     */
    @Override
    public TableDataInfo<MemberCoinsRecordVo> queryPageList(MemberCoinsRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberCoinsRecord> lqw = buildQueryWrapper(bo);
        Page<MemberCoinsRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询代币记录信息列表
     */
    @Override
    public List<MemberCoinsRecordVo> queryList(MemberCoinsRecordBo bo) {
        LambdaQueryWrapper<MemberCoinsRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberCoinsRecord> buildQueryWrapper(MemberCoinsRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberCoinsRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberCoinsRecord::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, MemberCoinsRecord::getMemberId, bo.getMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getActionCode()), MemberCoinsRecord::getActionCode, bo.getActionCode());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), MemberCoinsRecord::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getStatus() != null, MemberCoinsRecord::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginCreateTime"), params.get("endCreateTime")),
            MemberCoinsRecord::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增代币记录信息
     */
    @Override
    public Boolean insertByBo(MemberCoinsRecordBo bo) {
        MemberCoinsRecord add = MapstructUtils.convert(bo, MemberCoinsRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRecordId(add.getRecordId());
        }
        return flag;
    }

    /**
     * 修改代币记录信息
     */
    @Override
    public Boolean updateByBo(MemberCoinsRecordBo bo) {
        MemberCoinsRecord update = MapstructUtils.convert(bo, MemberCoinsRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberCoinsRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除代币记录信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
