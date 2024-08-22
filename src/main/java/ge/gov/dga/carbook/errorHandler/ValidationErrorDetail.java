package ge.gov.dga.carbook.errorHandler;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidationErrorDetail {
    private String field;
    private String message;
}
