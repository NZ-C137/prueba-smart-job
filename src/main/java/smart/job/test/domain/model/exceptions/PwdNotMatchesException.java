package smart.job.test.domain.model.exceptions;

public class PwdNotMatchesException extends Exception {

    private final String mensaje;

    public PwdNotMatchesException(String mensaje) {
        super();
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

}
