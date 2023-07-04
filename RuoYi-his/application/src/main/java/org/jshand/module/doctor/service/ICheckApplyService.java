package org.jshand.module.doctor.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.module.medical_technology.vo.ApplyQueryVo;
import org.jshand.module.medical_technology.vo.CheckApplyResultVo;
import org.jshand.module.medical_technology.vo.CheckApplyVO;

import javax.servlet.http.HttpServletRequest;

/**
 * 检查申请Service接口
 *
 * @author 金山老师
 * @date 2023-04-03
 */
public interface ICheckApplyService extends IService<CheckApply> {


    Page<CheckApplyVO> listApplyByRegister(IPage<CheckApplyVO> page , @Param("vo") ApplyQueryVo vo);


    boolean result(CheckApplyResultVo vo, HttpServletRequest request);
}