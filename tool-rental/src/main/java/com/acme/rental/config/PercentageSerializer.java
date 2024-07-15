package com.acme.rental.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;

public class PercentageSerializer extends JsonSerializer<Integer> {
    @Override
    public void serialize(Integer value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        String formattedPercentage = value + "%";
        gen.writeString(formattedPercentage);
    }
}