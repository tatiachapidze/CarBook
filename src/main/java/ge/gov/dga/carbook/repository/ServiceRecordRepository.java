package ge.gov.dga.carbook.repository;

import ge.gov.dga.carbook.entity.ServiceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceRecordRepository extends JpaRepository<ServiceRecord, Long> {
    List<ServiceRecord> findByCarCarId(Long carId);
}