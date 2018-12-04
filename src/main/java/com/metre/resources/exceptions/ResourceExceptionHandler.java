/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.resources.exceptions;

import com.metre.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

/**
 *
 * @author Renato
 */
@ControllerAdvice
public class ResourceExceptionHandler {

    public ResponseEntity<StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest requerst) {
        StandarError err = new StandarError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
