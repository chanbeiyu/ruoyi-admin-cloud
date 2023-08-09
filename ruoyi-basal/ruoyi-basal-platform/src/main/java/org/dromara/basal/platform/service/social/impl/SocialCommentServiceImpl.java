package org.dromara.basal.platform.service.social.impl;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.basal.platform.domain.social.bo.SocialCommentBo;
import org.dromara.basal.platform.domain.social.vo.SocialCommentVo;
import org.dromara.basal.platform.domain.social.SocialComment;
import org.dromara.basal.platform.mapper.social.SocialCommentMapper;
import org.dromara.basal.platform.service.social.ISocialCommentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.Objects;

/**
 * 评论信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-28
 */
@RequiredArgsConstructor
@Service
public class SocialCommentServiceImpl implements ISocialCommentService {

    private final SocialCommentMapper baseMapper;

    /**
     * 查询评论信息
     */
    @Override
    public SocialCommentVo queryById(Long commentId){
        return baseMapper.selectVoById(commentId);
    }


    /**
     * 查询评论信息列表
     */
    @Override
    public List<SocialCommentVo> queryList(SocialCommentBo bo) {
        LambdaQueryWrapper<SocialComment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SocialComment> buildQueryWrapper(SocialCommentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SocialComment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommentPid() != null, SocialComment::getCommentPid, bo.getCommentPid());
        lqw.eq(Objects.nonNull(bo.getAppId()), SocialComment::getAppId, bo.getAppId());
        lqw.in(CollectionUtils.isNotEmpty(bo.getAppIds()), SocialComment::getAppId, bo.getAppIds());
        lqw.eq(bo.getMemberId() != null, SocialComment::getMemberId, bo.getMemberId());
        lqw.eq(bo.getToMemberId() != null, SocialComment::getToMemberId, bo.getToMemberId());
        lqw.eq(bo.getSubjectId() != null, SocialComment::getSubjectId, bo.getSubjectId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetId()), SocialComment::getTargetId, bo.getTargetId());
        lqw.eq(StringUtils.isNotBlank(bo.getTargetContent()), SocialComment::getTargetContent, bo.getTargetContent());
        lqw.eq(StringUtils.isNotBlank(bo.getCommentTitle()), SocialComment::getCommentTitle, bo.getCommentTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getCommentContnet()), SocialComment::getCommentContnet, bo.getCommentContnet());
        lqw.eq(bo.getLikeNum() != null, SocialComment::getLikeNum, bo.getLikeNum());
        lqw.eq(bo.getAllowComment() != null, SocialComment::getAllowComment, bo.getAllowComment());
        lqw.eq(bo.getStatus() != null, SocialComment::getStatus, bo.getStatus());
        lqw.eq(bo.getDeleteTime() != null, SocialComment::getDeleteTime, bo.getDeleteTime());
        return lqw;
    }

    /**
     * 新增评论信息
     */
    @Override
    public Boolean insertByBo(SocialCommentBo bo) {
        SocialComment add = MapstructUtils.convert(bo, SocialComment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCommentId(add.getCommentId());
        }
        return flag;
    }

    /**
     * 修改评论信息
     */
    @Override
    public Boolean updateByBo(SocialCommentBo bo) {
        SocialComment update = MapstructUtils.convert(bo, SocialComment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SocialComment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除评论信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
