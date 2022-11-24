package com.star.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class MyBatisMetaObjectHandler implements MetaObjectHandler {

    /**
     * 自定义插入时填充规则
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        // 注意是类属性字段名称，不是表字段名称
        this.setFieldValByName("createTime", new Date(), metaObject);
        this.setFieldValByName("updateTime", new Date(), metaObject);
        this.setFieldValByName("views", 0, metaObject);
        this.setFieldValByName("commentCount", 0, metaObject);
        this.setFieldValByName("adminComment", false, metaObject);
        this.setFieldValByName("adminMessage", false, metaObject);
        //this.setFieldValByName("parentCommentId", -1, metaObject);

    }

    /**
     * 自定义更新时填充规则
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        // 注意是类属性字段名称，不是表字段名称
        this.setFieldValByName("updateTime", new Date(), metaObject);
    }

}
