package ge.gov.dga.carbook.service;

import ge.gov.dga.carbook.entity.Car;
import ge.gov.dga.carbook.exception.AlreadyExistsException;
import ge.gov.dga.carbook.exception.ResourceNotFoundException;
import ge.gov.dga.carbook.model.dto.car.AddCarRequest;
import ge.gov.dga.carbook.model.dto.car.CarResponse;
import ge.gov.dga.carbook.model.dto.car.UpdateCarRequest;
import ge.gov.dga.carbook.repository.CarRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CarService {

    private final CarRepository carRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;

    public CarResponse addCar(AddCarRequest car, Principal connectedUser) {
        var user = userService.getConnectedUser(connectedUser);
        if (carRepository.existsByStateNumber(car.getStateNumber())) {
            throw new AlreadyExistsException("Car already exists");
        }
        Car newCar = modelMapper.map(car, Car.class);
        newCar.setUser(user);
        Car savedCar = carRepository.save(newCar);
        return modelMapper.map(savedCar, CarResponse.class);
    }

    public CarResponse updateCar(UpdateCarRequest car, Long carId, Principal connectedUser) {
        var user = userService.getConnectedUser(connectedUser);
        Car existingCar = carRepository.findByCarIdAndUserId(carId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("car by id " + carId + " not exists by user id " + user.getId()));
        if (carRepository.existsByStateNumber(car.getStateNumber())) {
            throw new AlreadyExistsException("Car with provided state number already exists");
        }
        modelMapper.map(car, existingCar);
        Car updatedCar = carRepository.save(existingCar);
        return modelMapper.map(updatedCar, CarResponse.class);
    }

    public void deleteCar(Long carId, Principal connectedUser) {
        validateCarAndUser(carId, connectedUser);
        carRepository.deleteById(carId);
    }

    @Transactional(readOnly = true)
    public List<CarResponse> getCarsWithKeyWord(String keyword, Principal connectedUser) {
        var user = userService.getConnectedUser(connectedUser);
        return carRepository.findByUserIdAndKeyword(user.getId(), keyword).stream()
                .map(car -> modelMapper.map(car, CarResponse.class))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<CarResponse> getCarsWithKeyWordPaged(String keyword, Principal connectedUser, Pageable pageable) {
        var user = userService.getConnectedUser(connectedUser);
        return carRepository.findByUserIdAndKeyword(user.getId(), keyword, pageable)
                .map(car -> modelMapper.map(car, CarResponse.class));
    }

    public void validateCarAndUser(Long carId, Principal connectedUser) {
        var user = userService.getConnectedUser(connectedUser);
        carRepository.findByCarIdAndUserId(carId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("car by id " + carId + " not exists by user id " + user.getId()));
    }

}