package com.hui.miaosha.Validator;

import com.hui.miaosha.utils.ValidatorUtil;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Author: CarlChen
 * @Despriction:TODO
 * @Date: Create in 22:26 2019\4\13 0013
 */
public class IsMobileValidate implements ConstraintValidator<IsMobile,String> {

    private boolean require = false;

    @Override
    public void initialize(IsMobile constraintAnnotation) {
        require = constraintAnnotation.require();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
       if (require) {
           return ValidatorUtil.isMobile(value);
       } else {
           if (StringUtils.isEmpty(value)){
               return false;
           } else {
               return ValidatorUtil.isMobile(value);
           }
       }
    }
}
