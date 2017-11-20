package com.trendyol.demo;

import com.trendyol.demo.model.ConfigParameter;
import com.trendyol.demo.service.ConfigParameterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;


@ComponentScan
@EnableAutoConfiguration
@EnableMongoRepositories(
		basePackages = "com.trendyol.demo.repository"
)
@SpringBootApplication
public class SpringBootWebApplication extends SpringBootServletInitializer {

	@Autowired
	private ConfigParameterService configParameterService;

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(SpringBootWebApplication.class);
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootWebApplication.class, args);
	}

	/* Needed to remove _class data from document */
	@Bean
	public MongoTemplate mongoTemplate(MongoDbFactory mongoDbFactory, MongoMappingContext context) {
		MappingMongoConverter converter = new MappingMongoConverter(new DefaultDbRefResolver(mongoDbFactory), context);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return new MongoTemplate(mongoDbFactory, converter);
	}

//	@Bean
//	CommandLineRunner commandLineRunner() {
//		ConfigParameter parameter1 = new ConfigParameter();
//        parameter1.setApplicationName("SERVICE-A");
//        parameter1.setActive(true);
//        parameter1.setType("String");
//        parameter1.setName("SiteName");
//        parameter1.setValue("trendyol.com");
//		configParameterService.saveOrUpdate(parameter1);
//
//        ConfigParameter parameter2 = new ConfigParameter();
//        parameter2.setType("Boolean");
//        parameter2.setApplicationName("SERVICE-B");
//        parameter2.setActive(true);
//        parameter2.setName("IsBasketEnabled");
//        parameter2.setValue("1");
//		configParameterService.saveOrUpdate(parameter2);
//
//        ConfigParameter parameter3 = new ConfigParameter();
//        parameter3.setType("Int");
//        parameter3.setApplicationName("SERVICE-A");
//        parameter3.setActive(false);
//        parameter3.setName("MaxItemCount");
//        parameter3.setValue("50");
//		configParameterService.saveOrUpdate(parameter3);
//
//	}
}