package com.acme.rental.config;

// Custom serializer for currency
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DecimalFormat;

public class CurrencySerializer extends StdSerializer<Double> {

    private static final DecimalFormat formatter = new DecimalFormat("$#,##0.00");

    public CurrencySerializer() {
        this(null);
    }

    public CurrencySerializer(Class<Double> t) {
        super(t);
    }

    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        gen.writeString(formatter.format(value));
    }
}
