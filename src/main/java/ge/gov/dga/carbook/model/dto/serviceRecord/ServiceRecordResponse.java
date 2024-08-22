package ge.gov.dga.carbook.model.dto.serviceRecord;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ServiceRecordResponse {
    private Long id;
    private String serviceName;
    private LocalDate serviceDate;
    private Double price;
    private Long carId;
}
