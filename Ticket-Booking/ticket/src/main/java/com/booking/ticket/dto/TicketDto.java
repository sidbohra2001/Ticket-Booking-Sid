package com.booking.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TicketDto {
    @JsonProperty("User")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name should be in alphabetical format")
    private String user;
    @JsonProperty("City")
    private int city;
    @JsonProperty("Theatre")
    private int theatre;
    @JsonProperty("Movie")
    private int movie;
}
