/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.danyLasso.solutions.trains.exceptions;

import java.time.LocalDateTime;
import lombok.Getter;

/**
 *
 * @author dany_lasso
 */
@Getter
public class NotFoundException extends RuntimeException{
    
    private String code;
    private LocalDateTime timestamp; 

    public NotFoundException(String message) {
        super(message);
        this.timestamp = LocalDateTime.now();
    }

    public NotFoundException(String code, String message) {
        super(message);
        this.code = code;
        this.timestamp = LocalDateTime.now();
    }

    public NotFoundException(String code, LocalDateTime timestamp, String message) {
        super(message);
        this.code = code;
        this.timestamp = timestamp;
    }
    
}
