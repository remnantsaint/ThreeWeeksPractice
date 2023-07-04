package org.jshand.web.controller.monitor;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.jshand.support.common.core.domain.Ret;
import org.jshand.support.framework.web.domain.Server;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 服务器监
 *
 * @author ruoyi
 */

@RestController
@RequestMapping("/monitor/server")
@Api(tags = "监控管理")
public class ServerController {

    public static final String ABC = "aafdasdf";

    /**
     * @return
     */
    public static int abc() {
        int abc = 100;
        return 100;
    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {
        abc();
    }

    /**
     * @return 返回结果
     * @throws Exception
     */
    @ApiOperation(value = "获取系统信息")
    @PreAuthorize("@ss.hasPermi('monitor:server:list')")
    @GetMapping()
    public Ret<Server> getInfo() throws Exception {
        Server server = new Server();
        server.copyTo();

        int abc = 100;

        return Ret.success(server);
    }
}
