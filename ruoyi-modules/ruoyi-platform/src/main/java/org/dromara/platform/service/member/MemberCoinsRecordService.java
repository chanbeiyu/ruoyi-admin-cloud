package org.dromara.platform.service.member;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.basal.member.domain.bo.MemberCoinsRecordBo;
import org.dromara.basal.member.domain.member.MemberCoinsRecord;
import org.dromara.basal.member.mapper.MemberCoinsRecordMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.member.MemberCoinsRecordVo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * 代币记录信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberCoinsRecordService
    implements IBaseService<MemberCoinsRecord, MemberCoinsRecordVo, MemberCoinsRecordBo> {

    private final MemberCoinsRecordMapper memberCoinsRecordMapper;

    @Override
    public IBaseMapper<MemberCoinsRecord> mapper() {
        return memberCoinsRecordMapper;
    }

    @Override
    public LambdaQueryWrapper<MemberCoinsRecord> buildQueryWrapper(MemberCoinsRecordBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberCoinsRecord> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberCoinsRecord::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, MemberCoinsRecord::getMemberId, bo.getMemberId());
        lqw.eq(StringUtils.isNotBlank(bo.getActionCode()), MemberCoinsRecord::getActionCode, bo.getActionCode());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderNo()), MemberCoinsRecord::getOrderNo, bo.getOrderNo());
        lqw.eq(bo.getStatus() != null, MemberCoinsRecord::getStatus, bo.getStatus());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginCreateTime"), params.get("endCreateTime")),
            MemberCoinsRecord::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }


}
