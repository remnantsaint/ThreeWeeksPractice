package org.jshand.web.core.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.RequestParameterBuilder;
import springfox.documentation.schema.ScalarType;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ParameterType;
import springfox.documentation.service.RequestParameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class Knife4jConfig {
    @Bean
    public Docket dockerCommon() {
        return createDocket("org.jshand.web.controller.common", "通用管理");
    }

    @Bean
    public Docket dockeTool() {
        return createDocket("org.jshand.web.controller.tool", "工具管理");
    }

    @Bean
    public Docket dockerBase() {
        return createDocket("org.jshand.web.controller.monitor", "监控管理");
    }

    @Bean
    public Docket dockerJob() {
        return createDocket("org.jshand.quartz.controller", "定时任务管理");
    }

    @Bean
    public Docket dockerUms() {
        return createDocket("org.jshand.web.controller.system", "系统管理1");
    }



    @Bean
    public Docket dockerSystem() {
        return createDocket("org.jshand.module.system.controller", "系统管理2");
    }

    @Bean
    public Docket dockerRegist() {
        return createDocket("org.jshand.module.registe.controller", "诊疗信息");
    }

    @Bean
    public Docket dockerCommonData() {
        return createDocket("org.jshand.module.commondata.controller", "基础数据维护");
    }

    @Bean
    public Docket dockerDoctor() {
        return createDocket("org.jshand.module.doctor.controller", "门诊医生业务");
    }

    /**
     * 用于创建不同组的dockert
     *
     * @param basePackage
     * @return
     */
    public Docket createDocket(String basePackage, String groupName) {


        ApiSelectorBuilder builder = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("后台服务接口API文档")
                        //描述字段支持Markdown语法
                        .description("#的后端管理服务端接口")
                        .termsOfServiceUrl("")
                        .contact(new Contact("张金山", "https://jshand.gitee.io/#/", "zhangjinshan21f@163.com"))
                        .version("1.0")
                        .build())//分组名称
                .groupName(groupName)
                .select()
                //这里指定Controller扫描包路径
                .apis(RequestHandlerSelectors.basePackage(basePackage))
                .paths(PathSelectors.any());

        return builder.build().globalRequestParameters(getGlobalRequestParameters());

    }

    //生成全局通用请求头
    private List<RequestParameter> getGlobalRequestParameters() {
        List<RequestParameter> parameters = new ArrayList<>();
        parameters.add(new RequestParameterBuilder()
                .name("Authorization")
                .description("token令牌")
                .required(false)
                .in(ParameterType.HEADER)
                .query(q -> q.model(m -> m.scalarModel(ScalarType.STRING)))
                .parameterIndex(0)
                .build());
        return parameters;
    }
}
