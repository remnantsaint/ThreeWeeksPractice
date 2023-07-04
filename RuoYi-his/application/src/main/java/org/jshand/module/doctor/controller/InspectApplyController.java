package org.jshand.module.doctor.controller;

import java.util.List;
import java.util.Arrays;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
import org.jshand.module.doctor.domain.InspectApply;
import org.jshand.module.doctor.service.IInspectApplyService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 检验申请Controller
 *
 * @author 金山老师
 * @date 2023-04-03
 */
@Api(tags = "检验申请模块")
@RestController
@RequestMapping("/doctor/inspect_apply")
public class InspectApplyController extends BaseController {
    @Autowired
    private IInspectApplyService inspectApplyService;

    /**
     * 查询检验申请列表
     */
    @ApiOperation(value = "查询检验申请列表")
    @PreAuthorize("@ss.hasPermi('doctor:apply:list')")
    @GetMapping("/list")
    public Page<InspectApply> list(InspectApply entity, Page<InspectApply> page) {

        LambdaQueryWrapper<InspectApply> lambdaQueryWrapper = new LambdaQueryWrapper<InspectApply>();
        return inspectApplyService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出检验申请列表
     */
    @ApiOperation(value = "导出检验申请列表")
    @PreAuthorize("@ss.hasPermi('doctor:apply:export')")
    @Log(title = "检验申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InspectApply inspectApply) {
        LambdaQueryWrapper<InspectApply> queryWrapper = new LambdaQueryWrapper<>();
        List<InspectApply> list = inspectApplyService.list(queryWrapper);
        ExcelUtil<InspectApply> util = new ExcelUtil<InspectApply>(InspectApply.class);
        util.exportExcel(response, list, "检验申请数据");
    }


    /**
     * 获取检验申请详细信息
     */
    @ApiOperation(value = "获取检验申请详细信息")
    @PreAuthorize("@ss.hasPermi('doctor:apply:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(inspectApplyService.getById(id));
    }

    /**
     * 新增检验申请
     */
    @ApiOperation(value = "新增检验申请")
    @PreAuthorize("@ss.hasPermi('doctor:apply:add')")
    @Log(title = "检验申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectApply inspectApply) {
        return toAjax(inspectApplyService.save(inspectApply));
    }

    /**
     * 修改检验申请
     */
    @ApiOperation(value = "修改检验申请")
    @PreAuthorize("@ss.hasPermi('doctor:apply:edit')")
    @Log(title = "检验申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectApply inspectApply) {
        return toAjax(inspectApplyService.updateById(inspectApply));
    }


    /**
     * 删除检验申请
     */
    @ApiOperation(value = "删除检验申请")
    @PreAuthorize("@ss.hasPermi('doctor:apply:remove')")
    @Log(title = "检验申请", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(inspectApplyService.removeBatchByIds(Arrays.asList(ids)));
    }
}
