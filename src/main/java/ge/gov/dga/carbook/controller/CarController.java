package ge.gov.dga.carbook.controller;

import ge.gov.dga.carbook.model.dto.car.AddCarRequest;
import ge.gov.dga.carbook.model.dto.car.CarResponse;
import ge.gov.dga.carbook.model.dto.car.UpdateCarRequest;
import ge.gov.dga.carbook.service.CarService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
@RequiredArgsConstructor
@Validated
public class CarController {

    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarResponse> addCar(
            @RequestBody @Valid AddCarRequest car,
            Principal connectedUse
    ) {
        CarResponse newCar = carService.addCar(car, connectedUse);
        return ResponseEntity.ok(newCar);
    }

    @PutMapping("/{carId}")
    public ResponseEntity<CarResponse> updateCar(
            @PathVariable @Positive long carId,
            @RequestBody @Valid UpdateCarRequest car,
            Principal connectedUser
    ) {
        CarResponse updatedCar = carService.updateCar(car, carId, connectedUser);
        return ResponseEntity.ok(updatedCar);
    }

    @DeleteMapping("/{carId}")
    public ResponseEntity<String> deleteCar(@PathVariable Long carId, Principal connectedUser) {
        carService.deleteCar(carId, connectedUser);
        return ResponseEntity.ok().body("Car has been deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<List<CarResponse>> getCarByKeyword(@RequestParam String keyword, Principal connectedUser) {
        List<CarResponse> cars = carService.getCarsWithKeyWord(keyword, connectedUser);
        return ResponseEntity.ok(cars);
    }

    @GetMapping("/searchPaged")
    public ResponseEntity<Page<CarResponse>> getCarByKeyword(@RequestParam String keyword, Pageable pageable, Principal connectedUser) {
        Page<CarResponse> cars = carService.getCarsWithKeyWordPaged(keyword, connectedUser, pageable);
        return ResponseEntity.ok(cars);
    }
}