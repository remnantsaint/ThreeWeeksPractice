package org.jshand.module.system.domain;

import java.util.Arrays;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.jshand.support.common.annotation.Excel;
import org.jshand.support.common.core.domain.BaseEntity;


/**
 * 文件信息对象 sys_file_info
 *
 * @author 金山老师
 * @date 2023-04-11
 */
@Data
@Accessors(chain = true)
@TableName("sys_file_info")
@ApiModel(value = "SysFileInfo对象", description = "文件信息")
public class SysFileInfo {
    private static final long serialVersionUID=1L;

    /** 文件id */
    @ApiModelProperty("文件id")
    @TableId(value = "file_id", type = IdType.AUTO)
    private Long fileId;

    /** 文件名称 */
    @Excel(name = "文件名称")
    @ApiModelProperty("文件名称")
    @TableField("file_name")
    private String fileName;

    /** 原始文件名字 */
    @Excel(name = "原始文件名字")
    @ApiModelProperty("原始文件名字")
    @TableField("org_name")
    private String orgName;

    /** 文件路径 */
    @Excel(name = "文件路径")
    @ApiModelProperty("文件路径")
    @TableField("file_path")
    private String filePath;

    /** 上传的IP地址 */
    @Excel(name = "上传的IP地址")
    @ApiModelProperty("上传的IP地址")
    @TableField("ip")
    private String ip;



    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者", example = "作者", hidden = true)
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间", example = "2021-03-09 14:10:36",hidden = true)
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;


}
