package org.jshand.module.registe.controller;

import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.jshand.common.HisConstants;
import org.jshand.module.registe.domain.ApplyItem;
import org.jshand.module.registe.domain.Registlevel;
import org.jshand.module.registe.service.IRegistlevelService;
import org.jshand.module.registe.vo.RegisterVo;
import org.jshand.module.system.service.ISysDeptService;
import org.jshand.module.system.service.ISysUserService;
import org.jshand.support.common.core.domain.Ret;
import org.jshand.support.common.core.domain.entity.SysDept;
import org.jshand.support.common.core.domain.entity.SysRole;
import org.jshand.support.common.core.domain.entity.SysUser;
import org.jshand.support.common.utils.StringUtils;
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
import org.jshand.module.registe.domain.Register;
import org.jshand.module.registe.service.IRegisterService;
import org.jshand.support.common.utils.poi.ExcelUtil;

/**
 * 诊疗信息Controller
 *
 * @author 金山老师
 * @date 2023-03-29
 */
@Api(tags = "诊疗信息模块")
@RestController
@RequestMapping("/registe/register")
public class RegisterController extends BaseController {
    @Autowired
    private IRegisterService registerService;


    @Autowired
    private ISysDeptService deptService;

    @Autowired
    private IRegistlevelService registlevelService;


    @Autowired
    private ISysUserService sysUserService;

    /**
     * 查询诊疗信息列表
     */
    @ApiOperation(value = "查询诊疗信息列表")
    @PreAuthorize("@ss.hasPermi('registe:register:list')")
    @GetMapping("/list")
    public Page<Register> list(Register entity, Page<Register> page) {

        LambdaQueryWrapper<Register> lambdaQueryWrapper = new LambdaQueryWrapper<Register>();
        return registerService.page(page, lambdaQueryWrapper);
    }

    /**
     * 导出诊疗信息列表
     */
    @ApiOperation(value = "导出诊疗信息列表")
    @PreAuthorize("@ss.hasPermi('registe:register:export')")
    @Log(title = "诊疗信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Register register) {
        LambdaQueryWrapper<Register> queryWrapper = new LambdaQueryWrapper<>();
        List<Register> list = registerService.list(queryWrapper);
        ExcelUtil<Register> util = new ExcelUtil<Register>(Register.class);
        util.exportExcel(response, list, "诊疗信息数据");
    }


    /**
     * 获取诊疗信息详细信息
     */
    @ApiOperation(value = "获取诊疗信息详细信息")
    @PreAuthorize("@ss.hasPermi('registe:register:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(registerService.getById(id));
    }

    /**
     * 新增诊疗信息
     */
    @ApiOperation(value = "挂号")
    @PreAuthorize("@ss.hasPermi('registe:register:add')")
    @Log(title = "诊疗信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Register register) {

        //默认是待诊的状态
        register.setStatus(HisConstants.REGIST_STATUS_1);

        return toAjax(registerService.save(register));
    }

    /**
     * 修改诊疗信息
     */
    @ApiOperation(value = "修改诊疗信息")
    @PreAuthorize("@ss.hasPermi('registe:register:edit')")
    @Log(title = "诊疗信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Register register) {
        return toAjax(registerService.updateById(register));
    }


    /**
     * 删除诊疗信息
     */
    @ApiOperation(value = "删除诊疗信息")
    @PreAuthorize("@ss.hasPermi('registe:register:remove')")
    @Log(title = "诊疗信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(registerService.removeBatchByIds(Arrays.asList(ids)));
    }


    /**
     * 在挂号页面 显示 科室列表、挂号级别的下拉选
     *
     * @return
     */
//    @PreAuthorize("@ss.hasPermi('registe:register:getInfo')")
    @GetMapping(value = {"/getInfo"})
    public AjaxResult getInfo() {

        AjaxResult ajax = new AjaxResult();

        List<SysDept> sysDeptLists = deptService.selectAll();
        List<Registlevel> list = registlevelService.list();

        ajax.put("depts", sysDeptLists);
        ajax.put("registLevels", list);

        return ajax;
    }


    @ApiOperation(value = "根据部门查询可以挂号的医生")
//    @PreAuthorize("@ss.hasPermi('registe:register:getDoctor')")
    @GetMapping(value = {"/queryDoctor"})
    public Ret<List<SysUser>> queryDoctorByDeptAndRegistLevel(Long deptId, Integer registLevel) {

        List<SysUser> sysUserList = sysUserService.selectDoctorByDeptAndRegistLevel(deptId, registLevel);

        return Ret.success(sysUserList);
    }





    //根据患者查询已申请的检查、检验。。。。

    @ApiOperation(value = "根据患者查询已申请的检、检验")
//    @PreAuthorize("@ss.hasPermi('registe:register:queryItemByRegisterId')")
    @GetMapping(value = {"/queryItemByRegisterId/{registerId}"})
    public Ret<List<ApplyItem>> queryItemByRegisterId(@PathVariable Long registerId) {

        List<ApplyItem> applyItemList = registerService.selectItemByRegisterId(registerId);

        return Ret.success(applyItemList);
    }



    @ApiOperation(value = "收费")
    //    @PreAuthorize("@ss.hasPermi('registe:register:fee')")
    @PostMapping("/fee")
    public Ret<Boolean> fee(@RequestBody List<RegisterVo> registVoList){


        boolean success = registerService.fee(registVoList);
        return Ret.success(success );
    }

    @ApiOperation(value = "退费")
    //    @PreAuthorize("@ss.hasPermi('registe:register:refund')")
    @PostMapping("/refund")
    public Ret<Boolean> refund(@RequestBody List<RegisterVo> registVoList){


        boolean success = registerService.refund(registVoList);
        return Ret.success(success );
    }





    @ApiOperation(value = "退费")
    //    @PreAuthorize("@ss.hasPermi('registe:register:refund')")
    @GetMapping("/revert/{id}")
    public Ret<Boolean> revert(@PathVariable  long id){


        boolean success = registerService.revert(id);
        return Ret.success(success );
    }




}
