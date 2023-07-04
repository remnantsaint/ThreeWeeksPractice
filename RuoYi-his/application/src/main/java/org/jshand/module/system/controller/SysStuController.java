package org.jshand.module.system.controller;

import java.util.Arrays;
import java.util.List;
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
import org.jshand.module.system.domain.SysStu;
import org.jshand.module.system.service.ISysStuService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 学生管理Controller
 *
 * @author 张金山
 * @date 2023-03-24
 */
@Api(tags = "学生管理模块")
@RestController
@RequestMapping("/system/stu")
public class SysStuController extends BaseController {
    @Autowired
    private ISysStuService sysStuService;

/**
 * 查询学生管理列表
 */
@ApiOperation(value = "查询学生管理列表")
@PreAuthorize("@ss.hasPermi('system:stu:list')")
@GetMapping("/list")
    public Page<SysStu> list(SysStu entity, Page<SysStu> page) {

        LambdaQueryWrapper<SysStu> lambdaQueryWrapper = new LambdaQueryWrapper<SysStu>();
        return sysStuService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出学生管理列表
     */
    @ApiOperation(value = "导出学生管理列表")
    @PreAuthorize("@ss.hasPermi('system:stu:export')")
    @Log(title = "学生管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysStu sysStu) {
        LambdaQueryWrapper<SysStu> queryWrapper = new LambdaQueryWrapper<>();
        List<SysStu> list = sysStuService.list(queryWrapper);
        ExcelUtil<SysStu> util = new ExcelUtil<SysStu>(SysStu. class);
        util.exportExcel(response, list, "学生管理数据");
    }


    /**
     * 获取学生管理详细信息
     */
    @ApiOperation(value = "获取学生管理详细信息")
    @PreAuthorize("@ss.hasPermi('system:stu:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(sysStuService.getById(id));
    }

    /**
     * 新增学生管理
     */
    @ApiOperation(value = "新增学生管理")
    @PreAuthorize("@ss.hasPermi('system:stu:add')")
    @Log(title = "学生管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysStu sysStu) {
        return toAjax(sysStuService.save(sysStu));
    }

    /**
     * 修改学生管理
     */
    @ApiOperation(value = "修改学生管理")
    @PreAuthorize("@ss.hasPermi('system:stu:edit')")
    @Log(title = "学生管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysStu sysStu) {
        return toAjax(sysStuService.updateById(sysStu));
    }


    /**
     * 删除学生管理
     */
    @ApiOperation(value = "删除学生管理")
    @PreAuthorize("@ss.hasPermi('system:stu:remove')")
    @Log(title = "学生管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable String[] ids) {
        return toAjax(sysStuService.removeBatchByIds(Arrays.asList(ids)));
    }
}
