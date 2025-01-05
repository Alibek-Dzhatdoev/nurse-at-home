package com.nurseathome.bid.aspect;

import com.nurseathome.bid.model.enums.Role;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Retention(RUNTIME)
@Target({METHOD, TYPE})
public @interface CheckPermission {

    Role[] roles();
}
