package ge.gov.dga.carbook.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
public class ErrorValidationResponse extends ErrorResponse {
    private List<ValidationErrorDetail> validationErrors;

    public ErrorValidationResponse(LocalDateTime timestamp, String errorMessage, int errorCode, String error, List<ValidationErrorDetail> validationErrors) {
        super(timestamp, errorMessage, errorCode, error);
        this.validationErrors = validationErrors;
    }

}
