package cn.nukkit.api;

import java.lang.annotation.*;

@NukkitMVOnly
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE,
        ElementType.FIELD, ElementType.PACKAGE})
@Documented
public @interface Internal {

    @NukkitMVOnly
    String value() default "";

}
