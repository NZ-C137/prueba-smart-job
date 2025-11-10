package smart.job.test.domain.out;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.MethodParameter;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;
import smart.job.test.domain.model.ErrorMessageDTO;
import smart.job.test.domain.model.exceptions.DefaultException;
import smart.job.test.domain.model.exceptions.PwdNotMatchesException;
import smart.job.test.infrastructure.model.Constants;

import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CustomExceptionHandlerTest {

    private CustomExceptionHandler customExceptionHandler;

    @Mock
    private WebRequest webRequest;

    @Mock
    private BindingResult bindingResult;

    @Before
    public void setUp() {
        customExceptionHandler = new CustomExceptionHandler();
    }

    @Test
    public void handleMethodArgumentNotValid_shouldReturnBadRequest() {
        String errorMessage = "El email no puede ser nulo";
        ObjectError error = new ObjectError("userDTO", errorMessage);

        when(bindingResult.getAllErrors()).thenReturn(Collections.singletonList(error));

        MethodArgumentNotValidException ex = new MethodArgumentNotValidException((MethodParameter) null, bindingResult);

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleMethodArgumentNotValid(
                ex, null, HttpStatus.BAD_REQUEST, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorMessageDTO responseBody = (ErrorMessageDTO) responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(errorMessage, responseBody.getMensaje());
    }

    @Test
    public void handleDataIntegrityViolationException_shouldReturnBadRequest() {
        DataIntegrityViolationException ex = new DataIntegrityViolationException("Error de base de datos");

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleDataIntegrityViolationException(ex, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorMessageDTO responseBody = (ErrorMessageDTO) responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(Constants.DUPLICATED_MAIL_ERROR, responseBody.getMensaje());
    }

    @Test
    public void handlePwdNotMatchesException_shouldReturnBadRequest() {

        String errorMessage = "La contraseña no cumple los requisitos";
        PwdNotMatchesException ex = new PwdNotMatchesException(errorMessage);

        ResponseEntity<Object> responseEntity = customExceptionHandler.handlePwdNotMatchesException(ex, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorMessageDTO responseBody = (ErrorMessageDTO) responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(errorMessage, responseBody.getMensaje());
    }

    @Test
    public void handleDefaultException_shouldReturnBadRequest() {

        String errorMessage = "Ocurrió un error genérico";
        DefaultException ex = new DefaultException(errorMessage);

        ResponseEntity<Object> responseEntity = customExceptionHandler.handleDefaultException(ex, webRequest);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());

        ErrorMessageDTO responseBody = (ErrorMessageDTO) responseEntity.getBody();
        assertNotNull(responseBody);
        assertEquals(errorMessage, responseBody.getMensaje());
    }
}