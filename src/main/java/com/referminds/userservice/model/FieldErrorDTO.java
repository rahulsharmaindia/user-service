package com.referminds.userservice.model;

import com.referminds.userservice.enums.MessageType;

import lombok.Data;

@Data
public class FieldErrorDTO {
	 
    private String field;
 
    private String message;
    
    private MessageType messageType;

}
