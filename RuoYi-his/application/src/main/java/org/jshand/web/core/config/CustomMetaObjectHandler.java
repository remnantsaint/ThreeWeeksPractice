package org.jshand.web.core.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.jshand.support.common.utils.SecurityUtils;
import org.springframework.context.annotation.Configuration;

import java.util.Date;

@Configuration
public class CustomMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("createBy", SecurityUtils.getLoginUser().getUsername(), metaObject);
        this.setFieldValByName("createTime", new Date(), metaObject);

        //当执行插入的操作时即使 实体类中updateBy、updateTime两个字段有值，也需要把他们清空，防止前端传此参数
//        this.setFieldValByName("updateBy", "", metaObject);
//        this.setFieldValByName("updateTime", "", metaObject);//日期不能传null，否则底层不会覆盖

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        //执行修改的操作时不需要更新 createBy、createTime字段，防止前端恶意传递此数据，导致创建数据的用户信息被修改。
//        this.setFieldValByName("createBy", "", metaObject);
//        this.setFieldValByName("createTime", "", metaObject);//日期不能传null，否则底层不会覆盖

        this.setFieldValByName("updateBy", SecurityUtils.getLoginUser().getUsername(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}