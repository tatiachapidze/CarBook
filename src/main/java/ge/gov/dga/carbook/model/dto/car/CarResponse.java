package ge.gov.dga.carbook.model.dto.car;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CarResponse {
    private Long carId;
    private String stateNumber;
    private String vinCode;
    private String make;
    private String model;
    private String color;
    private Date createdOn;
    private Date updatedOn;
}
