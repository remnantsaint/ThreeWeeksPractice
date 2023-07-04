package org.jshand.module.registe.controller;

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
import org.jshand.module.registe.domain.Registlevel;
import org.jshand.module.registe.service.IRegistlevelService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 挂号级别Controller
 *
 * @author 金山老师
 * @date 2023-03-28
 */
@Api(tags = "挂号级别模块")
@RestController
@RequestMapping("/registe/registlevel")
public class RegistlevelController extends BaseController {
    @Autowired
    private IRegistlevelService registlevelService;

    /**
     * 查询挂号级别列表
     */
    @ApiOperation(value = "查询挂号级别列表")
    @PreAuthorize("@ss.hasPermi('registe:registlevel:list')")
    @GetMapping("/list")
    public Page<Registlevel> list(Registlevel entity, Page<Registlevel> page) {

        LambdaQueryWrapper<Registlevel> lambdaQueryWrapper = new LambdaQueryWrapper<Registlevel>();
        return registlevelService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出挂号级别列表
     */
    @ApiOperation(value = "导出挂号级别列表")
    @PreAuthorize("@ss.hasPermi('registe:registlevel:export')")
    @Log(title = "挂号级别", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Registlevel registlevel) {
        LambdaQueryWrapper<Registlevel> queryWrapper = new LambdaQueryWrapper<>();
        List<Registlevel> list = registlevelService.list(queryWrapper);
        ExcelUtil<Registlevel> util = new ExcelUtil<Registlevel>(Registlevel.class);
        util.exportExcel(response, list, "挂号级别数据");
    }


    /**
     * 获取挂号级别详细信息
     */
    @ApiOperation(value = "获取挂号级别详细信息")
    @PreAuthorize("@ss.hasPermi('registe:registlevel:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(registlevelService.getById(id));
    }

    /**
     * 新增挂号级别
     */
    @ApiOperation(value = "新增挂号级别")
    @PreAuthorize("@ss.hasPermi('registe:registlevel:add')")
    @Log(title = "挂号级别", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Registlevel registlevel) {
        return toAjax(registlevelService.save(registlevel));
    }

    /**
     * 修改挂号级别
     */
    @ApiOperation(value = "修改挂号级别")
    @PreAuthorize("@ss.hasPermi('registe:registlevel:edit')")
    @Log(title = "挂号级别", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Registlevel registlevel) {
        return toAjax(registlevelService.updateById(registlevel));
    }


    /**
     * 删除挂号级别
     */
    @ApiOperation(value = "删除挂号级别")
    @PreAuthorize("@ss.hasPermi('registe:registlevel:remove')")
    @Log(title = "挂号级别", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Integer[] ids) {
        return toAjax(registlevelService.removeBatchByIds(Arrays.asList(ids)));
    }
}
