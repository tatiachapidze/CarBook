package ge.gov.dga.carbook.model.dto.car;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddCarRequest {
    @NotBlank
    private String stateNumber;
    @NotBlank
    @Size(min = 17, max = 17)
    private String vinCode;
    @NotBlank
    private String make;
    @NotBlank
    private String model;
    @NotBlank
    private String color;
}