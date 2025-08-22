package com.project.eventmanagement.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PostEventRequestDTO {

    @NotBlank(message = "Event name is required")
    private String name;
    private String description;
    @NotBlank(message = "Location is required")
    private String location;
    @NotNull(message = "Start date/time is required")
    private LocalDateTime startDateTime;
    @NotNull(message = "Start date/time is required")
    private LocalDateTime endDateTime;
    @NotNull(message = "Capacity is required")
    private Integer capacity;
    @NotNull(message = "Organizer Id is required")
    private int organizer;


}
