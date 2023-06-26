package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.AppInfo;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;


/**
 * 应用信息视图对象 app_info
 *
 * @author beiyu
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = AppInfo.class)
public class AppInfoVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * App 编码
     */
    @ExcelProperty(value = "App 编码")
    private String code;

    /**
     * App 名称
     */
    @ExcelProperty(value = "App 名称")
    private String name;

    /**
     * App 类型
     */
    @ExcelProperty(value = "App 类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_app_type")
    private Integer type;

    /**
     * 口号
     */
    @ExcelProperty(value = "口号")
    private String slogan;

    /**
     * Logo
     */
    @ExcelProperty(value = "Logo")
    private String logo;

    /**
     * App 介绍
     */
    @ExcelProperty(value = "App 介绍")
    private String describes;

    /**
     * 网站地址
     */
    @ExcelProperty(value = "网站地址")
    private String websiteUrl;

    /**
     * iOS下载地址
     */
    @ExcelProperty(value = "iOS下载地址")
    private String iosUrl;

    /**
     * Android下载地址
     */
    @ExcelProperty(value = "Android下载地址")
    private String androidUrl;

    /**
     * 租户id
     */
    @ExcelProperty(value = "租户id")
    private Long tenantId;

    /**
     * 添加部门
     */
    @ExcelProperty(value = "添加部门")
    private Long createDept;

    /**
     * 添加用户
     */
    @ExcelProperty(value = "添加用户")
    private Long createBy;

    /**
     * 添加时间
     */
    @ExcelProperty(value = "添加时间")
    private Date createTime;

    /**
     * 更新用户
     */
    @ExcelProperty(value = "更新用户")
    private Long updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
