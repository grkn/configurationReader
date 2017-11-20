package com.trendyol.demo.service;

import com.trendyol.demo.model.ConfigParameter;
import com.trendyol.demo.repository.ConfigParameterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfigParameterServiceImpl implements ConfigParameterService {

    @Autowired
    ConfigParameterRepository configParameterRepository;

    @Override
    public List<ConfigParameter> findAll() {
        return configParameterRepository.findAll();
    }

    @Override
    public ConfigParameter saveOrUpdate(ConfigParameter configParameter) {
        return configParameterRepository.save(configParameter);
    }

    @Override
    public void delete(String id) {
        configParameterRepository.delete(id);
    }
}
