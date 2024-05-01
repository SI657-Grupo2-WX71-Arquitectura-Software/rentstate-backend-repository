package com.rentstate.property_service.service.impl;

import com.rentstate.property_service.api.dto.PropertyRequest;
import com.rentstate.property_service.api.dto.PropertyResponse;
import com.rentstate.property_service.domain.model.entity.Property;
import com.rentstate.property_service.domain.persistence.PropertyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

class PropertyServiceImplTest {

    @Mock
    private PropertyRepository propertyRepository;

    @InjectMocks
    private PropertyServiceImpl propertyService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createProperty() {
        PropertyRequest propertyRequest = new PropertyRequest();
        Property property = new Property();
        property.setUserId(1L);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyResponse propertyResponse = propertyService.createProperty(propertyRequest, 1L);

        assertNotNull(propertyResponse);
        assertEquals(1L, propertyResponse.getUserId());
    }

    @Test
    void updateProperty() {
        PropertyRequest propertyRequest = new PropertyRequest();
        Property property = new Property();
        property.setUserId(1L);
        when(propertyRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(property);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyResponse propertyResponse = propertyService.updateProperty(1L, propertyRequest, 1L);

        assertNotNull(propertyResponse);
        assertEquals(1L, propertyResponse.getUserId());
    }

    @Test
    void getAllProperties() {
        List<Property> properties = new ArrayList<>();
        Property property = new Property();
        property.setUserId(1L);
        properties.add(property);
        when(propertyRepository.findAll()).thenReturn(properties);

        List<PropertyResponse> propertyResponses = propertyService.getAllProperties();

        assertNotNull(propertyResponses);
        assertFalse(propertyResponses.isEmpty());
        assertEquals(1L, propertyResponses.get(0).getUserId());
    }

    @Test
    void availableProperty() {
        Property property = new Property();
        property.setUserId(1L);
        when(propertyRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(property);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyResponse propertyResponse = propertyService.availableProperty(1L, 1L);

        assertNotNull(propertyResponse);
        assertEquals(1L, propertyResponse.getUserId());
    }

    @Test
    void unavailableProperty() {
        Property property = new Property();
        property.setUserId(1L);
        when(propertyRepository.findByIdAndUserId(anyLong(), anyLong())).thenReturn(property);
        when(propertyRepository.save(any(Property.class))).thenReturn(property);

        PropertyResponse propertyResponse = propertyService.unavailableProperty(1L, 1L);

        assertNotNull(propertyResponse);
        assertEquals(1L, propertyResponse.getUserId());
    }
}