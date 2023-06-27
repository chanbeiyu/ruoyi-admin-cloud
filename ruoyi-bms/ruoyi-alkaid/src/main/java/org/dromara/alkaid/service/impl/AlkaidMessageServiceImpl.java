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
import org.dromara.alkaid.domain.bo.AlkaidMessageBo;
import org.dromara.alkaid.domain.vo.AlkaidMessageVo;
import org.dromara.alkaid.domain.AlkaidMessage;
import org.dromara.alkaid.mapper.AlkaidMessageMapper;
import org.dromara.alkaid.service.IAlkaidMessageService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 用户消息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidMessageServiceImpl implements IAlkaidMessageService {

    private final AlkaidMessageMapper baseMapper;

    /**
     * 查询用户消息
     */
    @Override
    public AlkaidMessageVo queryById(Long messageId){
        return baseMapper.selectVoById(messageId);
    }

    /**
     * 查询用户消息列表
     */
    @Override
    public TableDataInfo<AlkaidMessageVo> queryPageList(AlkaidMessageBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<AlkaidMessage> lqw = buildQueryWrapper(bo);
        Page<AlkaidMessageVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户消息列表
     */
    @Override
    public List<AlkaidMessageVo> queryList(AlkaidMessageBo bo) {
        LambdaQueryWrapper<AlkaidMessage> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidMessage> buildQueryWrapper(AlkaidMessageBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidMessage> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, AlkaidMessage::getUserId, bo.getUserId());
        lqw.eq(bo.getTriggerUserId() != null, AlkaidMessage::getTriggerUserId, bo.getTriggerUserId());
        lqw.eq(bo.getTriggerContentId() != null, AlkaidMessage::getTriggerContentId, bo.getTriggerContentId());
        lqw.eq(StringUtils.isNotBlank(bo.getMessageTitle()), AlkaidMessage::getMessageTitle, bo.getMessageTitle());
        lqw.eq(bo.getMessageType() != null, AlkaidMessage::getMessageType, bo.getMessageType());
        lqw.eq(bo.getStatus() != null, AlkaidMessage::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增用户消息
     */
    @Override
    public Boolean insertByBo(AlkaidMessageBo bo) {
        AlkaidMessage add = MapstructUtils.convert(bo, AlkaidMessage.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMessageId(add.getMessageId());
        }
        return flag;
    }

    /**
     * 修改用户消息
     */
    @Override
    public Boolean updateByBo(AlkaidMessageBo bo) {
        AlkaidMessage update = MapstructUtils.convert(bo, AlkaidMessage.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidMessage entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户消息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
