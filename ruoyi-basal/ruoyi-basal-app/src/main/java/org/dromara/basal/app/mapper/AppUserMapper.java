package org.dromara.basal.app.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.basal.app.domain.AppInfo;
import org.dromara.basal.app.domain.AppUser;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;

import java.util.List;

/**
 * 用户应用信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
public interface AppUserMapper extends IBaseMapper<AppUser> {

    List<AppInfo> selectByUserId(@Param("userId") Long userId);

}
