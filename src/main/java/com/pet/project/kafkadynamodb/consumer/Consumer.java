package com.pet.project.kafkadynamodb.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pet.project.kafkadynamodb.entity.CarInfo;
import com.pet.project.kafkadynamodb.entity.UserExceptionInfo;
import com.pet.project.kafkadynamodb.repository.CarInfoRepository;
import com.pet.project.kafkadynamodb.repository.UserExceptionInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Consumer {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final CarInfoRepository carInfoRepository;
    private final UserExceptionInfoRepository userExceptionInfoRepository;

    @KafkaListener(topics = "${kafka-dynamodb-app.topic-name.rented-cars}")
    public void saveCars(String content) throws JsonProcessingException {
        CarInfo carInfo = objectMapper.readValue(content, CarInfo.class);
        carInfoRepository.save(carInfo);
    }

    @KafkaListener(topics = "${kafka-dynamodb-app.topic-name.user-exceptions}")
    public void saveUserExceptions(String content) throws JsonProcessingException {
        UserExceptionInfo userExceptionInfo = objectMapper.readValue(content, UserExceptionInfo.class);
        userExceptionInfoRepository.save(userExceptionInfo);
    }

}
