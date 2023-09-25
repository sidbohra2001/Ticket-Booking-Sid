package com.booking.ticket.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class TheatreDto {
    @JsonProperty("Name")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must be alphanumeric")
    private String name;
    @NotNull
    @JsonProperty("City")
    private int city;
    @JsonProperty("Movies")
    private List<Integer> movies;
}
