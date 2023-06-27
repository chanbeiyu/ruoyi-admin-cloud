package org.dromara.alkaid.service;

import org.dromara.alkaid.domain.AlkaidImg;
import org.dromara.alkaid.domain.vo.AlkaidImgVo;
import org.dromara.alkaid.domain.bo.AlkaidImgBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 图片信息Service接口
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
public interface IAlkaidImgService {

    /**
     * 查询图片信息
     */
    AlkaidImgVo queryById(Long imgId);

    /**
     * 查询图片信息列表
     */
    TableDataInfo<AlkaidImgVo> queryPageList(AlkaidImgBo bo, PageQuery pageQuery);

    /**
     * 查询图片信息列表
     */
    List<AlkaidImgVo> queryList(AlkaidImgBo bo);

    /**
     * 新增图片信息
     */
    Boolean insertByBo(AlkaidImgBo bo);

    /**
     * 修改图片信息
     */
    Boolean updateByBo(AlkaidImgBo bo);

    /**
     * 校验并批量删除图片信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
