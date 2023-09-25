package com.booking.movie.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieDto {
    @JsonProperty("Name")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must be alphabetical")
    private String name;
}
