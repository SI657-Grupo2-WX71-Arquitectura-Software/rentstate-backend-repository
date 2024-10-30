package com.rentstate.user_service.api.resource;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rentstate.user_service.model.aggregates.Coordinates;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.ArrayList;
import java.util.List;

@Converter
public class CoverageAreaInterestConverter implements AttributeConverter<List<List<Coordinates>>, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<List<Coordinates>> attribute) {
        if (attribute == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(attribute);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error serializing coverageAreaInterest", e);
        }
    }

    @Override
    public List<List<Coordinates>> convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.trim().isEmpty()) {
            return new ArrayList<>();
        }
        try {
            return objectMapper.readValue(dbData, new TypeReference<List<List<Coordinates>>>() {});
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error deserializing coverageAreaInterest", e);
        }
    }
}
