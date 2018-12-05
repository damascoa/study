/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.metre.services.exceptions;

/**
 *
 * @author Renato
 */
public class DataIntegretyException extends RuntimeException {

    public DataIntegretyException(String msg) {
        super(msg);
    }

    public DataIntegretyException(String message, Throwable cause) {
        super(message, cause);
    }

}
