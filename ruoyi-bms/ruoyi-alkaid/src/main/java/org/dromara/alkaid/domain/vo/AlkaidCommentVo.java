package org.dromara.alkaid.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.alkaid.domain.AlkaidComment;
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
 * 图集评论信息视图对象 alkaid_comment
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidComment.class)
public class AlkaidCommentVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @ExcelProperty(value = "评论id")
    private Long commentId;

    /**
     * 父评论(回复)id
     */
    @ExcelProperty(value = "父评论(回复)id")
    private Long commentPid;

    /**
     * 评论用户id
     */
    @ExcelProperty(value = "评论用户id")
    private Long commentUserId;

    /**
     * 评论内容
     */
    @ExcelProperty(value = "评论内容")
    private String commentContent;

    /**
     * 集id
     */
    @ExcelProperty(value = "集id")
    private Long albumId;

    /**
     * 点赞数
     */
    @ExcelProperty(value = "点赞数")
    private Long likeNum;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_data_2status")
    private Long status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 删除时间
     */
    @ExcelProperty(value = "删除时间")
    private Date deleteTime;


}
