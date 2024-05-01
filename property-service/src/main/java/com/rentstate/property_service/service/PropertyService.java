package com.rentstate.property_service.service;

import com.rentstate.property_service.api.dto.PropertyRequest;
import com.rentstate.property_service.api.dto.PropertyResponse;


import java.util.List;

public interface PropertyService {

    PropertyResponse createProperty(PropertyRequest propertyRequestDTO, Long userId);

    PropertyResponse updateProperty(Long id, PropertyRequest propertyRequestDTO, Long userId);

    PropertyResponse deleteProperty(Long id, Long userId);

    PropertyResponse getPropertyById(Long id);

    List<PropertyResponse> getAllProperties();

    List<PropertyResponse> getPropertiesByUserId(Long userId);

    PropertyResponse availableProperty(Long id, Long userId);

    PropertyResponse unavailableProperty(Long id, Long userId);
}
