package umc.aehopark.domain.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.aehopark.domain.delivery.entity.Place;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place, Long> {

    Place findAllById(Long id);

    List<Place> findAllByUserId(Long id);


}
