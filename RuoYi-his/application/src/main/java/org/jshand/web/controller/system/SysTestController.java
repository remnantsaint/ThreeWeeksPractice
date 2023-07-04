package org.jshand.web.controller.system;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jshand.support.common.annotation.Log;
import org.jshand.support.common.core.controller.BaseController;
import org.jshand.support.common.core.domain.R;
import org.jshand.support.common.enums.BusinessType;
import org.jshand.support.common.utils.poi.ExcelUtil;
import org.jshand.module.system.domain.SysTest;
import org.jshand.module.system.service.ISysTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * 单表管理Controller
 *
 * @author 张金山
 * @date 2023-01-04
 */
@Api(tags = "单表管理")
@RestController
@RequestMapping("/system/test")
public class SysTestController extends BaseController {
    @Autowired
    private ISysTestService sysTestService;


    @ApiOperation(value = "测试功能")
    @PreAuthorize("@ss.hasPermi('system:test:list')")
    @GetMapping("/test")
    public SysTest test(SysTest entity) {

        return null;
    }


    /**
     * 查询单表管理列表
     */
    @ApiOperation(value = "查询单表管理列表")
    @PreAuthorize("@ss.hasPermi('system:test:list')")
    @GetMapping("/list")
    public R<Page<SysTest>> list(SysTest entity, Page<SysTest> page) {

        LambdaQueryWrapper<SysTest> lambdaQueryWrapper = new LambdaQueryWrapper<SysTest>();
        //return sysTestService.page(page, lambdaQueryWrapper);
        return R.ok(sysTestService.page(page, lambdaQueryWrapper));
    }

    /**
     * 导出单表管理列表
     */
    @ApiOperation(value = "导出单表管理列表")
    @PreAuthorize("@ss.hasPermi('system:test:export')")
    @Log(title = "单表管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysTest sysTest) {
        LambdaQueryWrapper<SysTest> queryWrapper = new LambdaQueryWrapper<>();
        List<SysTest> list = sysTestService.list(queryWrapper);
        ExcelUtil<SysTest> util = new ExcelUtil<SysTest>(SysTest.class);
        util.exportExcel(response, list, "单表管理数据");
    }


    /**
     * 获取单表管理详细信息
     */
    @ApiOperation(value = "获取单表管理详细信息")
    @PreAuthorize("@ss.hasPermi('system:test:query')")
    @GetMapping(value = "/{id}")
    public R<SysTest> getInfo(@PathVariable("id") Long id) {
        return R.ok(sysTestService.getById(id));
    }

    /**
     * 新增单表管理
     */
    @ApiOperation(value = "新增单表管理")
    @PreAuthorize("@ss.hasPermi('system:test:add')")
    @Log(title = "单表管理", businessType = BusinessType.INSERT)
    @PostMapping
    public R<String> add(@RequestBody SysTest sysTest) {
        return toReturn(sysTestService.save(sysTest));
    }

    /**
     * 修改单表管理
     */
    @ApiOperation(value = "修改单表管理")
    @PreAuthorize("@ss.hasPermi('system:test:edit')")
    @Log(title = "单表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    public R<String> edit(@RequestBody SysTest sysTest) {
        return toReturn(sysTestService.updateById(sysTest));
    }


    /**
     * 删除单表管理
     */
    @ApiOperation(value = "删除单表管理")
    @PreAuthorize("@ss.hasPermi('system:test:remove')")
    @Log(title = "单表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R<String> remove(@PathVariable Long[] ids) {
        return toReturn(sysTestService.removeBatchByIds(Arrays.asList(ids)));
    }
}
