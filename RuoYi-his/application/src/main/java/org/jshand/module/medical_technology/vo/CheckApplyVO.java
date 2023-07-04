package org.jshand.module.medical_technology.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.support.common.annotation.Excel;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 检查申请对象 check_apply
 *
 * @author 金山老师
 * @date 2023-04-03
 */
@Data
@Accessors(chain = true)
@TableName("check_apply")
@ApiModel(value = "CheckApply对象", description = "检查申请")
public class CheckApplyVO  extends CheckApply {



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



    //开立检查的 时间  jackson  序列化  long
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createtime;


    @Override
    public String toString() {
        return "CheckApplyVO{" +
                super.toString()

                +
                "name='" + name + '\'' +
                ", gender=" + gender +
                ", idno='" + idno + '\'' +
                ", birthday=" + birthday +
                ", age=" + age +
                ", address='" + address + '\'' +
                ", regsitLevelId=" + regsitLevelId +
                ", deptId=" + deptId +
                ", doctorId=" + doctorId +
                ", book='" + book + '\'' +
                ", visittime=" + visittime +
                ", readme='" + readme + '\'' +
                ", present='" + present + '\'' +
                ", presentTreat='" + presentTreat + '\'' +
                ", history='" + history + '\'' +
                ", allergy='" + allergy + '\'' +
                ", disease='" + disease + '\'' +
                ", suit='" + suit + '\'' +
                ", drug='" + drug + '\'' +
                ", createtime=" + createtime +
                '}';
    }
}
