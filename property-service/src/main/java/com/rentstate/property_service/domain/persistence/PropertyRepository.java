package com.rentstate.property_service.domain.persistence;

import com.rentstate.property_service.domain.model.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    Property findByIdAndUserId(Long id, Long userId);
    List<Property> findByUserId(Long userId);
}
