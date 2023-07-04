package org.jshand.module.system.domain;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 学生管理对象 sys_stu
 *
 * @author 张金山
 * @date 2023-03-24
 */
@Data
@Accessors(chain = true)
@TableName("sys_stu")
@ApiModel(value = "SysStu对象", description = "学生管理")
public class SysStu extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty("主键")
    @TableId(value = "id", type = IdType.ASSIGN_UUID)
    private String id;

    /**
     * 学生姓名
     */
    @Excel(name = "学生姓名")
    @ApiModelProperty("学生姓名")
    @TableField("stu_name")
    private String stuName;

    /**
     * 家庭住址
     */
    @Excel(name = "家庭住址")
    @ApiModelProperty("家庭住址")
    @TableField("address")
    private String address;

    /**
     * 出生日期
     */
    @ApiModelProperty("出生日期")
    @TableField("birthday")
    private Date birthday;




}
