package com.booking.theatre.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
public class Theatre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("ID")
    private int id;
    @JsonProperty("Name")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Name must be alphanumeric")
    private String name;
    @NotNull
    @JsonProperty("City")
    private int city;
    @JsonProperty("Movies")
    @ElementCollection
    private List<Integer> movies;
}
