package com.booking.theatre.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class MovieDto {
    private String id;
    private String name;
}
