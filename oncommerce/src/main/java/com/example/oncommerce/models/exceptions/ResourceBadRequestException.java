package com.example.oncommerce.models.exceptions;

public class ResourceBadRequestException extends RuntimeException{
    
   private static final long serialVersionUID = 1L;
    
    public ResourceBadRequestException(String message) {
    super(message);
    }
    
    public ResourceBadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

}