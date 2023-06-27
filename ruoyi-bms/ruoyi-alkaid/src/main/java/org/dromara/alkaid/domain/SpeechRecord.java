package org.dromara.alkaid.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.io.Serial;

/**
 * 语音记录管理对象 speech_record
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("speech_record")
public class SpeechRecord extends BaseEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * appCode
     */
    private String appCode;

    /**
     * 客户端类型
     */
    private Integer client;

    /**
     * 识别花费字符数
     */
    private Long costChar;

    /**
     * 识别花费时常
     */
    private Long costTime;

    /**
     * 识别花费次数
     */
    private Long costTimes;

    /**
     * 语音业务类型
     */
    private Long type;

    /**
     * 备注
     */
    private String remark;


}
