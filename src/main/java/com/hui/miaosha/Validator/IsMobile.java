package com.hui.miaosha.Validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author: CarlChen
 * @Despriction: 自定义手机号验证注解
 * @Date: Create in 22:22 2019\4\13 0013
 */
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = {IsMobileValidate.class })
public @interface IsMobile {

    boolean require() default true;

    String message() default "手机号格式错误";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
