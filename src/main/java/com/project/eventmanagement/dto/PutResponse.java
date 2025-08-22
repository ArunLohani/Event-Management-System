package com.project.eventmanagement.dto;

public class PutResponse {

    private String message;
    private EventDTO event;

    public PutResponse(String message, EventDTO event){
        this.message = message;
        this.event = event;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setEvent(EventDTO event){
        this.event = event;
    }

    public String getMessage(){
        return this.message;
    }

    public EventDTO getEvent(){
        return this.event;
    }


}
