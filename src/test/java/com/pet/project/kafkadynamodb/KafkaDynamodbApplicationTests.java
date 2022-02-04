//package com.pet.project.kafkadynamodb;
//
//import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
//import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
//import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
//import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
//import com.pet.project.kafkadynamodb.entity.CarInfo;
//import com.pet.project.kafkadynamodb.repository.CarInfoRepository;
//import org.junit.Before;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.TestPropertySource;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.web.WebAppConfiguration;
//
//import java.util.List;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = KafkaDynamodbApplication.class)
//@WebAppConfiguration
//@ActiveProfiles("local")
//@TestPropertySource(properties = {
//        "amazon.dynamodb.endpoint=http://localhost:8000/",
//        "amazon.aws.accesskey=fakeMyKeyId",
//        "amazon.aws.secretkey=fakeSecretAccessKey" })
//class KafkaDynamodbApplicationTests {
//
//    private DynamoDBMapper dynamoDBMapper;
//
//    @Autowired
//    private AmazonDynamoDB amazonDynamoDB;
//
//    @Autowired
//    CarInfoRepository repository;
//
//    private static final String CAR_MODEL = "BMW";
//    private static final String IS_RESERVED = "FALSE";
//
//    @Before
//    public void setup() {
//        dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);
//
//        CreateTableRequest tableRequest = dynamoDBMapper
//                .generateCreateTableRequest(CarInfo.class);
//        tableRequest.setProvisionedThroughput(
//                new ProvisionedThroughput(1L, 1L));
//        amazonDynamoDB.createTable(tableRequest);
//
//        //...
//
//        dynamoDBMapper.batchDelete(repository.findAll());
//    }
//
////    @Test
////    void givenItemWithExpectedCarModel_whenRunFindAll_thenItemIsFound() {
////        CarInfo carInfo = new CarInfo(CAR_MODEL, IS_RESERVED);
////        repository.save(carInfo);
////        List<CarInfo> result = (List<CarInfo>) repository.findAll();
////
////        Assertions.assertTrue(result.size() > 0);
////        Assertions.assertEquals(CAR_MODEL, result.get(0).getCarModel());
////    }
//
//}
