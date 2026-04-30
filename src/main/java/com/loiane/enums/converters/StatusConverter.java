package com.loiane.enums.converters;

import com.loiane.enums.Status;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class StatusConverter implements AttributeConverter<Status, String> {
    
    @Override
    public String convertToDatabaseColumn(Status status) {
        if (status == null) {
            return null;
        }
        return status.getValue();
    }
    
    @Override
    public Status convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }
        return java.util.stream.Stream.of(Status.values())
                .filter(s -> s.getValue().equals(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid Status: " + value));
    }
}
