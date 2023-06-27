package org.dromara.alkaid.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.alkaid.domain.AlkaidAlbum;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 图集信息视图对象 alkaid_album
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidAlbum.class)
public class AlkaidAlbumVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 集id
     */
    @ExcelProperty(value = "集id")
    private Long albumId;

    /**
     * 创建用户id
     */
    @ExcelProperty(value = "创建用户id")
    private Long userId;

    /**
     * 标题
     */
    @ExcelProperty(value = "标题")
    private String title;

    /**
     * 描述
     */
    @ExcelProperty(value = "描述")
    private String describe;

    /**
     * 分类
     */
    @ExcelProperty(value = "分类")
    private Long category;

    /**
     * 标签,英文逗号分隔
     */
    @ExcelProperty(value = "标签,英文逗号分隔")
    private String tags;

    /**
     * 默认封面
     */
    @ExcelProperty(value = "默认封面")
    private String coverImg;

    /**
     * 收藏数
     */
    @ExcelProperty(value = "收藏数")
    private Long favorNum;

    /**
     * 点赞数
     */
    @ExcelProperty(value = "点赞数")
    private Long likeNum;

    /**
     * 是否置顶Y是N否
     */
    @ExcelProperty(value = "是否置顶Y是N否")
    private String isTop;

    /**
     * 状态:0草稿1正常2删除
     */
    @ExcelProperty(value = "状态:0草稿1正常2删除", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_data_3status")
    private Long status;

    /**
     * 发布时间
     */
    @ExcelProperty(value = "发布时间")
    private Date publishTime;

    /**
     * 删除时间
     */
    @ExcelProperty(value = "删除时间")
    private Date deleteTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
