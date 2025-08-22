package com.project.eventmanagement.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalTime;
import java.util.Date;


public class ErrorResponse {

    private int status;
    private String message;
    private Date timeStamp;

    public ErrorResponse(int httpStatus, String message, Date date) {
        this.status = httpStatus;
        this.message = message;
        this.timeStamp = date;
    }

    public void setStatus(int statusCode) {
        this.status = statusCode;
    }

    public int getStatus() {
        return this.status;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}
