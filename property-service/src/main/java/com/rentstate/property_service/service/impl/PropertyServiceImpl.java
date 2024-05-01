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

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public PropertyResponse createProperty(PropertyRequest propertyRequestDTO, Long userId) {
        Property property = new Property();
        BeanUtils.copyProperties(propertyRequestDTO, property);
        property.setUserId(userId);
        property = propertyRepository.save(property);
        return convertToPropertyResponseDTO(property);
    }

    @Override
    public PropertyResponse updateProperty(Long id, PropertyRequest propertyRequestDTO, Long userId) {
        Property existingProperty = propertyRepository.findByIdAndUserId(id, userId);
        if (existingProperty == null) {
            return null;
        }
        BeanUtils.copyProperties(propertyRequestDTO, existingProperty);
        existingProperty = propertyRepository.save(existingProperty);
        return convertToPropertyResponseDTO(existingProperty);
    }

    @Override
    public PropertyResponse deleteProperty(Long id, Long userId) {
        Property property = propertyRepository.findByIdAndUserId(id, userId);
        if (property != null) {
            propertyRepository.delete(property);
            return convertToPropertyResponseDTO(property);
        }
        return null;
    }

    @Override
    public PropertyResponse getPropertyById(Long id) {
        Property property = propertyRepository.findById(id).orElse(null);
        if (property != null) {
            return convertToPropertyResponseDTO(property);
        }
        return null;
    }

    @Override
    public List<PropertyResponse> getAllProperties() {
        List<Property> properties = propertyRepository.findAll();
        return properties.stream().map(this::convertToPropertyResponseDTO).collect(Collectors.toList());
    }

    @Override
    public List<PropertyResponse> getPropertiesByUserId(Long userId) {
        List<Property> properties = propertyRepository.findByUserId(userId);
        return properties.stream().map(this::convertToPropertyResponseDTO).collect(Collectors.toList());
    }

    @Override
    public PropertyResponse availableProperty(Long id, Long userId) {
        Property property = propertyRepository.findByIdAndUserId(id, userId);
        if (property != null) {
            property.setAvailable(true);
            property = propertyRepository.save(property);
            return convertToPropertyResponseDTO(property);
        }
        return null;
    }

    @Override
    public PropertyResponse unavailableProperty(Long id, Long userId) {
        Property property = propertyRepository.findByIdAndUserId(id, userId);
        if (property != null) {
            property.setAvailable(false);
            property = propertyRepository.save(property);
            return convertToPropertyResponseDTO(property);
        }
        return null;
    }

    private PropertyResponse convertToPropertyResponseDTO(Property property) {
        PropertyResponse propertyResponseDTO = new PropertyResponse();
        BeanUtils.copyProperties(property, propertyResponseDTO);
        return propertyResponseDTO;
    }
}
