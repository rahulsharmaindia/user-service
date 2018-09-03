package com.referminds.userservice.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.referminds.userservice.enums.MessageType;
import com.referminds.userservice.model.ErrorDTO;
import com.referminds.userservice.model.ExceptionResponse;
import com.referminds.userservice.model.FieldErrorDTO;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<ExceptionResponse> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		/*ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), ex.getBindingResult().getErrorCount()+"Validation Failed",
				ex.getBindingResult().getFieldError().getDefaultMessage());*/
		return new ResponseEntity<>(processFieldErrors(ex.getBindingResult().getFieldErrors()), HttpStatus.BAD_REQUEST);
	}	
	private ErrorDTO processFieldErrors(List<FieldError> fieldErrors) {
		ErrorDTO dto = new ErrorDTO();
		List<FieldErrorDTO> errorDTOs = new ArrayList<FieldErrorDTO>();
		for (FieldError fieldError : fieldErrors) {
			//String localizedErrorMessage = resolveLocalizedErrorMessage(fieldError);
			FieldErrorDTO error = new FieldErrorDTO();
			error.setField(fieldError.getField());
			error.setMessage(fieldError.getDefaultMessage());
			error.setMessageType(MessageType.ERROR);
			errorDTOs.add(error);
		}
		dto.setErrorDTOs(errorDTOs);
		return dto;
	}

	/*private String resolveLocalizedErrorMessage(FieldError fieldError) {
		Locale currentLocale = LocaleContextHolder.getLocale();
		String localizedErrorMessage = messageSource.getMessage(fieldError.getDefaultMessage(), null, currentLocale);
		if (localizedErrorMessage.equals(fieldError.getDefaultMessage())) {
			String[] fieldErrorCodes = fieldError.getCodes();
			localizedErrorMessage = fieldErrorCodes[0];
		}
		return localizedErrorMessage;
	}*/
}
