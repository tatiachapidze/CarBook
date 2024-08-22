package ge.gov.dga.carbook.model.dto.serviceRecord;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceRecordRequest {
    @NotBlank
    private String serviceName;

    @NotNull
    private LocalDate serviceDate;

    @NotNull
    private Double price;
}
