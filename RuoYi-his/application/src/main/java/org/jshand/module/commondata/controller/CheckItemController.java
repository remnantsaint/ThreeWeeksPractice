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
import org.jshand.module.commondata.domain.CheckItem;
import org.jshand.module.commondata.service.ICheckItemService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 检查项目管理Controller
 *
 * @author 金山老师
 * @date 2023-03-31
 */
@Api(tags = "检查项目管理模块")
@RestController
@RequestMapping("/commondata/check_item")
public class CheckItemController extends BaseController {
    @Autowired
    private ICheckItemService checkItemService;

    /**
     * 查询检查项目管理列表
     */
    @ApiOperation(value = "查询检查项目管理列表")
    @PreAuthorize("@ss.hasPermi('commondata:check_item:list')")
    @GetMapping("/list")
    public Page<CheckItem> list(CheckItem entity, Page<CheckItem> page) {

        LambdaQueryWrapper<CheckItem> lambdaQueryWrapper = new LambdaQueryWrapper<CheckItem>();
        return checkItemService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出检查项目管理列表
     */
    @ApiOperation(value = "导出检查项目管理列表")
    @PreAuthorize("@ss.hasPermi('commondata:check_item:export')")
    @Log(title = "检查项目管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CheckItem checkItem) {
        LambdaQueryWrapper<CheckItem> queryWrapper = new LambdaQueryWrapper<>();
        List<CheckItem> list = checkItemService.list(queryWrapper);
        ExcelUtil<CheckItem> util = new ExcelUtil<CheckItem>(CheckItem.class);
        util.exportExcel(response, list, "检查项目管理数据");
    }


    /**
     * 获取检查项目管理详细信息
     */
    @ApiOperation(value = "获取检查项目管理详细信息")
    @PreAuthorize("@ss.hasPermi('commondata:check_item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(checkItemService.getById(id));
    }

    /**
     * 新增检查项目管理
     */
    @ApiOperation(value = "新增检查项目管理")
    @PreAuthorize("@ss.hasPermi('commondata:check_item:add')")
    @Log(title = "检查项目管理", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CheckItem checkItem) {
        return toAjax(checkItemService.save(checkItem));
    }

    /**
     * 修改检查项目管理
     */
    @ApiOperation(value = "修改检查项目管理")
    @PreAuthorize("@ss.hasPermi('commondata:check_item:edit')")
    @Log(title = "检查项目管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CheckItem checkItem) {
        return toAjax(checkItemService.updateById(checkItem));
    }


    /**
     * 删除检查项目管理
     */
    @ApiOperation(value = "删除检查项目管理")
    @PreAuthorize("@ss.hasPermi('commondata:check_item:remove')")
    @Log(title = "检查项目管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(checkItemService.removeBatchByIds(Arrays.asList(ids)));
    }
}
