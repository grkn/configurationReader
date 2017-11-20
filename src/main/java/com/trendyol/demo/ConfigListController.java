package com.trendyol.demo;

import com.trendyol.demo.model.ConfigParameter;
import com.trendyol.demo.service.ConfigParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@Controller
public class ConfigListController {

    @Autowired
    private ConfigParameterService configParameterService;


    @RequestMapping(value = "/configParameters", method = RequestMethod.POST)
    public ResponseEntity<ConfigParameter> saveParam(@RequestBody ConfigParameter configParameter) {
        ConfigParameter saved = null;
        try {
            saved = configParameterService.saveOrUpdate(configParameter);
        } catch (Exception e) {

        }

        return new ResponseEntity<>(configParameter, HttpStatus.OK);
    }

    @RequestMapping(value = "/configParameters", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteParam(@RequestParam String id) {
        ConfigParameter saved = null;
        try {
             configParameterService.delete(id);
        } catch (Exception e) {

        }

        return new ResponseEntity<>("", HttpStatus.OK);
    }


    @GetMapping("/")
    public String getConfigList(ModelMap model) {
        model.put("configList", configParameterService.findAll());

        return "welcome";
    }

}
