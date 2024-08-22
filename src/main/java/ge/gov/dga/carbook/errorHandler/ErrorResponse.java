package ge.gov.dga.carbook.errorHandler;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorResponse {
    private LocalDateTime timestamp;
    private String errorMessage;
    private int errorCode;
    private String error;
}