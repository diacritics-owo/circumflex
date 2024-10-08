package diacritics.owo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Repeatable;
import net.minecraft.util.Identifier;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@Repeatable(Groups.class)
public @interface Group {
  public String value();

  public String namespace() default Identifier.DEFAULT_NAMESPACE;
}
