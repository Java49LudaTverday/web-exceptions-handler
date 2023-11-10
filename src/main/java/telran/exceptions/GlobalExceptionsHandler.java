package telran.exceptions;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
//import telran.spring.controller.GreetingsController;

@ControllerAdvice
@Slf4j
public class GlobalExceptionsHandler {
	@ExceptionHandler({IllegalStateException.class})
	ResponseEntity<String> badRequest(IllegalStateException e){		
		String message = e.getMessage();
		log.error("Bad request: {}", message);
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler({NotFoundException.class})
	ResponseEntity<String> notFound(NotFoundException e){
		String message = e.getMessage();
		log.error("Not found : {}", message);
		return new ResponseEntity<String>(message, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	ResponseEntity<String> notValid(MethodArgumentNotValidException e){
		FieldError fe = e.getFieldError();
		String message = e.getFieldError().getDefaultMessage();
//		String message = String.format("%s is not valid : %s", fe.getField(),fe.getDefaultMessage() );
		log.error("NotValidException {}", String.format("%s : %s", fe.getField(),fe.getDefaultMessage()));
		return new ResponseEntity<String>(message, HttpStatus.BAD_REQUEST);
	}

}
