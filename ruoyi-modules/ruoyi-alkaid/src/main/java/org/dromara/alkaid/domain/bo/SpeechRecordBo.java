package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.SpeechRecord;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 语音记录管理业务对象 speech_record
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = SpeechRecord.class, reverseConvertGenerate = false)
public class SpeechRecordBo extends BaseEntity {

    /**
     * 主键id
     */
    private Long id;

    /**
     * 用户id
     */
    @NotNull(message = "用户id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;

    /**
     * App编号
     */
    @NotBlank(message = "App编号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String appCode;

    /**
     * 客户端类型
     */
    @NotNull(message = "客户端类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer client;

    /**
     * 识别花费字符数
     */
    @NotNull(message = "识别花费字符数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long costChar;

    /**
     * 识别花费时常
     */
    @NotNull(message = "识别花费时常不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long costTime;

    /**
     * 识别花费次数
     */
    @NotNull(message = "识别花费次数不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long costTimes;

    /**
     * 语音业务类型
     */
    @NotNull(message = "语音类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long type;

    /**
     * 备注
     */
    private String remark;


}
