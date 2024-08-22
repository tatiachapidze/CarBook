package ge.gov.dga.carbook.controller;

import ge.gov.dga.carbook.model.dto.serviceRecord.ServiceRecordRequest;
import ge.gov.dga.carbook.model.dto.serviceRecord.ServiceRecordResponse;
import ge.gov.dga.carbook.service.ServiceRecordService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/service-record")
@RequiredArgsConstructor
@Validated
public class ServiceRecordController {

    private final ServiceRecordService serviceRecordService;


    @PostMapping("{carId}")
    public ResponseEntity<ServiceRecordResponse> addServiceRecord(
            @PathVariable @Positive long carId,
            @RequestBody @Valid ServiceRecordRequest serviceRecord,
            Principal connectedUse
    ) {
        ServiceRecordResponse newServiceRecord = serviceRecordService.addServiceRecord(carId, serviceRecord, connectedUse);
        return ResponseEntity.ok(newServiceRecord);
    }

    @DeleteMapping("/{serviceRecordId}")
    public ResponseEntity<String> deleteServiceRecord(@PathVariable Long serviceRecordId, Principal connectedUser) {
        serviceRecordService.deleteServiceRecord(serviceRecordId, connectedUser);
        return ResponseEntity.ok().body("Service Record has been deleted");
    }

    @PutMapping("/{serviceRecordId}")
    public ResponseEntity<ServiceRecordResponse> updateServiceRecord(
            @PathVariable @Positive long serviceRecordId,
            @RequestBody @Valid ServiceRecordRequest serviceRecord,
            Principal connectedUser
    ) {
        ServiceRecordResponse updatedServiceRecord = serviceRecordService.updateServiceRecord(
                serviceRecordId,
                serviceRecord,
                connectedUser
        );
        return ResponseEntity.ok(updatedServiceRecord);
    }

    @GetMapping("/search")
    public ResponseEntity<List<ServiceRecordResponse>> getServicesByCar(
            @RequestParam Long carId,
            Principal connectedUser
    ) {
        List<ServiceRecordResponse> serviceRecords = serviceRecordService.getServicesByCar(carId, connectedUser);
        return ResponseEntity.ok(serviceRecords);
    }

}