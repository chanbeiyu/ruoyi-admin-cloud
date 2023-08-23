package org.dromara.platform.service.app;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.basal.app.domain.AppExtend;
import org.dromara.basal.app.domain.bo.AppExtendBo;
import org.dromara.basal.app.mapper.AppExtendMapper;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.service.IBaseService;
import org.dromara.platform.domain.vo.app.AppExtendVo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 应用扩展信息Service业务层处理
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@RequiredArgsConstructor
@Service
public class AppExtendService implements IBaseService<AppExtend, AppExtendVo, AppExtendBo> {

    private final AppExtendMapper appExtendMapper;

    @Override
    public IBaseMapper<AppExtend> mapper() {
        return appExtendMapper;
    }

    @Override
    public LambdaQueryWrapper<AppExtend> buildQueryWrapper(AppExtendBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<AppExtend> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getAppId() != null, AppExtend::getAppId, bo.getAppId());
        lqw.like(StringUtils.isNotBlank(bo.getKey()), AppExtend::getKey, bo.getKey());
        lqw.like(StringUtils.isNotBlank(bo.getLabel()), AppExtend::getLabel, bo.getLabel());
        lqw.like(StringUtils.isNotBlank(bo.getVersion()), AppExtend::getVersion, bo.getVersion());
        return lqw;
    }

}
