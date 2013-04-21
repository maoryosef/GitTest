package restapi;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Target({java.lang.annotation.ElementType.TYPE,java.lang.annotation.ElementType.METHOD})
public @interface AllowedRoles {
	String[] values();
}
