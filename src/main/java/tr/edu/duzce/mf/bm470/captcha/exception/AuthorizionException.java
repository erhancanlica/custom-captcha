package tr.edu.duzce.mf.bm470.captcha.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
class AuthorizationException extends RuntimeException{

    public AuthorizationException() {
        super("Bu sayfayı görüntülemeye Yetkiniz yok");
    }

}
