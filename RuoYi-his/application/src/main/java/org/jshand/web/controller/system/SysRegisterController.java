package org.jshand.web.controller.system;

import io.swagger.annotations.Api;
import org.jshand.support.common.core.controller.BaseController;
import org.jshand.support.common.core.domain.AjaxResult;
import org.jshand.support.common.core.domain.entity.SysRole;
import org.jshand.support.common.core.domain.entity.SysUser;
import org.jshand.support.common.core.domain.model.RegisterBody;
import org.jshand.support.common.utils.StringUtils;
import org.jshand.support.framework.web.service.SysRegisterService;
import org.jshand.module.system.service.ISysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 注册验证
 *
 * @author ruoyi
 */
@RestController
@Api(tags = "用户注册验证")
public class SysRegisterController extends BaseController {
    @Autowired
    private SysRegisterService registerService;

    @Autowired
    private ISysConfigService configService;

    @PostMapping("/register")
    public AjaxResult register(@RequestBody RegisterBody user) {
        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser")))) {
            return error("当前系统没有开启注册功能！");
        }
        String msg = registerService.register(user);
        return StringUtils.isEmpty(msg) ? success() : error(msg);
    }






}
