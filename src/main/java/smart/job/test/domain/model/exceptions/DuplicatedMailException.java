package smart.job.test.domain.model.exceptions;

import smart.job.test.infrastructure.model.Constants;

public class DuplicatedMailException extends RuntimeException {

    private final String mensaje;

    public DuplicatedMailException(String mensaje) {
        super();
        this.mensaje = Constants.DUPLICATED_MAIL_ERROR;
    }

    public String getMensaje() {
        return mensaje;
    }

}
