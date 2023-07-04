package org.jshand.module.medical_technology.vo;


import lombok.Data;

/**
 * {
 * 	"fileName": "/profile/upload/2023/04/11/教育账号_20230411112519A003.bmp",
 * 	"newFileName": "教育账号_20230411112519A003.bmp",
 * 	"originalFilename": "教育账号.bmp"
 * }
 */
@Data
public class CheckApplyResultVo {

    private Long id;



    private String result ;

    private String fileName;  // newFileName
    private String filePath	;   //fileName
    private String orgName;   //originalFilename


}
