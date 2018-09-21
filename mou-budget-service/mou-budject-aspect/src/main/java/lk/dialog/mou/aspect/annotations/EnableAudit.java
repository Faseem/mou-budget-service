package lk.dialog.mou.aspect.annotations;

import java.lang.annotation.*;

/**
 * Created by roshane on 1/3/18.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EnableAudit {
    /**
     * A short description of the method functionality which will get printed in audit log
     *
     * @return description of the method being audited
     */
    String description();
}
