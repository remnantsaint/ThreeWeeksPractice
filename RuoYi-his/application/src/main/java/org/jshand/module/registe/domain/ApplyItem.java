package org.jshand.module.registe.domain;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.jshand.support.common.annotation.Excel;

import java.math.BigDecimal;

@Data
public class ApplyItem {
    /** id */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 病历号 */
    @ApiModelProperty("病历号")
    @TableField("register_id")
    private Long registerId;

    /** 项目id */
    @ApiModelProperty("项目id")
    @TableField("item_id")
    private Long itemId;

    /** 项目名称 */
    @ApiModelProperty("项目名称")
    @TableField("item_name")
    private String itemName;

    /** 检查费用 */
    @ApiModelProperty("检查费用")
    @TableField("fee")
    private BigDecimal fee;

    /** 状态 */
    @ApiModelProperty("状态")
    @TableField("status")
    private Long status;

    /** 是否有效，1 有效，0 失效 */
    @ApiModelProperty("是否有效，1 有效，0 失效")
    @TableField("active")
    private Long active;


    /** 项目名称 */
    @ApiModelProperty("项目类型")
    @TableField("type")
    private String type;

       /** 项目名称 */
    @ApiModelProperty("身份证号")
    @TableField("idno")
    private String idno;


    @ApiModelProperty("患者姓名")
    private String registerName;//

}
