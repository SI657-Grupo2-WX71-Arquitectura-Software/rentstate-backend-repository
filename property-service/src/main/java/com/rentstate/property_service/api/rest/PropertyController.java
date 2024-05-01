package com.rentstate.property_service.api.rest;


import com.rentstate.property_service.api.dto.PropertyRequest;
import com.rentstate.property_service.api.dto.PropertyResponse;
import com.rentstate.property_service.service.PropertyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping(value = "/api/v1/properties", produces = "application/json")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping
    public ResponseEntity<PropertyResponse> createProperty(@RequestBody PropertyRequest propertyRequestDTO, @RequestParam Long userId) {
        PropertyResponse createdProperty = propertyService.createProperty(propertyRequestDTO, userId);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProperty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PropertyResponse> updateProperty(@PathVariable Long id, @RequestBody PropertyRequest propertyRequestDTO, @RequestParam Long userId) {
        PropertyResponse updatedProperty = propertyService.updateProperty(id, propertyRequestDTO, userId);
        if (updatedProperty != null) {
            return ResponseEntity.ok(updatedProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProperty(@PathVariable Long id, @RequestParam Long userId) {
        PropertyResponse deletedProperty = propertyService.deleteProperty(id, userId);
        if (deletedProperty != null) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PropertyResponse> getPropertyById(@PathVariable Long id) {
        PropertyResponse property = propertyService.getPropertyById(id);
        if (property != null) {
            return ResponseEntity.ok(property);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public ResponseEntity<List<PropertyResponse>> getAllProperties() {
        List<PropertyResponse> properties = propertyService.getAllProperties();
        return ResponseEntity.ok(properties);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PropertyResponse>> getPropertiesByUserId(@PathVariable Long userId) {
        List<PropertyResponse> properties = propertyService.getPropertiesByUserId(userId);
        return ResponseEntity.ok(properties);
    }

    @PutMapping("/{id}/available")
    public ResponseEntity<PropertyResponse> markPropertyAvailable(@PathVariable Long id, @RequestParam Long userId) {
        PropertyResponse availableProperty = propertyService.availableProperty(id, userId);
        if (availableProperty != null) {
            return ResponseEntity.ok(availableProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}/unavailable")
    public ResponseEntity<PropertyResponse> markPropertyUnavailable(@PathVariable Long id, @RequestParam Long userId) {
        PropertyResponse unavailableProperty = propertyService.unavailableProperty(id, userId);
        if (unavailableProperty != null) {
            return ResponseEntity.ok(unavailableProperty);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
