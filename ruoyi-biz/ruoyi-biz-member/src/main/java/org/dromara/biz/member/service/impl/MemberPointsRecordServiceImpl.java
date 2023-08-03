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
import org.dromara.biz.member.domain.bo.MemberPointsRecordBo;
import org.dromara.biz.member.domain.vo.MemberPointsRecordVo;
import org.dromara.biz.member.domain.MemberPointsRecord;
import org.dromara.biz.member.mapper.MemberPointsRecordMapper;
import org.dromara.biz.member.service.IMemberPointsRecordService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员积分记录Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberPointsRecordServiceImpl implements IMemberPointsRecordService {

    private final MemberPointsRecordMapper baseMapper;

    /**
     * 查询会员积分记录
     */
    @Override
    public MemberPointsRecordVo queryById(Long recordId){
        return baseMapper.selectVoById(recordId);
    }

    /**
     * 查询会员积分记录列表
     */
    @Override
    public TableDataInfo<MemberPointsRecordVo> queryPageList(MemberPointsRecordBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberPointsRecord> lqw = buildQueryWrapper(bo);
        Page<MemberPointsRecordVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员积分记录列表
     */
    @Override
    public List<MemberPointsRecordVo> queryList(MemberPointsRecordBo bo) {
        LambdaQueryWrapper<MemberPointsRecord> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberPointsRecord> buildQueryWrapper(MemberPointsRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberPointsRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberPointsRecord::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, MemberPointsRecord::getMemberId, bo.getMemberId());
        lqw.eq(bo.getMemberTypeId() != null, MemberPointsRecord::getMemberTypeId, bo.getMemberTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getActionCode()), MemberPointsRecord::getActionCode, bo.getActionCode());
        lqw.eq(bo.getStatus() != null, MemberPointsRecord::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增会员积分记录
     */
    @Override
    public Boolean insertByBo(MemberPointsRecordBo bo) {
        MemberPointsRecord add = MapstructUtils.convert(bo, MemberPointsRecord.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRecordId(add.getRecordId());
        }
        return flag;
    }

    /**
     * 修改会员积分记录
     */
    @Override
    public Boolean updateByBo(MemberPointsRecordBo bo) {
        MemberPointsRecord update = MapstructUtils.convert(bo, MemberPointsRecord.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberPointsRecord entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员积分记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
