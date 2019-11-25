package org.jeecg.common.aspect.annotation;

import java.lang.annotation.*;

/**
 * 忽略会员验证注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreMemberAuth {
}
