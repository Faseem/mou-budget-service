package lk.dialog.mou.aspect.annotations;

import java.lang.annotation.*;

/**
 * Created by roshane on 1/3/18.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EnableAuth {
    /**
     * A short description of the method functionality which will get printed in audit log
     *
     * @return description of the method being audited
     */
    String[] paramNames();
}
