package com.pet.project.kafkadynamodb.converter;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeConverter implements DynamoDBTypeConverter<String, LocalDateTime> {

    private static final String DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER = "yyyy-MM-dd'T'HH:mm:ss";
    private static final DateTimeFormatter DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER);

    @Override
    public String convert(LocalDateTime dateTime) {
        return dateTime.format(DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER);
    }

    @Override
    public LocalDateTime unconvert(String dateTime) {
        return LocalDateTime.parse(dateTime, DATE_TIME_PATTERN_ISO_WITH_TIME_DELIMITER_FORMATTER);
    }
}
