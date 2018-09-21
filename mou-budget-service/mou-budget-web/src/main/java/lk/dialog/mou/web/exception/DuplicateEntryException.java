package lk.dialog.mou.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Aux072 on 20/09/2018.
 */
@ResponseStatus(value = HttpStatus.NOT_ACCEPTABLE)
public class DuplicateEntryException extends ApplicationException {
    public DuplicateEntryException(String message) {
        super(message);
    }
}
