package net.luxclient.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface LuxModuleData {
    String name();
    String description();
    Category category();
    String[] aliases() default {};
}
