package org.dromara.basal.thoughts.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.dromara.basal.thoughts.domain.ThotThought;
import org.dromara.common.mybatis.core.mapper.IBaseMapper;

/**
 * 思绪信息Mapper接口
 *
 * @author chanbeiyu
 * @date 2023-07-01
 */
@Mapper
public interface ThotThoughtMapper extends IBaseMapper<ThotThought> {

}
