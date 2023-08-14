package cn.nukkit.api;

import java.lang.annotation.*;

@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR, ElementType.METHOD, ElementType.ANNOTATION_TYPE, ElementType.TYPE,
        ElementType.FIELD, ElementType.PACKAGE})
@NukkitMVOnly
@Since("1.0.0-NMV")
@Inherited
@Documented
public @interface NukkitMVOnly {
    @NukkitMVOnly
    String value() default "";
}