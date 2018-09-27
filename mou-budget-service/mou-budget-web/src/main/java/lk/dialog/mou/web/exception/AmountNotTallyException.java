package lk.dialog.mou.web.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Created by Aux072 on 26/09/2018.
 */
@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class AmountNotTallyException extends ApplicationException {
    public AmountNotTallyException(String message){
        super(message);
    }
}
