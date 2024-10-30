package com.rentstate.property_service.service.impl;


import com.rentstate.property_service.api.dto.PropertyRequest;
import com.rentstate.property_service.api.dto.PropertyResponse;
import com.rentstate.property_service.domain.model.entity.Property;
import com.rentstate.property_service.domain.persistence.PropertyRepository;
import com.rentstate.property_service.service.PropertyService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    private final ModelMapper modelMapper;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, ModelMapper modelMapper) {
        this.propertyRepository = propertyRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Property createProperty(PropertyRequest propertyRequest, Long userId) {
        Property propertyResponse = modelMapper.map(propertyRequest, Property.class);
        propertyResponse.setUserId(userId);
        return propertyRepository.save(propertyResponse);
    }

    @Override
    public Property updateProperty(Long id, PropertyRequest propertyRequest) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);

        if (optionalProperty.isEmpty()) {
            throw new IllegalArgumentException("Property with id " + id + " not found");
        }
        Property property = optionalProperty.get();
        property.setCategory(propertyRequest.getCategory());
        property.setDistrict(propertyRequest.getDistrict());
        property.setLocation(propertyRequest.getLocation());
        property.setLatitude(propertyRequest.getLatitude());
        property.setLongitude(propertyRequest.getLongitude());
        property.setDescription(propertyRequest.getDescription());
        property.setCharacteristics(propertyRequest.getCharacteristics());
        property.setCardimage(propertyRequest.getCardimage());
        property.setPrice(propertyRequest.getPrice());

        return propertyRepository.save(property);
    }

    @Override
    public void deleteProperty(Long id) {
        propertyRepository.deleteById(id);
    }

    @Override
    public Property getPropertyById(Long id) {
        Optional<Property> optionalProperty = propertyRepository.findById(id);
        if (optionalProperty.isPresent()) {
            return optionalProperty.get();
        } else {
            throw new IllegalArgumentException("Property with id " + id + " not found");
        }
    }
    @Override
    public Iterable<PropertyResponse> getAllProperties() {
        Iterable<Property> properties = propertyRepository.findAll();
        Type listType = new TypeToken<Iterable<PropertyResponse>>() {}.getType();
        return modelMapper.map(properties, listType);
    }

    @Override
    public Iterable<PropertyResponse> getPropertiesByUserId(Long userId) {
        Iterable<Property> properties = propertyRepository.findByUserId(userId);
        Type listType = new TypeToken<Iterable<PropertyResponse>>() {}.getType();
        return modelMapper.map(properties, listType);

    }



    @Override
    public Property availableProperty(Long id) {
        return propertyRepository.findById(id)
                .map(property -> {
            property.setAvailable(true);
            return propertyRepository.save(property);
        }).orElse(null);

    }

    @Override
    public Property unavailableProperty(Long id) {

        return propertyRepository.findById(id)
                .map(property -> {
            property.setAvailable(false);
            return propertyRepository.save(property);
        }).orElse(null);

    }

    @Override
    public Property rentedProperty(Long id) {
        return propertyRepository.findById(id)
                .map(property -> {
            property.setRented(true);
            return propertyRepository.save(property);
        }).orElse(null);

    }

    @Override
    public Property unrentedProperty(Long id) {
        return propertyRepository.findById(id)
                .map(property -> {
            property.setRented(false);
            return propertyRepository.save(property);
        }).orElse(null);

    }

    @Override
    public void addInterestedUser(Long propertyId, Long userId) {
        Optional<Property> optionalProperty = propertyRepository.findById(propertyId);

        if (optionalProperty.isEmpty()) {
            throw new IllegalArgumentException("Property with id " + propertyId + " not found");
        }

        Property property = optionalProperty.get();
        List<Long> interestedUserIds = property.getInterestedUserIds();

        if (interestedUserIds == null) {
            interestedUserIds = new ArrayList<>();
        }

        interestedUserIds.add(userId);
        property.setInterestedUserIds(interestedUserIds);

        propertyRepository.save(property);
    }

    @Override
    public void addTenant(Long propertyId, Long userId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property with id " + propertyId + " not found"));

        property.setTenantId(userId);
        propertyRepository.save(property);
    }

    @Override
    public void removeTenantAndAddToExTenants(Long propertyId, Long userId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new IllegalArgumentException("Property with id " + propertyId + " not found"));

        if (property.getTenantId() != null && property.getTenantId().equals(userId)) {
            property.setTenantId(null);

            List<Long> exTenantIds = Optional.ofNullable(property.getExTenantIds()).orElse(new ArrayList<>());
            if (!exTenantIds.contains(userId)) {
                exTenantIds.add(userId);
                property.setExTenantIds(exTenantIds);
            }

            propertyRepository.save(property);
        } else {
            throw new IllegalArgumentException("User with id " + userId + " is not a current tenant of this property");
        }
    }



}