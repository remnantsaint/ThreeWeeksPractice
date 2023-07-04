package org.jshand.module.registe.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

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
 * 诊疗信息对象 register
 *
 * @author 金山老师
 * @date 2023-03-29
 */
@Data
@Accessors(chain = true)
@TableName("register")
@ApiModel(value = "Register对象", description = "诊疗信息")
public class Register extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * id病历号
     */
    @ApiModelProperty("id病历号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 姓名
     */
    @Excel(name = "姓名")
    @ApiModelProperty("姓名")
    @TableField("name")
    private String name;

    /**
     * 性别
     */
    @Excel(name = "性别")
    @ApiModelProperty("性别")
    @TableField("gender")
    private Long gender;

    /**
     * 身份证号
     */
    @Excel(name = "身份证号")
    @ApiModelProperty("身份证号")
    @TableField("idno")
    private String idno;

    /**
     * 出生日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("出生日期")
    @TableField("birthday")
    private Date birthday;

    /**
     * 年龄
     */
    @Excel(name = "年龄")
    @ApiModelProperty("年龄")
    @TableField("age")
    private Long age;

    /**
     * 家庭住址
     */
    @Excel(name = "家庭住址")
    @ApiModelProperty("家庭住址")
    @TableField("address")
    private String address;

    /**
     * 挂号级别
     */
    @Excel(name = "挂号级别")
    @ApiModelProperty("挂号级别")
    @TableField("regsit_level_id")
    private Long regsitLevelId;

    /**
     * 挂号科室
     */
    @Excel(name = "挂号科室")
    @ApiModelProperty("挂号科室")
    @TableField("dept_id")
    private Long deptId;

    /**
     * 看诊医生
     */
    @Excel(name = "看诊医生")
    @ApiModelProperty("看诊医生")
    @TableField("doctor_id")
    private Long doctorId;

    /**
     * 是否要病历本
     */
    @Excel(name = "是否要病历本")
    @ApiModelProperty("是否要病历本")
    @TableField("book")
    private String book;

    /**
     * 看诊时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "看诊时间", width = 30, dateFormat = "yyyy-MM-dd")
    @ApiModelProperty("看诊时间")
    @TableField("visittime")
    private Date visittime;

    /**
     * 挂号费用
     */
    @Excel(name = "挂号费用")
    @ApiModelProperty("挂号费用")
    @TableField("fee")
    private BigDecimal fee;

    /**
     * 主诉
     */
    @Excel(name = "主诉")
    @ApiModelProperty("主诉")
    @TableField("readme")
    private String readme;

    /**
     * 现病史
     */
    @Excel(name = "现病史")
    @ApiModelProperty("现病史")
    @TableField("present")
    private String present;

    /**
     * 现病史治疗情况
     */
    @Excel(name = "现病史治疗情况")
    @ApiModelProperty("现病史治疗情况")
    @TableField("present_treat")
    private String presentTreat;

    /**
     * 既往史
     */
    @Excel(name = "既往史")
    @ApiModelProperty("既往史")
    @TableField("history")
    private String history;

    /**
     * 过敏史
     */
    @Excel(name = "过敏史")
    @ApiModelProperty("过敏史")
    @TableField("allergy")
    private String allergy;

    /**
     * 确诊疾病
     */
    @Excel(name = "确诊疾病")
    @ApiModelProperty("确诊疾病")
    @TableField("disease")
    private String disease;

    /**
     * 处置方案
     */
    @Excel(name = "处置方案")
    @ApiModelProperty("处置方案")
    @TableField("suit")
    private String suit;

    /**
     * 药品清单
     */
    @Excel(name = "药品清单")
    @ApiModelProperty("药品清单")
    @TableField("drug")
    private String drug;

    /**
     * 状态
     */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    @TableField("status")
    private Long status;


}
