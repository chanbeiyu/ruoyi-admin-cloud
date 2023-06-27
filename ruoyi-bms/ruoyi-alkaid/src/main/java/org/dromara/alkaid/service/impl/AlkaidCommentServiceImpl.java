package org.dromara.alkaid.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.alkaid.domain.bo.AlkaidCommentBo;
import org.dromara.alkaid.domain.vo.AlkaidCommentVo;
import org.dromara.alkaid.domain.AlkaidComment;
import org.dromara.alkaid.mapper.AlkaidCommentMapper;
import org.dromara.alkaid.service.IAlkaidCommentService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 图集评论信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@RequiredArgsConstructor
@Service
public class AlkaidCommentServiceImpl implements IAlkaidCommentService {

    private final AlkaidCommentMapper baseMapper;

    /**
     * 查询图集评论信息
     */
    @Override
    public AlkaidCommentVo queryById(Long commentId){
        return baseMapper.selectVoById(commentId);
    }


    /**
     * 查询图集评论信息列表
     */
    @Override
    public List<AlkaidCommentVo> queryList(AlkaidCommentBo bo) {
        LambdaQueryWrapper<AlkaidComment> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<AlkaidComment> buildQueryWrapper(AlkaidCommentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AlkaidComment> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommentPid() != null, AlkaidComment::getCommentPid, bo.getCommentPid());
        lqw.eq(bo.getCommentUserId() != null, AlkaidComment::getCommentUserId, bo.getCommentUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getCommentContent()), AlkaidComment::getCommentContent, bo.getCommentContent());
        lqw.eq(bo.getLikeNum() != null, AlkaidComment::getLikeNum, bo.getLikeNum());
        lqw.eq(bo.getStatus() != null, AlkaidComment::getStatus, bo.getStatus());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            AlkaidComment::getCreateTime ,params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增图集评论信息
     */
    @Override
    public Boolean insertByBo(AlkaidCommentBo bo) {
        AlkaidComment add = MapstructUtils.convert(bo, AlkaidComment.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCommentId(add.getCommentId());
        }
        return flag;
    }

    /**
     * 修改图集评论信息
     */
    @Override
    public Boolean updateByBo(AlkaidCommentBo bo) {
        AlkaidComment update = MapstructUtils.convert(bo, AlkaidComment.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(AlkaidComment entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除图集评论信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
