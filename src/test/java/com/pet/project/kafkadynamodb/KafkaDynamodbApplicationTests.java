package com.pet.project.kafkadynamodb;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.pet.project.kafkadynamodb.entity.CarInfo;
import com.pet.project.kafkadynamodb.entity.UserExceptionInfo;
import com.pet.project.kafkadynamodb.repository.CarInfoRepository;
import com.pet.project.kafkadynamodb.repository.UserExceptionInfoRepository;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = KafkaDynamodbApplication.class)
@WebAppConfiguration
@ActiveProfiles("local")
@TestPropertySource(properties = {
        "amazon.dynamodb.endpoint=http://localhost:8000/",
        "amazon.aws.accessKeyId=",
        "amazon.aws.secretKey=" })
class KafkaDynamodbApplicationTests {

    private DynamoDBMapper dynamoDBMapper;

    @Autowired
    private AmazonDynamoDB amazonDynamoDB;

    @Autowired
    CarInfoRepository carInfoRepository;

    @Autowired
    UserExceptionInfoRepository userExceptionInfoRepository;

    private static final String CAR_MODEL = "BMW";

    @Before
    public void setup() {
        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        CreateTableRequest tableRequest = dynamoDBMapper
                .generateCreateTableRequest(CarInfo.class);
        tableRequest.setProvisionedThroughput(
                new ProvisionedThroughput(1L, 1L));
        amazonDynamoDB.createTable(tableRequest);

        dynamoDBMapper.batchDelete(carInfoRepository.findAll());
    }

    @Test
    void saveCarTest() {
        CarInfo carInfo = new CarInfo();
        carInfo.setCarModel(CAR_MODEL);
        carInfoRepository.save(carInfo);
        List<CarInfo> result = (List<CarInfo>) carInfoRepository.findAll();

        Assertions.assertTrue(result.size() > 0);
        Assertions.assertEquals(CAR_MODEL, result.get(0).getCarModel());
    }

    @Test
    void saveUserExceptionTest() {
        UserExceptionInfo userExceptionInfo = new UserExceptionInfo();
        userExceptionInfo.setCarModel(CAR_MODEL);
        userExceptionInfoRepository.save(userExceptionInfo);
        List<UserExceptionInfo> result = (List<UserExceptionInfo>) userExceptionInfoRepository.findAll();

        Assertions.assertTrue(result.size() > 0);
        Assertions.assertEquals(CAR_MODEL, result.get(0).getCarModel());
    }

}
