package org.dromara.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.system.domain.SysSocial;
import org.dromara.system.domain.bo.SysSocialBo;
import org.dromara.system.domain.vo.SysSocialVo;
import org.dromara.system.mapper.SysSocialMapper;
import org.dromara.system.service.ISysSocialService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 社会化关系Service业务层处理
 *
 * @author thiszhc
 * @date 2023-06-12
 */
@RequiredArgsConstructor
@Service
public class SysSocialServiceImpl implements ISysSocialService {

    private final SysSocialMapper baseMapper;


    /**
     * 查询社会化关系
     */
    @Override
    public SysSocialVo queryById(String id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 授权列表
     */
    @Override
    public List<SysSocialVo> queryList() {
        return baseMapper.selectVoList();
    }

    @Override
    public List<SysSocialVo> queryListByUserId(Long userId) {
        return baseMapper.selectVoList(new LambdaQueryWrapper<SysSocial>().eq(SysSocial::getUserId, userId));
    }


    /**
     * 新增社会化关系
     */
    @Override
    public Boolean insertByBo(SysSocialBo bo) {

        SysSocial sysSocial = baseMapper.selectOne(new LambdaQueryWrapper<SysSocial>().eq(SysSocial::getAuthId, bo.getAuthId()));
        boolean flag;
        if (sysSocial != null) {
            BeanUtil.copyProperties(sysSocial, bo, CopyOptions.create().ignoreNullValue());
            flag = baseMapper.updateById(sysSocial) > 0;
        } else {
            SysSocial add = MapstructUtils.convert(bo, SysSocial.class);
            validEntityBeforeSave(add);
            add.setCreateBy(0L);
            add.setUpdateBy(0L);
            add.setCreateDept(0L);
            flag = baseMapper.insert(add) > 0;
            if (flag) {
                bo.setId(add.getId());
            }
        }
        return flag;
    }


    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SysSocial entity) {
        //TODO 做一些数据校验,如唯一约束
    }


    /**
     * 删除社会化关系
     */
    @Override
    public Boolean deleteWithValidById(Long id) {
        return baseMapper.deleteById(id) > 0;
    }


    /**
     * 根据 authId 查询用户信息
     *
     * @param authId 认证id
     * @return 授权信息
     */
    @Override
    public SysSocialVo selectByAuthId(String authId) {
        return baseMapper.selectVoOne(new LambdaQueryWrapper<SysSocial>().eq(SysSocial::getAuthId, authId));
    }

}
