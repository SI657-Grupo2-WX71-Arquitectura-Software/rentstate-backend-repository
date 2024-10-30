package com.rentstate.property_service.service;

import com.rentstate.property_service.api.dto.PropertyRequest;
import com.rentstate.property_service.api.dto.PropertyResponse;


import java.util.List;

public interface PropertyService {

    Property createProperty(PropertyRequest propertyRequest, Long userId);

    Property updateProperty(Long id, PropertyRequest propertyRequest);

    void deleteProperty(Long id);

    Property getPropertyById(Long id);

    Iterable<PropertyResponse> getAllProperties();

    Iterable<PropertyResponse> getPropertiesByUserId(Long userId);

    Property availableProperty(Long id);

    Property unavailableProperty(Long id);

    Property rentedProperty(Long id);

    Property unrentedProperty(Long id);

    void addInterestedUser(Long propertyId, Long userId);

    void addTenant(Long propertyId, Long userId);


    void removeTenantAndAddToExTenants(Long propertyId, Long userId);
}
