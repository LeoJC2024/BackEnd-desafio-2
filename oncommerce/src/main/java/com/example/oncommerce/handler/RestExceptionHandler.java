package com.example.oncommerce.handler;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.example.oncommerce.models.RespostaError;
import com.example.oncommerce.models.exceptions.ResourceBadRequestException;
import com.example.oncommerce.models.exceptions.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<RespostaError> handleResourceNotFound(ResourceNotFoundException ex){
    
    RespostaError error = new RespostaError(
        LocalDate.now().toString(), 
    HttpStatus.NOT_FOUND.value(),
     "Recurso não encontrado", 
     ex.getMessage()
     );   

    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }

     @ExceptionHandler(ResourceBadRequestException.class)
  public ResponseEntity<RespostaError> handleResourceBadResponseEntity(ResourceBadRequestException ex){
    
    RespostaError error = new RespostaError(
        LocalDate.now().toString(), 
    HttpStatus.BAD_REQUEST.value(),
     "BAD REQUEST - Erro de requisição ", 
     ex.getMessage()
     );   

    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<RespostaError> handleException(Exception ex){
    
    RespostaError error = new RespostaError(
      LocalDate.now().toString(),
      HttpStatus.INTERNAL_SERVER_ERROR.value(),
      "Erro interno do servidor",
      ex.getMessage()
    );

    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
