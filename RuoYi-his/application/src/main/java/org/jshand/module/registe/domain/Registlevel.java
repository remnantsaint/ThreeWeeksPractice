package org.jshand.module.registe.domain;

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
 * 挂号级别对象 registlevel
 *
 * @author 金山老师
 * @date 2023-03-28
 */
@Data
@Accessors(chain = true)
@TableName("registlevel")
@ApiModel(value = "Registlevel对象", description = "挂号级别")
public class Registlevel extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * ID主键
     */
    @ApiModelProperty("ID主键")
    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    /**
     * 号别编码
     */
    @Excel(name = "号别编码")
    @ApiModelProperty("号别编码")
    @TableField("RegistCode")
    private String registcode;

    /**
     * 号别名称
     */
    @Excel(name = "号别名称")
    @ApiModelProperty("号别名称")
    @TableField("RegistName")
    private String registname;

    /**
     * 显示顺序号
     */
    @Excel(name = "显示顺序号")
    @ApiModelProperty("显示顺序号")
    @TableField("SequenceNo")
    private Integer sequenceno;

    /**
     * 挂号费
     */
    @Excel(name = "挂号费")
    @ApiModelProperty("挂号费")
    @TableField("RegistFee")
    private BigDecimal registfee;

    /**
     * 挂号限额
     */
    @Excel(name = "挂号限额")
    @ApiModelProperty("挂号限额")
    @TableField("RegistQuota")
    private Integer registquota;





}
