package com.project.eventmanagement.dto;

import com.project.eventmanagement.model.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {

    private String username;
    @Email
    private String email;
    private List<Event> organizedEvents;

}
