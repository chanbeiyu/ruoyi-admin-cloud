package org.dromara.basal.platform.runner;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.basal.platform.constant.RedisKey;
import org.dromara.basal.platform.domain.member.MemberInfo;
import org.dromara.basal.platform.domain.member.MemberType;
import org.dromara.basal.platform.mapper.member.MemberInfoMapper;
import org.dromara.basal.platform.mapper.member.MemberTypeMapper;
import org.dromara.common.redis.utils.CacheUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 初始化 system 模块对应业务数据
 *
 * @author chanbeiyu
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class MemberApplicationRunner implements ApplicationRunner, DisposableBean {

    private final MemberTypeMapper memberTypeMapper;
    private final MemberInfoMapper memberInfoMapper;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        initMemberMapping();
        log.info("初始化 initAppMaping 配置成功");
    }

    private void initMemberMapping() {
        List<MemberInfo> memberInfos = memberInfoMapper.selectList();
        List<MemberType> memberTypes = memberTypeMapper.selectList();
        memberInfos.forEach(o -> {
            CacheUtils.put(RedisKey.MEMBER_INFO_ID_NAME, o.getMemberId(), o.getNickName());
            CacheUtils.put(RedisKey.MEMBER_INFO_UNIONID_NAME, o.getUnionId(), o.getNickName());
        });
        memberTypes.forEach(o -> {
            CacheUtils.put(RedisKey.MEMBER_TYPE_ID_NAME, o.getTypeId(), o.getTypeName());
            CacheUtils.put(RedisKey.MEMBER_TYPE_CODE_NAME, o.getTypeCode(), o.getTypeName());
        });
    }

    @Override
    public void destroy() throws Exception {
        CacheUtils.evict(RedisKey.MEMBER_INFO_ID_NAME);
        CacheUtils.evict(RedisKey.MEMBER_INFO_UNIONID_NAME);
        CacheUtils.evict(RedisKey.MEMBER_TYPE_ID_NAME);
        CacheUtils.evict(RedisKey.MEMBER_TYPE_CODE_NAME);
    }
}
