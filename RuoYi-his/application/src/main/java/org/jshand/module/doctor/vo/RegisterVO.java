package org.jshand.module.doctor.vo;

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
@ApiModel(value = "患者病例首页", description = "诊疗信息")
public class RegisterVO {
    private static final long serialVersionUID = 1L;

    /**
     * id病历号
     */
    @ApiModelProperty("id病历号")
    private Long id;


    /**
     * 主诉
     */
    @Excel(name = "主诉")
    @ApiModelProperty("主诉")
    private String readme;

    /**
     * 现病史
     */
    @Excel(name = "现病史")
    @ApiModelProperty("现病史")
    private String present;

    /**
     * 现病史治疗情况
     */
    @Excel(name = "现病史治疗情况")
    @ApiModelProperty("现病史治疗情况")
    private String presentTreat;

    /**
     * 既往史
     */
    @Excel(name = "既往史")
    @ApiModelProperty("既往史")
    private String history;

    /**
     * 过敏史
     */
    @Excel(name = "过敏史")
    @ApiModelProperty("过敏史")
    private String allergy;

    /**
     * 确诊疾病
     */
    @Excel(name = "确诊疾病")
    @ApiModelProperty("确诊疾病")
    private String disease;

    /**
     * 处置方案
     */
    @Excel(name = "处置方案")
    @ApiModelProperty("处置方案")
    private String suit;

    /**
     * 药品清单
     */
    @Excel(name = "药品清单")
    @ApiModelProperty("药品清单")
    private String drug;

    /**
     * 状态  1 暂存， 2 提交
     */
    @Excel(name = "状态")
    @ApiModelProperty("状态")
    private Long status;


}
