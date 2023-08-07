package org.dromara.biz.member.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.biz.common.constant.RedisKey;
import org.dromara.biz.member.domain.MemberCoins;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.stereotype.Service;
import org.dromara.biz.member.domain.bo.MemberTypeBo;
import org.dromara.biz.member.domain.vo.MemberTypeVo;
import org.dromara.biz.member.domain.MemberType;
import org.dromara.biz.member.mapper.MemberTypeMapper;
import org.dromara.biz.member.service.IMemberTypeService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 会员类型信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
public class MemberTypeServiceImpl implements IMemberTypeService {

    private final MemberTypeMapper baseMapper;

    /**
     * 查询会员类型信息
     */
    @Override
    public MemberTypeVo queryById(Long typeId) {
        return baseMapper.selectVoById(typeId);
    }

    /**
     * 查询会员类型信息列表
     */
    @Override
    public TableDataInfo<MemberTypeVo> queryPageList(MemberTypeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MemberType> lqw = buildQueryWrapper(bo);
        Page<MemberTypeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询会员类型信息列表
     */
    @Override
    public List<MemberTypeVo> queryList(MemberTypeBo bo) {
        LambdaQueryWrapper<MemberType> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MemberType> buildQueryWrapper(MemberTypeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MemberType> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, MemberType::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), MemberType::getAppId, bo.getAppIds());
        lqw.eq(StringUtils.isNotBlank(bo.getTypeCode()), MemberType::getTypeCode, bo.getTypeCode());
        lqw.like(StringUtils.isNotBlank(bo.getTypeName()), MemberType::getTypeName, bo.getTypeName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MemberType::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增会员类型信息
     */
    @Override
    public Boolean insertByBo(MemberTypeBo bo) {
        MemberType add = MapstructUtils.convert(bo, MemberType.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTypeId(add.getTypeId());
            CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, bo.getTypeId(), bo.getTypeName());
            CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, bo.getTypeCode(), bo.getTypeName());
        }
        return flag;
    }

    /**
     * 修改会员类型信息
     */
    @Override
    public Boolean updateByBo(MemberTypeBo bo) {
        MemberType update = MapstructUtils.convert(bo, MemberType.class);
        validEntityBeforeSave(update);
        boolean bool = baseMapper.updateById(update) > 0;
        if (bool) {
            CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, bo.getTypeId(), bo.getTypeName());
            CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, bo.getTypeCode(), bo.getTypeName());
        }
        return bool;
    }

    /**
     * 修改状态
     *
     * @return 结果
     */
    @Override
    public int updateStatus(Long appId, String status) {
        return baseMapper.update(null,
            new LambdaUpdateWrapper<MemberType>()
                .set(MemberType::getStatus, status)
                .eq(MemberType::getTypeId, appId));
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MemberType entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除会员类型信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        List<MemberType> memberTypes = baseMapper.selectList();
        boolean bool = baseMapper.deleteBatchIds(ids) > 0;
        if (bool) {
            memberTypes.forEach(bo -> {
                CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, bo.getTypeId(), bo.getTypeName());
                CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, bo.getTypeCode(), bo.getTypeName());
            });
        }
        return bool;
    }
}
