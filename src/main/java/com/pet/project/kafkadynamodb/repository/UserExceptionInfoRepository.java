package com.pet.project.kafkadynamodb.repository;

import com.pet.project.kafkadynamodb.entity.UserExceptionInfo;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

@EnableScan
public interface UserExceptionInfoRepository extends CrudRepository<UserExceptionInfo, String> {

}
