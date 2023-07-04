package org.jshand.module.doctor.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jshand.common.HisConstants;
import org.jshand.module.commondata.domain.CheckItem;
import org.jshand.module.commondata.domain.InspectItem;
import org.jshand.module.commondata.service.ICheckItemService;
import org.jshand.module.commondata.service.IInspectItemService;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.module.doctor.domain.InspectApply;
import org.jshand.module.doctor.service.ICheckApplyService;
import org.jshand.module.doctor.service.IInspectApplyService;
import org.jshand.module.doctor.vo.DoctorVo;
import org.jshand.module.doctor.vo.RegisterVO;
import org.jshand.module.registe.domain.Register;
import org.jshand.module.registe.service.IRegisterService;
import org.jshand.support.common.annotation.Log;
import org.jshand.support.common.core.domain.AjaxResult;
import org.jshand.support.common.core.domain.Ret;
import org.jshand.support.common.enums.BusinessType;
import org.jshand.support.common.utils.SecurityUtils;
import org.jshand.support.common.utils.bean.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 医生相关的操作
 */
@RestController
@Api(tags = "医生相关操作")
@RequestMapping("/doctor")
public class DoctorController {


    @Autowired
    IRegisterService registerService;

    @Autowired
    ICheckItemService checkItemService;

    @Autowired
    IInspectItemService inspectItemService;



    @Autowired
    ICheckApplyService checkApplyService;

    @Autowired
    IInspectApplyService inspectApplyService;


    /**
     * 根据类型获取医生门诊首页
     *
     * @param type
     * @return
     */
//    @PreAuthorize("ss.hasAnyPermi('doctor:doctor:listRegister')")
    @GetMapping("/listRegister/{type}")
    public Ret<List<Register>> listRegister(@PathVariable String type) {

        LambdaQueryWrapper<Register> queryWrapper = new LambdaQueryWrapper<>();
        if ("person".equals(type)) {
            // where doctor_id = ?
            queryWrapper.eq(Register::getDoctorId, SecurityUtils.getUserId());
        } else if ("dept".equals(type)) {
            queryWrapper.eq(Register::getDeptId, SecurityUtils.getDeptId());

        }

        return Ret.success(registerService.list(queryWrapper));


    }


    @ApiOperation(value = "保存门诊病例")
//    @PreAuthorize("@ss.hasPermi('doctor:doctor:saveRegister')")
    @Log(title = "诊疗信息", businessType = BusinessType.UPDATE)
    @PutMapping("/saveRegister")
    public Ret<Boolean> saveRegister(@RequestBody RegisterVO registerVO) {


        Register register = new Register();

        BeanUtils.copyBeanProp(register, registerVO);

        if (new Long(1).equals(registerVO.getStatus())) { //暂存
            register.setStatus(HisConstants.REGIST_STATUS_2);
        } else if (new Long(2).equals(registerVO.getStatus())) { //提交
            register.setStatus(HisConstants.REGIST_STATUS_3);
        }


        return Ret.success(registerService.updateById(register));
    }



    /**
     * 查询检查项目管理列表
     */
    @ApiOperation(value = "查询检查项目管理列表")
//    @PreAuthorize("@ss.hasPermi('commondata:check_item:list')")
    @GetMapping("/check_item/list")
    public Page<CheckItem> list(CheckItem entity, Page<CheckItem> page) {

        LambdaQueryWrapper<CheckItem> lambdaQueryWrapper = new LambdaQueryWrapper<CheckItem>();
        return checkItemService.page(page, lambdaQueryWrapper);
    }

    /**
     * 查询检查项目管理列表
     */
    @ApiOperation(value = "查询检验项目管理列表")
//    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:list')")
    @GetMapping("/inspect_item/list")
    public Page<InspectItem> list(InspectItem entity, Page<InspectItem> page) {

        LambdaQueryWrapper<InspectItem> lambdaQueryWrapper = new LambdaQueryWrapper<InspectItem>();
        return inspectItemService.page(page, lambdaQueryWrapper);
    }



    @ApiOperation(value = "保存患者的检查项目")
//    @PreAuthorize("@ss.hasPermi('commondata:check_item:list')")
    @PostMapping("/saveCheckList")
    public Ret<Boolean> saveCheckList(@RequestBody List<CheckApply> checkAppliyList) {


        //批量保存
        checkAppliyList.forEach(item->{
            //设置为待缴费的状态
            item.setStatus(HisConstants.DOCTOR_CHECK_APPLY_STATUS_1);
        });

        boolean success = checkApplyService.saveBatch(checkAppliyList);

        return Ret.success(success);

    }

    @ApiOperation(value = "保存患者的检验项目")
//    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:list')")
    @PostMapping("/saveInspectList")
    public Ret<Boolean> saveInspectList(@RequestBody List<InspectApply> inspectAppliyList) {


        //批量保存
        inspectAppliyList.forEach(item->{
            //设置为待缴费的状态
            item.setStatus(HisConstants.DOCTOR_CHECK_APPLY_STATUS_1);
        });

        boolean success = inspectApplyService.saveBatch(inspectAppliyList);

        return Ret.success(success);

    }




    @ApiOperation(value = "根据患者id查询检查、检验项目")
//    @PreAuthorize("@ss.hasPermi('commondata:check_item:list')")
    @GetMapping("/getInfo/{id}")
    public Ret<DoctorVo> getInfo(@PathVariable  Long id) {


        DoctorVo vo = new DoctorVo();

        //根据患者id查询检查项目
        LambdaQueryWrapper<CheckApply> checkApplyQueryWrapper = new LambdaQueryWrapper<CheckApply>();
        checkApplyQueryWrapper.eq(CheckApply::getRegisterId,id);
        vo.setCheckApplyList(checkApplyService.list(checkApplyQueryWrapper));


        //已申请的检验
        LambdaQueryWrapper<InspectApply> inspectApplyQueryWrapper = new LambdaQueryWrapper<InspectApply>();
        inspectApplyQueryWrapper.eq(InspectApply::getRegisterId,id);
        vo.setInspectApplyList(inspectApplyService.list(inspectApplyQueryWrapper));
        return Ret.success(vo);

    }











}
