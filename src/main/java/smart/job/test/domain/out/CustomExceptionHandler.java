package smart.job.test.domain.out;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import smart.job.test.domain.model.ErrorMessageDTO;
import smart.job.test.domain.model.exceptions.DefaultException;
import smart.job.test.domain.model.exceptions.PwdNotMatchesException;
import smart.job.test.infrastructure.model.Constants;

import java.util.regex.PatternSyntaxException;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
        HttpStatusCode status, WebRequest request) {

        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
        HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getMessage());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest webRequest) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(Constants.DUPLICATED_MAIL_ERROR);

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler({PwdNotMatchesException.class})
    public ResponseEntity<Object> handlePwdNotMatchesException(PwdNotMatchesException ex, WebRequest webRequest) {
        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getMensaje());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolationException(final ConstraintViolationException ex, final WebRequest request) {

        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getMessage());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({PatternSyntaxException.class})
    public ResponseEntity<Object> handlePatternSyntaxException(PatternSyntaxException ex, WebRequest request) {

        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getMessage());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ValidationException.class})
    public ResponseEntity<Object> handleValidationException(ValidationException ex, WebRequest request) {

        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getMessage());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({DefaultException.class})
    public ResponseEntity<Object> handleDefaultException(DefaultException ex, WebRequest request) {

        ErrorMessageDTO errorMessageDTO = new ErrorMessageDTO();
        errorMessageDTO.setMensaje(ex.getMessage());

        return handleExceptionInternal(ex, errorMessageDTO, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

}
