package com.trendyol.demo.repository;

import com.trendyol.demo.model.ConfigParameter;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "configParameters", path = "configParameters")
public interface ConfigParameterRepository extends MongoRepository<ConfigParameter, String>, ConfigParameterRepositoryCustom {

    List<ConfigParameter> findAllByApplicationNameAndActiveTrue(String applicationName);

}
