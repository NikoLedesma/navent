package com.exercise.navent.com.exercise.navent.exception;


//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class PedidoNotFoundException extends RuntimeException{
    public PedidoNotFoundException(String exception){
        super(exception);
    }
}
