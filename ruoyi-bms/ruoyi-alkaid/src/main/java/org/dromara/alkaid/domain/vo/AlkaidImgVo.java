package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.AlkaidImg;
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
 * 图片信息视图对象 alkaid_img
 *
 * @author chanbeiyu
 * @date 2023-06-20
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AlkaidImg.class)
public class AlkaidImgVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 图片id
     */
    @ExcelProperty(value = "图片id")
    private Long imgId;

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
     * 标签,英文逗号分隔
     */
    @ExcelProperty(value = "标签,英文逗号分隔")
    private String tags;

    /**
     * 原图地址
     */
    @ExcelProperty(value = "原图地址")
    private String imgUrl;

    /**
     * 小图地址
     */
    @ExcelProperty(value = "小图地址")
    private String imgSUrl;

    /**
     * 大图地址
     */
    @ExcelProperty(value = "大图地址")
    private String imgLUrl;

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
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
