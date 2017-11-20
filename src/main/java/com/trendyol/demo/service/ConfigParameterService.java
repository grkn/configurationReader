package com.trendyol.demo.service;

import com.trendyol.demo.model.ConfigParameter;

import java.util.List;

public interface ConfigParameterService {

    List<ConfigParameter> findAll();

    ConfigParameter saveOrUpdate(ConfigParameter configParameter);

    void delete(String id);


}
