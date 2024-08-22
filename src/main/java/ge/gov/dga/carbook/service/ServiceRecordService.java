package ge.gov.dga.carbook.service;

import ge.gov.dga.carbook.entity.Car;
import ge.gov.dga.carbook.entity.ServiceRecord;
import ge.gov.dga.carbook.exception.ResourceNotFoundException;
import ge.gov.dga.carbook.model.dto.serviceRecord.ServiceRecordRequest;
import ge.gov.dga.carbook.model.dto.serviceRecord.ServiceRecordResponse;
import ge.gov.dga.carbook.repository.CarRepository;
import ge.gov.dga.carbook.repository.ServiceRecordRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class ServiceRecordService {

    private final CarRepository carRepository;
    private final ServiceRecordRepository serviceRecordRepository;
    private final UserService userService;
    private final CarService carService;
    private final ModelMapper modelMapper;

    public ServiceRecordResponse addServiceRecord(Long carId, ServiceRecordRequest serviceRecord, Principal connectedUser) {
        var user = userService.getConnectedUser(connectedUser);
        Car car = carRepository.findByCarIdAndUserId(carId, user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("car with id " + carId + " not exists by user id " + user.getId()));
        ServiceRecord newRecord = modelMapper.map(serviceRecord, ServiceRecord.class);
        newRecord.setCar(car);
        ServiceRecord savedRecord = serviceRecordRepository.save(newRecord);
        return modelMapper.map(savedRecord, ServiceRecordResponse.class);
    }

    public void deleteServiceRecord(Long serviceRecordId, Principal connectedUser) {
        checkServiceAndUser(serviceRecordId, connectedUser);
        serviceRecordRepository.deleteById(serviceRecordId);
    }

    public ServiceRecordResponse updateServiceRecord(
            Long serviceRecordId,
            ServiceRecordRequest serviceRecord,
            Principal connectedUser
    ) {
        ServiceRecord existingService = checkServiceAndUser(serviceRecordId, connectedUser);
        modelMapper.map(serviceRecord, existingService);
        ServiceRecord updatedService = serviceRecordRepository.save(existingService);
        return modelMapper.map(updatedService, ServiceRecordResponse.class);
    }

    private ServiceRecord checkServiceAndUser(Long serviceRecordId, Principal connectedUser) {
        var user = userService.getConnectedUser(connectedUser);
        ServiceRecord serviceRecord = serviceRecordRepository.findById(serviceRecordId)
                .orElseThrow(() -> new ResourceNotFoundException("Service record with id " + serviceRecordId + " not exists"));
        carRepository.findByCarIdAndUserId(serviceRecord.getCar().getCarId(), user.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Service record with id " + serviceRecordId + " not exists by user id " + user.getId()));
        return serviceRecord;
    }

    @Transactional(readOnly = true)
    public List<ServiceRecordResponse> getServicesByCar(Long carId, Principal connectedUser) {
        carService.validateCarAndUser(carId, connectedUser);
        var user = userService.getConnectedUser(connectedUser);
        return serviceRecordRepository.findByCarCarId(carId).stream()
                .map(ServiceRecord -> modelMapper.map(ServiceRecord, ServiceRecordResponse.class))
                .collect(Collectors.toList());
    }
}