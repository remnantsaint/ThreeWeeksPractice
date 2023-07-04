package org.jshand.module.commondata.controller;

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
import org.jshand.module.commondata.domain.InspectItem;
import org.jshand.module.commondata.service.IInspectItemService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 检验项目管理Controller
 *
 * @author 金山
 * @date 2023-03-31
 */
@Api(tags = "检验项目管理模块")
@RestController
@RequestMapping("/commondata/inspect_item")
public class InspectItemController extends BaseController {
    @Autowired
    private IInspectItemService inspectItemService;

/**
 * 查询检验项目管理列表
 */
@ApiOperation(value = "查询检验项目管理列表")
@PreAuthorize("@ss.hasPermi('commondata:inspect_item:list')")
@GetMapping("/list")
    public Page<InspectItem> list(InspectItem entity, Page<InspectItem> page) {

        LambdaQueryWrapper<InspectItem> lambdaQueryWrapper = new LambdaQueryWrapper<InspectItem>();
        return inspectItemService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出检验项目管理列表
     */
    @ApiOperation(value = "导出检验项目管理列表")
    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:export')")
    @Log(title = "检验项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, InspectItem inspectItem) {
        LambdaQueryWrapper<InspectItem> queryWrapper = new LambdaQueryWrapper<>();
        List<InspectItem> list = inspectItemService.list(queryWrapper);
        ExcelUtil<InspectItem> util = new ExcelUtil<InspectItem>(InspectItem. class);
        util.exportExcel(response, list, "检验项目管理数据");
    }


    /**
     * 获取检验项目管理详细信息
     */
    @ApiOperation(value = "获取检验项目管理详细信息")
    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(inspectItemService.getById(id));
    }

    /**
     * 新增检验项目管理
     */
    @ApiOperation(value = "新增检验项目管理")
    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:add')")
    @Log(title = "检验项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody InspectItem inspectItem) {
        return toAjax(inspectItemService.save(inspectItem));
    }

    /**
     * 修改检验项目管理
     */
    @ApiOperation(value = "修改检验项目管理")
    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:edit')")
    @Log(title = "检验项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody InspectItem inspectItem) {
        return toAjax(inspectItemService.updateById(inspectItem));
    }


    /**
     * 删除检验项目管理
     */
    @ApiOperation(value = "删除检验项目管理")
    @PreAuthorize("@ss.hasPermi('commondata:inspect_item:remove')")
    @Log(title = "检验项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(inspectItemService.removeBatchByIds(Arrays.asList(ids)));
    }
}
