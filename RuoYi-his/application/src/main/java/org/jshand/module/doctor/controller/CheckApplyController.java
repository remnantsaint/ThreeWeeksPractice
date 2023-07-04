package org.jshand.module.doctor.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.jshand.module.medical_technology.vo.ApplyQueryVo;
import org.jshand.module.medical_technology.vo.CheckApplyResultVo;
import org.jshand.module.medical_technology.vo.CheckApplyVO;
import org.jshand.support.common.core.domain.Ret;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.jshand.support.common.annotation.Log;
import org.jshand.support.common.core.controller.BaseController;
import org.jshand.support.common.core.domain.AjaxResult;
import org.jshand.support.common.enums.BusinessType;
import org.jshand.module.doctor.domain.CheckApply;
import org.jshand.module.doctor.service.ICheckApplyService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 检查申请Controller
 *
 * @author 金山老师
 * @date 2023-04-03
 */
@Api(tags = "检查申请模块")
@RestController
@RequestMapping("/doctor/check_apply")
public class CheckApplyController extends BaseController {
    @Autowired
    private ICheckApplyService checkApplyService;

    /**
     * 查询检查申请列表
     */
    @ApiOperation(value = "查询检查申请列表")
    @PreAuthorize("@ss.hasPermi('doctor:apply:list')")
    @GetMapping("/list")
    public Page<CheckApply> list(CheckApply entity, Page<CheckApply> page) {

        LambdaQueryWrapper<CheckApply> lambdaQueryWrapper = new LambdaQueryWrapper<CheckApply>();
        return checkApplyService.page(page, lambdaQueryWrapper);
    }


    /**
     * 查询检查申请列表
     */
    @ApiOperation(value = "查询检查申请列表关联患者")
    @PreAuthorize("@ss.hasPermi('doctor:apply:listByRegister')")
    @GetMapping("/listByRegister")
    public Page<CheckApplyVO> listByRegister(ApplyQueryVo vo, Page<CheckApplyVO> page) {

        return checkApplyService.listApplyByRegister(page, vo);
    }

    /**
     * 导出检查申请列表
     */
    @ApiOperation(value = "导出检查申请列表")
    @PreAuthorize("@ss.hasPermi('doctor:apply:export')")
    @Log(title = "检查申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckApply checkApply) {
        LambdaQueryWrapper<CheckApply> queryWrapper = new LambdaQueryWrapper<>();
        List<CheckApply> list = checkApplyService.list(queryWrapper);
        ExcelUtil<CheckApply> util = new ExcelUtil<CheckApply>(CheckApply.class);
        util.exportExcel(response, list, "检查申请数据");
    }


    /**
     * 获取检查申请详细信息
     */
    @ApiOperation(value = "获取检查申请详细信息")
    @PreAuthorize("@ss.hasPermi('doctor:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(checkApplyService.getById(id));
    }

    /**
     * 新增检查申请
     */
    @ApiOperation(value = "新增检查申请")
    @PreAuthorize("@ss.hasPermi('doctor:apply:add')")
    @Log(title = "检查申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckApply checkApply) {
        return toAjax(checkApplyService.save(checkApply));
    }

    /**
     * 修改检查申请
     */
    @ApiOperation(value = "修改检查申请")
    @PreAuthorize("@ss.hasPermi('doctor:apply:edit')")
    @Log(title = "检查申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckApply checkApply) {
        return toAjax(checkApplyService.updateById(checkApply));
    }


    /**
     * 删除检查申请
     */
    @ApiOperation(value = "删除检查申请")
    @PreAuthorize("@ss.hasPermi('doctor:apply:remove')")
    @Log(title = "检查申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(checkApplyService.removeBatchByIds(Arrays.asList(ids)));
    }



    @ApiOperation(value = "填写检查结果")
//    @PreAuthorize("@ss.hasPermi('doctor:apply:result')")
    @PostMapping("/result")
    public Ret<Boolean> result(@RequestBody CheckApplyResultVo vo, HttpServletRequest request){

        boolean success = checkApplyService.result(vo, request);
        return Ret.success(null);

    }











}
