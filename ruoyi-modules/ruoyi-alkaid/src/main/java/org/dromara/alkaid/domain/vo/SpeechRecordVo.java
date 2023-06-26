package org.dromara.alkaid.domain.vo;

import org.dromara.alkaid.domain.SpeechRecord;
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
 * 语音记录管理视图对象 speech_record
 *
 * @author beiyu
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = SpeechRecord.class)
public class SpeechRecordVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ExcelProperty(value = "主键id")
    private Long id;

    /**
     * 玩家id
     */
    @ExcelProperty(value = "用户id")
    private Long userId;

    @ExcelProperty(value = "用户名")
    private String userName;

    /**
     * appCode
     */
    @ExcelProperty(value = "App编码")
    private String appCode;

    @ExcelProperty(value = "App名称")
    private String appName;

    /**
     * 客户端,0:web,1:ios,2:android
     */
    @ExcelProperty(value = "客户端类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_client_type")
    private Integer client;

    /**
     * 识别花费字符数
     */
    @ExcelProperty(value = "识别花费字符数")
    private Long costChar;

    /**
     * 识别花费时常
     */
    @ExcelProperty(value = "识别花费时常")
    private Long costTime;

    /**
     * 识别花费次数
     */
    @ExcelProperty(value = "识别花费次数")
    private Long costTimes;

    /**
     * 类型,1:tts,2:asr
     */
    @ExcelProperty(value = "语音业务类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "ensoul_speech_type")
    private Long type;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

}
