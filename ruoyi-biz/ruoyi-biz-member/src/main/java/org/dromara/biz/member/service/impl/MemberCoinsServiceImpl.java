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
import org.dromara.biz.member.domain.bo.MemberCoinsBo;
import org.dromara.biz.member.domain.vo.MemberCoinsVo;
import org.dromara.biz.member.domain.MemberCoins;
import org.dromara.biz.member.mapper.MemberCoinsMapper;
import org.dromara.biz.member.service.IMemberCoinsService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 代币信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberCoinsServiceImpl implements IMemberCoinsService {

    private final MemberCoinsMapper baseMapper;

    /**
     * 查询代币信息
     */
    @Override
    public MemberCoinsVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询代币信息列表
     */
    @Override
    public TableDataInfo<MemberCoinsVo> queryPageList(MemberCoinsBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberCoins> lqw = buildQueryWrapper(bo);
        Page<MemberCoinsVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询代币信息列表
     */
    @Override
    public List<MemberCoinsVo> queryList(MemberCoinsBo bo) {
        LambdaQueryWrapper<MemberCoins> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberCoins> buildQueryWrapper(MemberCoinsBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberCoins> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberCoins::getAppId, bo.getAppId());
        lqw.eq(bo.getMemberId() != null, MemberCoins::getMemberId, bo.getMemberId());
        lqw.eq(bo.getCoinsType() != null, MemberCoins::getCoinsType, bo.getCoinsType());
        lqw.eq(bo.getLastCoins() != null, MemberCoins::getLastCoins, bo.getLastCoins());
        lqw.between(StringUtils.isAllNotEmpty(params.get("beginExpiredDate"), params.get("endExpiredDate")),
            MemberCoins::getExpiredDate ,params.get("beginExpiredDate"), params.get("endExpiredDate"));
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MemberCoins::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增代币信息
     */
    @Override
    public Boolean insertByBo(MemberCoinsBo bo) {
        MemberCoins add = MapstructUtils.convert(bo, MemberCoins.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改代币信息
     */
    @Override
    public Boolean updateByBo(MemberCoinsBo bo) {
        MemberCoins update = MapstructUtils.convert(bo, MemberCoins.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberCoins entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除代币信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
