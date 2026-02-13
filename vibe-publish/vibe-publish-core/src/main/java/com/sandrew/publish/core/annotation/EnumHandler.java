package com.sandrew.publish.core.annotation;


import com.sandrew.publish.core.enums.BaseEnum;

import java.lang.annotation.*;

/**
 * @Author summer
 * @Description 通过枚举自动转换数据字典中code与desc注解
 * @Date 15:19 2023/3/10
 * @Param
 * @return
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EnumHandler
{
    Class<? extends BaseEnum> value();
}
