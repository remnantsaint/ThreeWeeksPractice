package org.jshand.web.controller.tool;

import io.swagger.annotations.*;
import org.jshand.support.common.core.controller.BaseController;
import org.jshand.support.common.core.domain.R;
import org.jshand.support.common.utils.StringUtils;
import org.jshand.web.domain.User;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * swagger 用户测试方法
 *
 * @author ruoyi
 */
@Api(tags = "SwaggerDemo管理")
@RestController
@RequestMapping("/test/user")
public class KnifeController extends BaseController {
    private final static Map<Integer, User> users = new LinkedHashMap<Integer, User>();

    {
        users.put(1, new User(1, "admin"));
        users.put(2, new User(2, "ry"));
    }

    @ApiOperation("获取用户列表")
    @GetMapping("/list")
    public R<List<User>> userList() {
        List<User> userList = new ArrayList<User>(users.values());
        return R.ok(userList);
    }

    @ApiOperation("获取用户详细")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @GetMapping("/{userId}")
    public R<User> getUser(@PathVariable Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            return R.ok(users.get(userId));
        } else {
            return R.fail("用户不存在");
        }
    }

    @ApiOperation("新增用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = "Integer", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "username", value = "用户名称", dataType = "String", dataTypeClass = String.class)
    })
    @PostMapping("/save")
    public R<String> save(User user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return R.fail("用户ID不能为空");
        }
        users.put(user.getUserId(), user);
        return R.ok();
    }

    @ApiOperation("更新用户")
    @PutMapping("/update")
    public R<String> update(@RequestBody User user) {
        if (StringUtils.isNull(user) || StringUtils.isNull(user.getUserId())) {
            return R.fail("用户ID不能为空");
        }
        if (users.isEmpty() || !users.containsKey(user.getUserId())) {
            return R.fail("用户不存在");
        }
        users.remove(user.getUserId());
        users.put(user.getUserId(), user);
        return R.ok();
    }

    @ApiOperation("删除用户信息")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "int", paramType = "path", dataTypeClass = Integer.class)
    @DeleteMapping("/{userId}")
    public R<String> delete(@PathVariable Integer userId) {
        if (!users.isEmpty() && users.containsKey(userId)) {
            users.remove(userId);
            return R.ok();
        } else {
            return R.fail("用户不存在");
        }
    }
}

//@ApiModel(value = "User", description = "用户实体")
//class User {
//    @ApiModelProperty("用户ID")
//    private Integer userId;
//
//    @ApiModelProperty("用户名称")
//    private String username;
//
//    @ApiModelProperty("用户密码")
//    private String password;
//
//    @ApiModelProperty("用户手机")
//    private String mobile;
//
//    public User() {
//
//    }
//
//    public User(Integer userId, String username, String password, String mobile) {
//        this.userId = userId;
//        this.username = username;
//        this.password = password;
//        this.mobile = mobile;
//    }
//
//    public Integer getUserId() {
//        return userId;
//    }
//
//    public void setUserId(Integer userId) {
//        this.userId = userId;
//    }
//
//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getMobile() {
//        return mobile;
//    }
//
//    public void setMobile(String mobile) {
//        this.mobile = mobile;
//    }
//}
