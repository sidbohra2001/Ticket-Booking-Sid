package com.booking.city.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CityDto {
    @JsonProperty("Name")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must be alphabetical")
    private String name;
}
