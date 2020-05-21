package com.mylog.common.batch.validators;

import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.InitializingBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class PersonDbValidator<T> implements Validator<T>, InitializingBean {

    private javax.validation.Validator validator;

    /**
     * 进行JSR-303的Validator的初始化
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        validator = validatorFactory.usingContext().getValidator();
    }

    @Override
    public void validate(T t) throws ValidationException {
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(t);
        if (constraintViolations.size() > 0){
            StringBuilder message = new StringBuilder();
            for (ConstraintViolation<T> constraintViolation: constraintViolations){
                message.append(constraintViolation.getMessage() + "\n");
            }

            throw new ValidationException(message.toString());
        }
    }
}
