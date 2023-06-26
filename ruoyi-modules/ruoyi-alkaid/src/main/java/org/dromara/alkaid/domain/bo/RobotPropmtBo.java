package org.dromara.alkaid.domain.bo;

import org.dromara.alkaid.domain.RobotPropmt;
import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * Ai Propmt业务对象 robot_propmt
 *
 * @author beiyu
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = RobotPropmt.class, reverseConvertGenerate = false)
public class RobotPropmtBo extends BaseEntity {

    /**
     * 主键id
     */
    @NotNull(message = "主键id不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 主题
     */
    @NotBlank(message = "主题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String subject;

    /**
     * 标题
     */
    @NotBlank(message = "标题不能为空", groups = {AddGroup.class, EditGroup.class})
    private String title;

    /**
     * 内容
     */
    @NotBlank(message = "内容不能为空", groups = {AddGroup.class, EditGroup.class})
    private String content;

    /**
     * 平台
     */
    @NotNull(message = "平台不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer platform;

    /**
     * 来源
     */
    @NotNull(message = "来源不能为空", groups = {AddGroup.class, EditGroup.class})
    private Integer source;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = {AddGroup.class, EditGroup.class})
    private String remark;


}
