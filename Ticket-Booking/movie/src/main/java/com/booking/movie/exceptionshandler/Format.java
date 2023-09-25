package com.booking.movie.exceptionshandler;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Format {
    private String status;
    private String message;
}
