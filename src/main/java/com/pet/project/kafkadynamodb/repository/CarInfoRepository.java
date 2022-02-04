package com.pet.project.kafkadynamodb.repository;

import com.pet.project.kafkadynamodb.entity.CarInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface CarInfoRepository extends CrudRepository<CarInfo, String> {

}
