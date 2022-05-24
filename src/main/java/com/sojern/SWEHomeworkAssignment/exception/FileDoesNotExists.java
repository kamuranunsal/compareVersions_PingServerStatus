package com.sojern.SWEHomeworkAssignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.SERVICE_UNAVAILABLE , reason = "/tmp/ok directory is not exists")
public class FileDoesNotExists extends Exception{
    public FileDoesNotExists(String message){
        super(message);
    }
}
