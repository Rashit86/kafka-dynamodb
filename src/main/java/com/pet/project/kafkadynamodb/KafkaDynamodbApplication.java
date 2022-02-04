package com.pet.project.kafkadynamodb;

import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.model.AttributeDefinition;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.KeySchemaElement;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SpringBootApplication
public class KafkaDynamodbApplication {

    @Value("${amazon.dynamodb.endpoint}")
    private String serviceEndpoint;

    @Value("${amazon.dynamodb.region}")
    private String signingRegion;

    public static final String STRING = "S";
    public static final String KEY_TYPE = "HASH";

    public static final String CAR_INFO = "CarInfo";
    public static final String USER_EXCEPTION_INFO = "UserExceptionInfo";
    public static final String CAR_INFO_KEY = "carModel";
    public static final Long CAR_INFO_RCU = 1L;
    public static final Long USER_EXCEPTION_INFO_RCU = 1L;
    public static final Long CAR_INFO_WCU = 1L;
    public static final Long USER_EXCEPTION_INFO_WCU = 1L;

    public static void main(String[] args) {
        SpringApplication.run(KafkaDynamodbApplication.class, args);
    }

    @Bean
    void createTables() {
        AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(serviceEndpoint, signingRegion))
                .build();
        DynamoDB dynamoDB = new DynamoDB(client);

        CreateTableRequest carInfoRequest = new CreateTableRequest();
        carInfoRequest.setTableName(CAR_INFO);
        carInfoRequest.setAttributeDefinitions(Collections.singletonList(new AttributeDefinition(CAR_INFO_KEY, STRING)));
        carInfoRequest.setKeySchema(Collections.singletonList(new KeySchemaElement(CAR_INFO_KEY, KEY_TYPE)));
        carInfoRequest.setProvisionedThroughput(
                new ProvisionedThroughput(CAR_INFO_RCU, CAR_INFO_WCU));

        CreateTableRequest userExceptionRequest = new CreateTableRequest();
        userExceptionRequest.setTableName(USER_EXCEPTION_INFO);
        userExceptionRequest.setAttributeDefinitions(Collections.singletonList(new AttributeDefinition(CAR_INFO_KEY, STRING)));
        userExceptionRequest.setKeySchema(Collections.singletonList(new KeySchemaElement(CAR_INFO_KEY, KEY_TYPE)));
        userExceptionRequest.setProvisionedThroughput(
                new ProvisionedThroughput(USER_EXCEPTION_INFO_RCU, USER_EXCEPTION_INFO_WCU));

        List<String> tables = new ArrayList<>();
        dynamoDB.listTables().forEach(table -> tables.add(table.getTableName()));

        if (!tables.contains(CAR_INFO)) {
            dynamoDB.createTable(carInfoRequest);
        }

        if (!tables.contains(USER_EXCEPTION_INFO)) {
            dynamoDB.createTable(userExceptionRequest);
        }
    }
}
