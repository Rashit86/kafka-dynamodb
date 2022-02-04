package com.pet.project.kafkadynamodb.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pet.project.kafkadynamodb.entity.CarInfo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CarInfoDeserializer extends StdDeserializer<CarInfo> {

    private static final String DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER);
    public static final String CAR_MODEL = "carModel";
    public static final String IS_RESERVED = "isReserved";
    public static final String RENTER_NAME = "renterName";
    public static final String DATE_TIME = "dateTime";

    public CarInfoDeserializer() {
        this(null);
    }

    public CarInfoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public CarInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String carModel = node.get(CAR_MODEL).asText();
        boolean isReserved = node.get(IS_RESERVED).asBoolean();
        String renterName = node.get(RENTER_NAME).asText();
        LocalDateTime dateTime = LocalDateTime.parse(node.get(DATE_TIME).asText(), DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER);

        return new CarInfo(carModel, isReserved, renterName, dateTime);
    }
}
