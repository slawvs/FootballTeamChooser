package pl.slawek.constraint;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import pl.slawek.validator.UniqueNickNameValidator;

@Documented
@Constraint(validatedBy = { UniqueNickNameValidator.class })
@Target({ METHOD, FIELD, PARAMETER, ANNOTATION_TYPE })
@Retention(RUNTIME)
public @interface UniqueNickName {
    String message() default "{pl.slawek.constraint.UniqueNickName.message}";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
