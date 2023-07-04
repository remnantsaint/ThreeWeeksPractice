package org.jshand.module.commondata.domain;

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
 * 检验项目管理对象 inspect_item
 *
 * @author 金山
 * @date 2023-03-31
 */
@Data
@Accessors(chain = true)
@TableName("inspect_item")
@ApiModel(value = "InspectItem对象", description = "检验项目管理")
public class InspectItem extends BaseEntity{
    private static final long serialVersionUID=1L;

    /** id */
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /** 检查名称 */
    @Excel(name = "检查名称")
    @ApiModelProperty("检查名称")
    @TableField("name")
    private String name;

    /** 检查费用 */
    @Excel(name = "检查费用")
    @ApiModelProperty("检查费用")
    @TableField("fee")
    private BigDecimal fee;


}
