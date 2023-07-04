package org.jshand.module.doctor.domain;

import java.math.BigDecimal;
import java.util.Arrays;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jshand.support.common.annotation.Excel;
import org.jshand.support.common.core.domain.BaseEntity;


/**
 * 检验申请对象 inspect_apply
 *
 * @author 金山老师
 * @date 2023-04-03
 */
@Data
@Accessors(chain = true)
@TableName("inspect_apply")
@ApiModel(value = "InspectApply对象", description = "检验申请")
public class InspectApply {
    private static final long serialVersionUID=1L;

    /** id */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 项目id */
    @Excel(name = "项目id")
    @ApiModelProperty("项目id")
    @TableField("item_id")
    private Long itemId;

    /** 项目名称 */
    @Excel(name = "项目名称")
    @ApiModelProperty("项目名称")
    @TableField("item_name")
    private String itemName;

    /** 检查费用 */
    @Excel(name = "检查费用")
    @ApiModelProperty("检查费用")
    @TableField("fee")
    private BigDecimal fee;

    /** 状态 */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    @TableField("status")
    private Long status;

    /** 病历号 */
    @Excel(name = "病历号")
    @ApiModelProperty("病历号")
    @TableField("register_id")
    private Long registerId;

    /** 是否有效，1 有效，0 失效 */
    @Excel(name = "是否有效，1 有效，0 失效")
    @ApiModelProperty("是否有效，1 有效，0 失效")
    @TableField("active")
    private Long active;


}
