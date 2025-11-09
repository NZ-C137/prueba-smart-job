package smart.job.test.domain.model;

public class ErrorMessageDTO {

    private String mensaje;

    public ErrorMessageDTO() {
    }

    public ErrorMessageDTO(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
