package com.booking.ticket.exceptionshandler;

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
