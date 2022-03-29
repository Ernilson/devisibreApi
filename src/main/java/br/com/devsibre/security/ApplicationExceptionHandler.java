package br.com.devsibre.security;



import java.time.Instant;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{

	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<DefaultError> handleAllException(Exception ex, WebRequest request){
		DefaultError err = new DefaultError();
		err.setMessage(ex.getMessage());
		err.setError("Erro ao processar sua requisição");
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_GATEWAY.value());
		err.setPath(((HttpServletRequest) request).getRequestURI());
		
		return new ResponseEntity<>(err, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	public final ResponseEntity<DefaultError> handlerBadRequestException(Exception e, HttpServletRequest request) {
		
		DefaultError err = new DefaultError();
		err.setMessage(e.getMessage());
		err.setError("Erro ao processar sua requisição");
		err.setTimestamp(Instant.now());
		err.setStatus(HttpStatus.BAD_GATEWAY.value());
		err.setPath(request.getRequestURI());

		return new ResponseEntity<DefaultError>(err, HttpStatus.BAD_GATEWAY);
	}
}
