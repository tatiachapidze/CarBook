package ge.gov.dga.carbook.repository;

import ge.gov.dga.carbook.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CarRepository extends JpaRepository<Car, Long> {
    Boolean existsByStateNumber(String stateNumber);

    Optional<Car> findByCarIdAndUserId(Long carId, Long userId);

    Boolean existsByCarIdAndUserId(Long carId, Long userId);

    @Query("SELECT c FROM Car c WHERE c.user.id = :userId AND (" +
            "LOWER(c.stateNumber) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.vinCode) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.make) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.make) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.color) LIKE LOWER(CONCAT('%',:keyword,'%')))")
    List<Car> findByUserIdAndKeyword(@Param("userId") Long userId, @Param("keyword") String keyword);


    @Query("SELECT c FROM Car c WHERE c.user.id = :userId AND (" +
            "LOWER(c.stateNumber) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.vinCode) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.make) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.make) LIKE LOWER(CONCAT('%',:keyword,'%')) OR " +
            "LOWER(c.color) LIKE LOWER(CONCAT('%',:keyword,'%')))")
    Page<Car> findByUserIdAndKeyword(@Param("userId") Long userId, @Param("keyword") String keyword, Pageable pageable);
}