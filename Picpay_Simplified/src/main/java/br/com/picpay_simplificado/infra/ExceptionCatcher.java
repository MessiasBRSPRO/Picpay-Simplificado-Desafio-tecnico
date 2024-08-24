package br.com.picpay_simplificado.infra;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionCatcher {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> runtimeException(){
        return ResponseEntity.badRequest().build();
    }
}
