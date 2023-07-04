package org.jshand.module.system.controller;

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
import org.jshand.module.system.domain.SysFileInfo;
import org.jshand.module.system.service.ISysFileInfoService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 文件信息Controller
 *
 * @author 金山老师
 * @date 2023-04-11
 */
@Api(tags = "文件信息模块")
@RestController
@RequestMapping("/system/file_info")
public class SysFileInfoController extends BaseController {
    @Autowired
    private ISysFileInfoService sysFileInfoService;

    /**
     * 查询文件信息列表
     */
    @ApiOperation(value = "查询文件信息列表")
    @PreAuthorize("@ss.hasPermi('system:file_info:list')")
    @GetMapping("/list")
    public Page<SysFileInfo> list(SysFileInfo entity, Page<SysFileInfo> page) {

        LambdaQueryWrapper<SysFileInfo> lambdaQueryWrapper = new LambdaQueryWrapper<SysFileInfo>();
        return sysFileInfoService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出文件信息列表
     */
    @ApiOperation(value = "导出文件信息列表")
    @PreAuthorize("@ss.hasPermi('system:file_info:export')")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysFileInfo sysFileInfo) {
        LambdaQueryWrapper<SysFileInfo> queryWrapper = new LambdaQueryWrapper<>();
        List<SysFileInfo> list = sysFileInfoService.list(queryWrapper);
        ExcelUtil<SysFileInfo> util = new ExcelUtil<SysFileInfo>(SysFileInfo.class);
        util.exportExcel(response, list, "文件信息数据");
    }


    /**
     * 获取文件信息详细信息
     */
    @ApiOperation(value = "获取文件信息详细信息")
//    @PreAuthorize("@ss.hasPermi('system:file_info:query')")
    @GetMapping(value = "/{fileId}")
    public AjaxResult getInfo(@PathVariable("fileId") Long id) {
        return success(sysFileInfoService.getById(id));
    }

    /**
     * 新增文件信息
     */
    @ApiOperation(value = "新增文件信息")
    @PreAuthorize("@ss.hasPermi('system:file_info:add')")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysFileInfo sysFileInfo) {
        return toAjax(sysFileInfoService.save(sysFileInfo));
    }

    /**
     * 修改文件信息
     */
    @ApiOperation(value = "修改文件信息")
    @PreAuthorize("@ss.hasPermi('system:file_info:edit')")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysFileInfo sysFileInfo) {
        return toAjax(sysFileInfoService.updateById(sysFileInfo));
    }


    /**
     * 删除文件信息
     */
    @ApiOperation(value = "删除文件信息")
    @PreAuthorize("@ss.hasPermi('system:file_info:remove')")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{fileIds}")
    public AjaxResult remove(@PathVariable Long[] fileIds) {
        return toAjax(sysFileInfoService.removeBatchByIds(Arrays.asList(fileIds)));
    }
}
