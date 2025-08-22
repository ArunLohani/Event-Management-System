package com.project.eventmanagement.dto;

public class DeleteResponse {

    private String message;
    private EventDTO deletedEvent;

    public DeleteResponse(String message, EventDTO deletedEvent){
        this.message = message;
        this.deletedEvent = deletedEvent;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setDeletedEvent(EventDTO deletedEvent){
        this.deletedEvent = deletedEvent;
    }

    public String getMessage(){
        return this.message;
    }

    public EventDTO getDeletedEvent(){
        return this.deletedEvent;
    }


}
