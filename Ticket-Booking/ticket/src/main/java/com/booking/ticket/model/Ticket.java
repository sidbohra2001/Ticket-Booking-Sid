package com.booking.ticket.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int id;
    @JsonProperty("User")
    @Pattern(regexp = "^[A-Za-z]+$", message = "Name should be in alphabetical format")
    private String user;
    @JsonProperty("City")
    private int city;
    @JsonProperty("Theatre")
    private int theatre;
    @JsonProperty("Movie")
    private int movie;
    @JsonProperty("Seat")
    private int seat;
    @JsonProperty("Date")
    private LocalDate date;
}
