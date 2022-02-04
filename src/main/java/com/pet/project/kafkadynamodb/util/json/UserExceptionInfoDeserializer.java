package com.pet.project.kafkadynamodb.util.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.pet.project.kafkadynamodb.entity.UserExceptionInfo;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class UserExceptionInfoDeserializer extends StdDeserializer<UserExceptionInfo> {

    private static final String DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER);
    public static final String CAR_MODEL = "carModel";
    public static final String MESSAGE = "message";
    public static final String DATE_TIME = "dateTime";

    public UserExceptionInfoDeserializer() {
        this(null);
    }

    public UserExceptionInfoDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public UserExceptionInfo deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        String message = node.get(MESSAGE).asText();
        LocalDateTime dateTime = LocalDateTime.parse(node.get(DATE_TIME).asText(), DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER);

        return new UserExceptionInfo(message, dateTime);
    }
}
