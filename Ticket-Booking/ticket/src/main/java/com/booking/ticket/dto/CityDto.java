package com.booking.ticket.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CityDto {
    private String id;
    private String name;
}
